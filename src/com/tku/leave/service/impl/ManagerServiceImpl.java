package com.tku.leave.service.impl;

import java.util.List;

import com.tku.leave.dao.impl.LeaveAdyearDaoImpl;
import com.tku.leave.dao.impl.LeaveClassDaoImpl;
import com.tku.leave.dao.impl.LeaveUserDaoImpl;
import com.tku.leave.dao.impl.LeaveUserMaxDaoImpl;
import com.tku.leave.dao.inter.LeaveAdyearDaoInter;
import com.tku.leave.dao.inter.LeaveClassDaoInter;
import com.tku.leave.dao.inter.LeaveUserDaoInter;
import com.tku.leave.dao.inter.LeaveUserMaxDaoInter;
import com.tku.leave.domain.LeaveAdyear;
import com.tku.leave.domain.LeaveClass;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.domain.LeaveUserMax;
import com.tku.leave.service.inter.ManagerServiceInter;

public class ManagerServiceImpl implements ManagerServiceInter {
	
	LeaveClassDaoInter leaveclassDao = new LeaveClassDaoImpl();
	LeaveAdyearDaoInter leaveadyearDao = new LeaveAdyearDaoImpl();
	LeaveUserDaoInter leaveuserDao = new LeaveUserDaoImpl();
	LeaveUserMaxDaoInter leaveusermaxDao = new LeaveUserMaxDaoImpl();
	
	//列出所有假別資料
	public List<LeaveClass> getAll() {
		
		return leaveclassDao.getAll();
	}

	//更新假別資料
	public void updateClassName(LeaveClass leaveclass) {
		// TODO Auto-generated method stub
		leaveclassDao.updateClassName(leaveclass);
	}

	//新增假別名稱
	public void addClassName(LeaveClass leaveclass) {
		// TODO Auto-generated method stub
		leaveclassDao.addClassName(leaveclass);
		
	}

	// 根據假別名稱查詢 classID
	public long getClassIdByClassName(String className) {
		// TODO Auto-generated method stub
		return leaveclassDao.getClassIdByClassName(className);
	}

	@Override
	//檢查假別名稱
	public boolean checkClassName(String leaveclassname) {
		// TODO Auto-generated method stub
		if(leaveclassDao.checkClassName(leaveclassname)==null){
			return true;//true-->無資料,可新增
		}else{
			return false;//false-->資料重複,不可新增
		}
	}
	
	@Override
	//檢查年度名稱
	public boolean checkAdyearName(String adYearname) {
		// TODO Auto-generated method stub
				if(leaveadyearDao.checkAdyearName(adYearname)==null){
					return true;//true-->無資料,可新增
				}else{
					return false;//false-->資料重複,不可新增
				}
	}

	@Override
	//列出所有年度詳細資料
	public List<LeaveAdyear> getAllAdyear() {
		// TODO Auto-generated method stub
		return leaveadyearDao.getAllAdyear();
	}

	@Override
	// 更新假別年度名稱
	public void updateAdyear(LeaveAdyear leaveadyear) {
		// TODO Auto-generated method stub
		leaveadyearDao.updateAdyear(leaveadyear);
		
	}

	@Override
	// 新增年度
	public void addAdyear(LeaveAdyear leaveadyear) {
		// TODO Auto-generated method stub
		leaveadyearDao.addAdyear(leaveadyear);
		
	}

	@Override
	public List<LeaveUser> getAllUserId() {
		// TODO Auto-generated method stub
		return leaveuserDao.getAllUserId();
	}

	@Override
	// 初始化未新增的使用者年度假別最大時數為50
	public void initialUserMax(LeaveUserMax leaveusermax) {
		// TODO Auto-generated method stub
		leaveusermaxDao.initialUserMax(leaveusermax);
	}

	@Override
	//查詢該使用者是否有年度最大假別時數資料
	public boolean isExistByCompositeKey(long userId, long adyearId,
			long ClassId) {
		// TODO Auto-generated method stub
		if (leaveusermaxDao.getLeaveUserMaxByCompositeKey(userId,adyearId,ClassId) != null) {//如果有回傳true
			return true;
		} else {//如果沒有回傳false
			return false;
		}
	}

	@Override
	// 用姓名查詢最大時數資訊
	public List<LeaveUserMax> getleaveUserMaxByName(String name) {
		// TODO Auto-generated method stub
		return leaveusermaxDao.getleaveUserMaxByName(name);
	}

	@Override
	// 用年度&假別查詢最大時數資訊
	public List<LeaveUserMax> getleaveUserMaxByAdyearClassName(String adyear,
			String className) {
		// TODO Auto-generated method stub
		return leaveusermaxDao.getleaveUserMaxByAdyearClassName(adyear, className);
	}

	@Override
	//使用複合主建更新時數資料
	public void updateLeaveUserMax(LeaveUserMax leaveusermax) {
		// TODO Auto-generated method stub
		leaveusermaxDao.updateLeaveUserMax(leaveusermax);
	}

	@Override
	// 使用姓名和假別稱查詢 請過當年度假別總時數
	public float getSumLeaveTimeByNameClassName(String name, String className) {
		// TODO Auto-generated method stub
		return leaveusermaxDao.getSumLeaveTimeByNameClassName(name, className);
	}

	@Override
	// 使用姓名和假別稱查詢 該年度假准許的時數
	public float getApproveMaxByNameClassName(String name, String className) {
		// TODO Auto-generated method stub
		return leaveusermaxDao.getApproveMaxByNameClassName(name, className);
	}

}
