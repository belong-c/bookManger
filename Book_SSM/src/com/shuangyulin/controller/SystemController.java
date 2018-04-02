package com.shuangyulin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

  
import com.shuangyulin.po.Admin;
import com.shuangyulin.service.AdminService;
import com.shuangyulin.utils.UserException;

 
@Controller
@SessionAttributes("username")
public class SystemController { 
	
	@Resource AdminService adminService;  
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(new Admin());
		return "login";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@Validated Admin admin,BindingResult br,Model model,HttpServletRequest request,HttpSession session) throws Exception { 
		if(br.hasErrors()) {
			return "login";
		} 
		if (!adminService.checkLogin(admin)) {
			request.setAttribute("error", java.net.URLEncoder.encode(adminService.getErrMessage())); 
			return "error";
		} 
		//session.setAttribute("username", admin.getUsername());
		model.addAttribute("username", admin.getUsername());
		return "main";  
		//return "redirect:/user/users";
	}
	
	
	
	@RequestMapping("/logout")
	public String logout(Model model,HttpSession session) {
		model.asMap().remove("username"); 
		session.invalidate();
		return "redirect:/login";
	}
	
	
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public String ChangePassword(String oldPassword,String newPassword,String newPassword2,HttpServletRequest request,HttpSession session) throws Exception { 
		if(oldPassword.equals("")) throw new UserException("����������룡");
		if(newPassword.equals("")) throw new UserException("�����������룡");
		if(!newPassword.equals(newPassword2)) throw new UserException("�������������벻һ��"); 
		
		String username = (String)session.getAttribute("username");
		if(username == null) throw new UserException("session�Ự��ʱ�������µ�¼ϵͳ!");
		 
		
		Admin admin = adminService.findAdminByUserName(username); 
		if(!admin.getPassword().equals(oldPassword)) throw new UserException("����ľ����벻��ȷ!");
		
		try { 
			adminService.changePassword(username,newPassword);
			request.setAttribute("message", java.net.URLEncoder.encode(
					"�����޸ĳɹ�!", "GBK"));
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", java.net.URLEncoder
					.encode("�����޸�ʧ��!","GBK"));
			return "error";
		}   
	}
	
	
	
	
}
