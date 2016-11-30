package com.tku.leave.service.inter;

import java.util.List;

import com.tku.leave.domain.LeaveAdyear;
import com.tku.leave.domain.LeaveClass;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.domain.LeaveUserMax;

public interface ManagerServiceInter {

	// 列出所有假別詳細資料
	List<LeaveClass> getAll();

	// 更新假別名稱
	void updateClassName(LeaveClass leaveclass);

	// 新增假別
	void addClassName(LeaveClass leaveclass);

	// 根據假別名稱查詢 classID
	long getClassIdByClassName(String className);

	// 檢查假別名稱
	boolean checkClassName(String leaveclassname);
	
	// 檢查年度名稱
	boolean checkAdyearName(String adYearname);

	// 列出所有年度詳細資料
	List<LeaveAdyear> getAllAdyear();

	// 更新假別年度名稱
	void updateAdyear(LeaveAdyear leaveadyear);

	// 新增年度
	void addAdyear(LeaveAdyear leaveadyear);

	// 取得所有使用者ID
	List<LeaveUser> getAllUserId();

	// 初始化未新增的使用者年度假別最大時數為50
	void initialUserMax(LeaveUserMax leaveusermax);

	// 查詢該使用者是否有年度最大假別時數資料
	boolean isExistByCompositeKey(long userId, long adyearId, long ClassId);

	// 用姓名查詢最大時數資訊
	List<LeaveUserMax> getleaveUserMaxByName(String name);

	// 用年度&假別查詢最大時數資訊
	List<LeaveUserMax> getleaveUserMaxByAdyearClassName(String adyear,
			String className);

	// 使用複合主建更新時數資料
	void updateLeaveUserMax(LeaveUserMax leaveusermax);

	// 使用姓名和假別稱查詢 請過當年度假別總時數
	float getSumLeaveTimeByNameClassName(String name, String className);

	// 使用姓名和假別稱查詢 該年度假准許的時數
	float getApproveMaxByNameClassName(String name, String className);
}
