package com.tku.leave.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.tku.leave.domain.LeaveClass;

public interface LeaveClassDaoInter {
	// 根據clssId查詢 假別名稱和ClassId
	LeaveClass getClassNameByClassId(Serializable classId);

	// 根據假別名稱查詢 classID
	long getClassIdByClassName(String className);

	// 新增假別名稱
	void addClassName(LeaveClass leaveclass);

	// 修改假別名稱
	void updateClassName(LeaveClass leaveclass);

	// 刪除假別
	void deleteClass(LeaveClass leaveclass);

	// 獲取所有假別詳細資料
	List<LeaveClass> getAll();

	// 檢查假別名稱
	String checkClassName(String className);
	
	//取得所有假別Id
	List<LeaveClass> getAllClassId();

}
