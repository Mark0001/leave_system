package com.tku.leave.service.inter;

import java.util.List;

import com.tku.leave.domain.LeaveDetail;
import com.tku.leave.domain.LeaveMain;

public interface AdministratorServiceInter {
	// 獲取所有假別詳細資料
	List<LeaveMain> getAllNotCheck();

	// 使用UserId查詢,使用者名稱
	String getNameByUserId(long userId);

	// 傳回未審假單張數
	int returnNotCheckNumber();

	// 用mainId查詢假單Detail
	List<LeaveDetail> getLeaveDetail(long mainId);
	
	//主管審核假單 -- 0 未審 1 通過 2不通過 3刪除
	void checkLeave(LeaveMain leavemain);
	
	//用MainId 查詢 Main假單
	LeaveMain getLeaveByMainId(long mainId);

	//主管用MainId查詢寄信
	String getMailByMainId(long mainid);
}
