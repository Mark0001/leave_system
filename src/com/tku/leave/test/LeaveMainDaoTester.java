package com.tku.leave.test;

import java.util.List;

import com.tku.leave.dao.impl.LeaveMainDaoImpl;
import com.tku.leave.dao.inter.LeaveMainDaoInter;
import com.tku.leave.domain.LeaveMain;

public class LeaveMainDaoTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeaveMainDaoInter mdi = new LeaveMainDaoImpl();
		
		////新增價單Main
		// LeaveMain leavemain=new LeaveMain();
		// leavemain.setApproveStatus("0");
		// leavemain.setUserId(41);
		// mdi.addLeaveMain(leavemain);
		
		// 用UserId取出最大MainId
		// long leavemain = mdi.getFristMainById(41);
		// System.out.println(leavemain);

		// 取出所有未審核假單
		// List<LeaveMain> leavemainlist = mdi.getAllNotCheck();
		// for(LeaveMain l:leavemainlist){
		// System.out.println(l);
		// }

		// 傳回未審假單張數
		// long number = mdi.returnNotCheckNumber();
		// System.out.println(number);

		// 主管審核假單 -- 0 未審 1 通過 2不通過
		// LeaveMain leavemain = mdi.getLeaveByMainId(433);
		// mdi.checkLeave(leavemain);

		// 用userId 查詢未通過Main假單
		// List<LeaveMain> leavemainlist = mdi.getNotApproveByUserId(41);
		// for(LeaveMain l:leavemainlist){
		// System.out.println(l);
		// }

		// 用name,adyear查詢假單
		// List<LeaveMain> leavemainlist = mdi.getLeaveMainByNameAdyear("施智升",
		// "2016");
		// for(LeaveMain l:leavemainlist){
		// System.out.println(l);
		// }
		//用LeaveMain 刪除 Main假單
		// LeaveMain leavemain=new LeaveMain();
		// leavemain.setMainId(621);
		// mdi.deleteMainSheet(leavemain);
		
		//用使用者userId 傳回未通過假單數
		// int number = mdi.returnNoApproveNumber(43);
		// System.out.println(number);

	}

}
