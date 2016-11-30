package com.tku.leave.dao.inter;

import java.io.Serializable;
import java.util.List;


import com.tku.leave.domain.LeaveMain;


public interface LeaveMainDaoInter {
	//新增價單Main
	void addLeaveMain(LeaveMain leavemain);
	
	//用ID查詢,是否准假
	List<LeaveMain> getApproveStatusById(Serializable userId);
	
	//用ID查詢,編號最大Main假單
	long getFristMainById(long userId);
	
	//查出所有未審假單
	List<LeaveMain> getAllNotCheck();
	
	//傳回未審假單張數
	int returnNotCheckNumber();
	
	//主管審核假單 -- 0 未審 1 通過 2不通過 3刪除
	void checkLeave(LeaveMain leavemain);
	
	//用MainId 查詢 Main假單
	LeaveMain getLeaveByMainId(long mainId);
	
	//用userId 查詢未通過Main假單
	List<LeaveMain> getNotApproveByUserId(long userId);
	
	//用name,adyear查詢假單
	List<LeaveMain> getLeaveMainByNameAdyear(String name ,String adyear);
	
	//用LeaveMain 刪除 Main假單
	void deleteMainSheet(LeaveMain leavemain);
	
	//用使用者userId 傳回未通過假單數
	int returnNoApproveNumber(long userId);
	
	
	
	

}
