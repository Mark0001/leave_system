package com.tku.leave.service.inter;

import java.util.List;

import com.tku.leave.domain.LeaveDetail;
import com.tku.leave.domain.LeaveMain;
import com.tku.leave.domain.LeaveUser;

public interface UserServiceInter {

	// 身分檢驗
	LeaveUser checkerUser(String account, String password);

	// 新增假單Main
	void addLeaveMain(LeaveMain leavemain);

	// 新增假單Detail
	void addLeaveDetail(LeaveDetail leavedetail);

	// 用ID查詢,編號最大Main假單
	long getFristMainById(long userId);

	// 使用UserId查詢不通過假單
	List<LeaveMain> getNotApproveByUserId(long userId);

	// 用name,adyear查詢假單
	List<LeaveMain> getLeaveMainByNameAdyear(String name, String adyear);

	// 用LeaveMain 刪除 Main假單
	void deleteMainSheet(LeaveMain leavemain);

	// 用使用者userId 傳回未通過假單數
	int returnNoApproveNumber(long userId);
	
	
}
