package com.tku.leave.dao.inter;

import java.util.List;

import com.tku.leave.domain.LeaveDetail;

public interface LeaveDetailDaoInter {

	// 新增價單Detail
	void addLeaveDetail(LeaveDetail leavedetail);

	// 用mainId查詢假單Detail
	List<LeaveDetail> getLeaveDetail(long mainId);


}
