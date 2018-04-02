package com.shuangyulin.controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shuangyulin.utils.UserException;

public class BaseController {
	@InitBinder
	// ������һ������WebDataBinder
	public void initBinder(WebDataBinder binder) {
		//System.out.println(binder.getFieldDefaultPrefix());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	 
		binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() { 
				return (getValue() == null) ? "" : getValue().toString();
			} 
			@Override
			public void setAsText(String text) {
				Integer value = null;
				if (null != text && !text.equals("")) {  
						try {
							value = Integer.valueOf(text);
						} catch(Exception ex)  { 
							throw new UserException("���ݸ�ʽ���벻��ȷ��"); 
						}  
				}
				setValue(value);
			} 
		});
	  
		//binder.registerCustomEditor(Integer.class, null,new CustomNumberEditor(Integer.class, null, true));
		
		binder.registerCustomEditor(Float.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() { 
				return (getValue() == null)? "" : getValue().toString();
			} 
			@Override
			public void setAsText(String text)  {
				Float value = null;
				if (null != text && !text.equals("")) {
					try {
						value = Float.valueOf(text);
					} catch (Exception e) { 
						throw new UserException("���ݸ�ʽ���벻��ȷ��"); 
					}
				}
				setValue(value);
			}
		});
	}
 
	/** 
	 * ����ͼƬ�ļ��ϴ������ر�����ļ���·��
	 * fileKeyName: ͼƬ�ϴ���key
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */ 
	public String handlePhotoFileUpload(HttpServletRequest request,String fileKeyName) throws IllegalStateException, IOException {
		String fileName = "upload/NoImage.jpg";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
        /**����ͼƬ�����Ŀ¼**/    
        String photoBookPathDir = "/upload";     
        /**�õ�ͼƬ����Ŀ¼����ʵ·��**/    
        String photoBookRealPathDir = request.getSession().getServletContext().getRealPath(photoBookPathDir);     
        /**������ʵ·������Ŀ¼**/    
        File photoBookSaveFile = new File(photoBookRealPathDir);     
        if(!photoBookSaveFile.exists())     
        	photoBookSaveFile.mkdirs();           
        /**ҳ��ؼ����ļ���**/    
        MultipartFile multipartFile_photoBook = multipartRequest.getFile(fileKeyName);    
        if(!multipartFile_photoBook.isEmpty()) {
        	/**��ȡ�ļ��ĺ�׺**/    
            String suffix = multipartFile_photoBook.getOriginalFilename().substring  
                            (multipartFile_photoBook.getOriginalFilename().lastIndexOf("."));     
            /**ʹ��UUID�����ļ�����**/    
            String photoBookFileName = UUID.randomUUID().toString()+ suffix;//�����ļ�����     
            //String logImageName = multipartFile.getOriginalFilename();  
            /**ƴ���������ļ�����·�����ļ�**/    
            String photoBookFilePath = photoBookRealPathDir + File.separator  + photoBookFileName;                
            File photoBookFile = new File(photoBookFilePath);          
           
            multipartFile_photoBook.transferTo(photoBookFile);     
            
            fileName = "upload/" + photoBookFileName;
        } 
		
		return fileName;
	}


}
