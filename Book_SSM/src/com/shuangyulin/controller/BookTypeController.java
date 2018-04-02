package com.shuangyulin.controller;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shuangyulin.po.BookType;
import com.shuangyulin.service.BookTypeService;

//ͼ�����͹�����Ʋ�
@Controller
@RequestMapping("/BookType")
public class BookTypeController {

	@Resource
	BookTypeService bookTypeService;

	@InitBinder
	// ������һ������WebDataBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
		binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() {
				return getValue() == null ? "" : getValue().toString();
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				Integer value = 0;
				if (null != text && !text.equals("")) {
					value = Integer.valueOf(text);
				}
				setValue(value);
			}
		});
	 
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new BookType());
		return "BookType_add";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated BookType bookType, BindingResult br,
			Model model, HttpServletRequest request) {
		if (br.hasErrors()) {
			model.addAttribute(bookType);
			return "BookType_add";
		}
		try {
			bookTypeService.AddBookType(bookType);
			request.setAttribute("message", java.net.URLEncoder.encode(
					"ͼ��������ӳɹ�!", "GBK"));
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", java.net.URLEncoder
					.encode("ͼ���������ʧ��!"));
			return "error";
		}

	}

	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Integer currentPage, Model model, HttpServletRequest request) throws Exception {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		List<BookType> bookTypeList = bookTypeService.QueryBookTypeInfo(currentPage);
		/* �����ܵ�ҳ�����ܵļ�¼�� */
		bookTypeService.CalculateTotalPageAndRecordNumber();
		/* ��ȡ���ܵ�ҳ����Ŀ */
		int totalPage = bookTypeService.getTotalPage();
		/* ��ǰ��ѯ�������ܼ�¼�� */
		int recordNumber = bookTypeService.getRecordNumber();
		request.setAttribute("bookTypeList", bookTypeList);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("recordNumber", recordNumber);
		request.setAttribute("currentPage", currentPage);
		return "BookType_query_result"; 
	}
	
	
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(Integer currentPage, Model model, HttpServletRequest request) throws Exception {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		List<BookType> bookTypeList = bookTypeService.QueryBookTypeInfo(currentPage);
		/* �����ܵ�ҳ�����ܵļ�¼�� */
		bookTypeService.CalculateTotalPageAndRecordNumber();
		/* ��ȡ���ܵ�ҳ����Ŀ */
		int totalPage = bookTypeService.getTotalPage();
		/* ��ǰ��ѯ�������ܼ�¼�� */
		int recordNumber = bookTypeService.getRecordNumber();
		request.setAttribute("bookTypeList", bookTypeList);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("recordNumber", recordNumber);
		request.setAttribute("currentPage", currentPage);
		return "BookType_frontquery_result"; 
	}
	
	
	
	@RequestMapping(value="/{bookTypeId}/update",method=RequestMethod.GET)
	public String update(@PathVariable int bookTypeId,Model model) throws Exception {
		/*��������bookTypeId��ȡBookType����*/
        BookType bookType = bookTypeService.GetBookTypeByBookTypeId(bookTypeId);
        model.addAttribute(bookType); 
        return "BookType_modify"; 
	}
	
	
	@RequestMapping(value="/{bookTypeId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable int bookTypeId,Model model) throws Exception {
		/*��������bookTypeId��ȡBookType����*/
        BookType bookType = bookTypeService.GetBookTypeByBookTypeId(bookTypeId);
        model.addAttribute(bookType); 
        return "BookType_frontshow"; 
	}
	
	
	@RequestMapping(value = "/{bookTypeId}/update", method = RequestMethod.POST)
	public String update(@Validated BookType bookType, BindingResult br,
			Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		if (br.hasErrors()) {
			model.addAttribute(bookType);
			return "BookType_modify";
		}
		try {
			bookTypeService.UpdateBookType(bookType);
			request.setAttribute("message", java.net.URLEncoder.encode(
					"ͼ��������ӳɹ�!", "GBK"));
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", java.net.URLEncoder.encode("ͼ���������ʧ��!","GBK"));
			return "error";
		} 
	}
	
	
	@RequestMapping(value="/{bookTypeId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable int bookTypeId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try { 
			  bookTypeService.DeleteBookType(bookTypeId);
	            request.setAttribute("message", java.net.URLEncoder.encode(
						"ͼ������ɾ���ɹ�!", "GBK"));
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", java.net.URLEncoder
						.encode("ͼ���������ʧ��!","GBK"));
				return "error";
	        }
	}
	
	
	

}
