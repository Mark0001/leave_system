package com.tku.leave.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.tku.leave.domain.LeaveAdyear;

public interface LeaveAdyearDaoInter {
	//根據AdYearId查詢 年度名稱
	LeaveAdyear getAdYearByAdYearId(Serializable adyearId);
	
	//根據假別名稱查詢 AdYearId
	LeaveAdyear getAdYearIdByAdYear(String adYear);
	
	//新增假別名稱
	void addAdyear(LeaveAdyear leaveadyear);
	
	//修改假別名稱
	void updateAdyear(LeaveAdyear leaveadyear);
	
	//刪除假別
	void deleteAdyear(LeaveAdyear leaveadyear);
	
	//獲取所有假別詳細資料
	List<LeaveAdyear> getAllAdyear();
	
	//取得所有 年度ID
	List<LeaveAdyear> getAllAdyearId();
	
	// 檢查年度名稱
	String checkAdyearName(String adyearName);
	

}
