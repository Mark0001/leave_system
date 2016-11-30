package com.tku.leave.test;

import java.util.List;

import com.tku.leave.dao.impl.LeaveUserMaxDaoImpl;
import com.tku.leave.dao.inter.LeaveUserMaxDaoInter;
import com.tku.leave.domain.LeaveUserMax;

public class LeaveUserMaxDaoTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeaveUserMaxDaoInter xdi = new LeaveUserMaxDaoImpl();
		// 新增年度使用者假別最大時數
		// LeaveUserMax leaveusermax = new LeaveUserMax();
		// leaveusermax.setAdYearId(465);
		// leaveusermax.setClassId(47);
		// leaveusermax.setUserId(41);
		// xdi.initialUserMax(leaveusermax);
		// 用複合建查詢資訊
		// LeaveUserMax leaveusermax = new LeaveUserMax();
		// leaveusermax = xdi.getLeaveUserMaxByCompositeKey(43,465, 81);
		// System.out.print(leaveusermax);
		// 用姓名查詢最大時數資訊
		// List<LeaveUserMax> leaveusermaxlist=null;
		// leaveusermaxlist=xdi.getleaveUserMaxByName("葉子其");
		// for(LeaveUserMax l:leaveusermaxlist){
		// System.out.println(l);
		// }
		// 用年度&假別查詢最大時數資訊
		// List<LeaveUserMax> leaveusermaxlist = null;
		// leaveusermaxlist = xdi.getleaveUserMaxByAdyearClassName("2016",
		// "產假");
		// for (LeaveUserMax l : leaveusermaxlist) {
		// System.out.println(l);
		// }
		// 使用複合主建更新時數資料
		// LeaveUserMax leaveusermax = new LeaveUserMax();
		// leaveusermax.setUserId(41);
		// leaveusermax.setAdYearId(465);
		// leaveusermax.setClassId(47);
		// leaveusermax.setApproveMax("70");
		// xdi.updateLeaveUserMax(leaveusermax);
		// 使用姓名和假別稱查詢 請過當年度假別總時數
		// int SumLeaveTime=0;
		// int SumLeaveTime = xdi.getSumLeaveTimeByNameClassName("葉子其","產假" );
		// System.out.print(SumLeaveTime);
		// 使用姓名和假別稱查詢 該年度假准許的時數
		// int ApproveMax=xdi.getApproveMaxByNameClassName("葉子其", "病假");
		// System.out.print(ApproveMax);

	}
}
