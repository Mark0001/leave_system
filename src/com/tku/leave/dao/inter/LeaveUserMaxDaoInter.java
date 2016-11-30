package com.tku.leave.dao.inter;

import java.util.List;

import com.tku.leave.domain.LeaveUserMax;



public interface LeaveUserMaxDaoInter {
	//新增使用者年度假別最大時數
	void initialUserMax(LeaveUserMax leaveusermax);
	
	//用複合建查詢資訊
	LeaveUserMax getLeaveUserMaxByCompositeKey(long userId,long adyearId,long ClassId);
	
	//用姓名查詢最大時數資訊
	List<LeaveUserMax> getleaveUserMaxByName(String name);
	
	//用年度&假別查詢最大時數資訊
	List<LeaveUserMax> getleaveUserMaxByAdyearClassName(String adyear,String className);
	
	//使用複合主建更新時數資料
	void updateLeaveUserMax(LeaveUserMax leaveusermax);
	
	//使用姓名和假別稱查詢 請過當年度假別總時數
	float getSumLeaveTimeByNameClassName(String name,String className);
	
	//使用姓名和假別稱查詢 該年度假准許的時數
	float getApproveMaxByNameClassName(String name,String className);
}
