package com.shuangyulin.po;

import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;

 
public class Admin {
	/*����Ա�û���*/
	@NotEmpty(message="�û�������Ϊ��")  
	private String username;
	/*��½����*/
	@NotEmpty(message="��½���벻��Ϊ��") 
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
