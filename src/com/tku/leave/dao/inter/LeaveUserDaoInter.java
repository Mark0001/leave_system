package com.tku.leave.dao.inter;

import com.tku.leave.domain.LeaveUser;
import java.io.Serializable;
import java.util.List;

public interface LeaveUserDaoInter {

	// 使用UserID查詢所有User資訊
	LeaveUser getUserByUserId(Serializable userId);

	// 使用帳號得到 user資訊
	LeaveUser getUserByAccount(String account);

	// 使用者姓名
	LeaveUser getUserByName(String name);

	// 使用UserId查詢,使用者名稱
	String getNameByUserId(long userId);

	// 用 MainId 找使用者mail
	String getMailByMainId(long mainid);
	
	//取得所有使用者ID
	List<LeaveUser> getAllUserId();
}
