package com.tku.leave.test;

import java.util.List;

import com.tku.leave.dao.impl.LeaveClassDaoImpl;
import com.tku.leave.dao.inter.LeaveClassDaoInter;
import com.tku.leave.domain.LeaveClass;

public class LeaveClassDaoTeaster {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeaveClassDaoInter cdi = new LeaveClassDaoImpl();

		// 根據clssID查詢 假別名稱測試測試
//		 LeaveClass leaveclass = cdi.getClassNameByClassId("47");//有資料 OK
//	//	 LeaveClass leaveclass = cdi.getClassNameByClassId("1");//無資料 OK
//		 System.out.println(leaveclass);

		// 根據假別名稱查詢 clssID測試測試
//		 long leaveclass = cdi.getClassIdByClassName("事假");//有資料 OK
		 //LeaveClass leaveclass = cdi.getClassIdByClassName("放假");//無資料 OK
//		 System.out.println(leaveclass);

		 //新增假別名稱
//		 LeaveClass leaveclass =new LeaveClass();
//		 leaveclass.setClassName("哈囉"); //新增成功
//		 cdi.addClassName(leaveclass);

		// 修改假別名稱
		// LeaveClass leaveclass = cdi.getClassNameByClassId("61");
		// //先把要改的資料ID取出
		// System.out.println(leaveclass);//查看
		// leaveclass.setClassName("病假");//設定修改名稱
		// cdi.updateClassName(leaveclass);//修改
		// System.out.println(leaveclass);// OK~~

		// 列出所有假別 
		// List<LeaveClass> leaveclasslist = cdi.getAll();
		// for (LeaveClass l : leaveclasslist) {
		// System.out.println(l);
		// }
		
		
		
		
		
	}

}
