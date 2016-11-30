package com.tku.leave.test;

import java.util.List;

import com.tku.leave.dao.impl.LeaveUserDaoImpl;

import com.tku.leave.dao.inter.LeaveUserDaoInter;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.utils.MailUtils;

public class LeaveUserDaoTester {

	public static void main(String[] args) {
		LeaveUserDaoInter udi = new LeaveUserDaoImpl();

		// userId查詢測試
		// LeaveUser user = udi.getUserByUserId("42");//有資料的 OK
		// LeaveUser user = udi.getUserByUserId("3");//無資料的 OK
		// System.out.println(user);

		// 使用UserId查詢,使用者名稱
		// String name = udi.getNameByUserId(41);
		// System.out.print(name);

		// 使用MainId 查使用者Mail
		// String mail = udi.getMailByMainId(408);
		// System.out.print(mail);
		// MailUtils.MailOk("a0934480869@gmail.com", "feedback");

		//取得所有UserId
//		List<LeaveUser> listuserid = null;
//		listuserid = udi.getAllUserId();
//		System.out.print(listuserid);
	}
}
