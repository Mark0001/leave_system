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
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.exception.ApplicationException;
import com.tku.leave.service.inter.UserServiceInter;

public class UserServiceImpl implements UserServiceInter {

	
	LeaveMainDaoInter leavemainDao = new LeaveMainDaoImpl();
	LeaveUserDaoInter leaveuserDao = new LeaveUserDaoImpl();
	LeaveDetailDaoInter leavedetailDao = new LeaveDetailDaoImpl();
	
	@Override
	public LeaveUser checkerUser(String account, String password) {
		// TODO 身分檢驗
		LeaveUser user = leaveuserDao.getUserByAccount(account);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				throw new ApplicationException("您輸入的密碼錯誤,請重新輸入");
			}
		} else {
			throw new ApplicationException("帳號不存在");
		}

	}

	@Override
	public void addLeaveMain(LeaveMain leavemain) {
		// TODO Auto-generated method stub
		leavemainDao.addLeaveMain(leavemain);
	}

	@Override
	public void addLeaveDetail(LeaveDetail leavedetail) {
		// TODO Auto-generated method stub
		leavedetailDao.addLeaveDetail(leavedetail);
	}

	@Override
	//用ID查詢,編號最大Main假單
	public long getFristMainById(long userId) {
		
		return leavemainDao.getFristMainById(userId);
	}

	@Override
	//使用UserId查詢不通過假單
	public List<LeaveMain> getNotApproveByUserId(long userId) {
		// TODO Auto-generated method stub
		return leavemainDao.getNotApproveByUserId(userId);
	}

	@Override
	public List<LeaveMain> getLeaveMainByNameAdyear(String name, String adyear) {
		// TODO Auto-generated method stub
		return leavemainDao.getLeaveMainByNameAdyear(name, adyear);
	}

	@Override
	public void deleteMainSheet(LeaveMain leavemain) {
		// TODO Auto-generated method stub
		leavemainDao.deleteMainSheet(leavemain);
	}

	@Override
	// 用使用者userId 傳回未通過假單數
	public int returnNoApproveNumber(long userId) {
		// TODO Auto-generated method stub
		return leavemainDao.returnNoApproveNumber(userId);
	}



}
