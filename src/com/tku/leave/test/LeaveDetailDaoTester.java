package com.tku.leave.test;

import java.util.List;

import com.tku.leave.dao.impl.LeaveDetailDaoImpl;
import com.tku.leave.dao.inter.LeaveDetailDaoInter;
import com.tku.leave.domain.LeaveDetail;

public class LeaveDetailDaoTester {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeaveDetailDaoInter ddi =new LeaveDetailDaoImpl();
		 List<LeaveDetail> leavedetaillist = ddi.getLeaveDetail(425);
			 for(LeaveDetail l:leavedetaillist){
			 System.out.println(l);
			 }

	}

}
