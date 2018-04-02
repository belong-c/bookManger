package com.shuangyulin.service;

import javax.annotation.Resource;

 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shuangyulin.mapper.AdminMapper; 
import com.shuangyulin.po.Admin;

@Service @Transactional
public class AdminService {
	@Resource AdminMapper adminMapper;

	/*����ҵ���߼�������Ϣ�ֶ�*/
	private String errMessage;
	public String getErrMessage() { return this.errMessage; }
	
	/*��֤�û���¼*/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public boolean checkLogin(Admin admin) throws Exception { 
		Admin db_admin = (Admin) adminMapper.findAdminByUserName(admin.getUsername());
		if(db_admin == null) { 
			this.errMessage = " �˺Ų����� ";
			System.out.print(this.errMessage);
			return false;
		} else if( !db_admin.getPassword().equals(admin.getPassword())) {
			this.errMessage = " ���벻��ȷ! ";
			System.out.print(this.errMessage);
			return false;
		}
		
		return true;
	}
	

	/*�޸��û���¼����*/
	public void changePassword(String username, String newPassword) throws Exception {  
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(newPassword);
		adminMapper.changePassword(admin);  
	}
	
	/*�����û�����ȡ����Ա����*/
	public Admin findAdminByUserName(String username) throws Exception {
		Admin db_admin = null;
		db_admin = adminMapper.findAdminByUserName(username);
		return db_admin;
	}
}
