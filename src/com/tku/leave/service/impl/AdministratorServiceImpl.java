package com.tku.leave.service.impl;

import java.util.List;

import com.tku.leave.dao.impl.LeaveDetailDaoImpl;
import com.tku.leave.dao.impl.LeaveMainDaoImpl;
import com.tku.leave.dao.impl.LeaveUserDaoImpl;
import com.tku.leave.dao.inter.LeaveDetailDaoInter;
import com.tku.leave.dao.inter.LeaveMainDaoInter;
import com.tku.leave.dao.inter.LeaveUserDaoInter;
import com.tku.leave.domain.LeaveDetail;
import com.tku.leave.domain.LeaveMain;
import com.tku.leave.service.inter.AdministratorServiceInter;

public class AdministratorServiceImpl implements AdministratorServiceInter {
	LeaveMainDaoInter leavemainDao = new LeaveMainDaoImpl(); 
	LeaveUserDaoInter leaveuserDao = new LeaveUserDaoImpl();
	LeaveDetailDaoInter leavedetailDao = new LeaveDetailDaoImpl(); 
	
	
	@Override
	//查出所有未審假單
	public List<LeaveMain> getAllNotCheck() {
		// TODO Auto-generated method stub
		return leavemainDao.getAllNotCheck();
	}

	@Override
	//使用UserId查詢,使用者名稱
	public String getNameByUserId(long userId) {
		// TODO Auto-generated method stub
		return leaveuserDao.getNameByUserId(userId);
	}

	@Override
	//傳回未審假單張數
	public int returnNotCheckNumber() {
		// TODO Auto-generated method stub
		return leavemainDao.returnNotCheckNumber();
	}

	@Override
	// 用mainId查詢假單Detail
	public List<LeaveDetail> getLeaveDetail(long mainId) {
		// TODO Auto-generated method stub
		return leavedetailDao.getLeaveDetail(mainId);
	}

	@Override
	//主管審核假單 -- 0 未審 1 通過 2不通過 3刪除
	public void checkLeave(LeaveMain leavemain) {
		// TODO Auto-generated method stub
		leavemainDao.checkLeave(leavemain);
		
	}

	@Override
	public LeaveMain getLeaveByMainId(long mainId) {
		// TODO Auto-generated method stub
		return leavemainDao.getLeaveByMainId(mainId);
	}

	@Override
	public String getMailByMainId(long mainid) {
		// TODO Auto-generated method stub
		return leaveuserDao.getMailByMainId(mainid);
	}

}
