package com.tku.leave.test;

import java.util.List;

import com.tku.leave.dao.impl.LeaveAdyearDaoImpl;
import com.tku.leave.dao.inter.LeaveAdyearDaoInter;
import com.tku.leave.domain.LeaveAdyear;

public class LeaveAdyearDaoTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeaveAdyearDaoInter lai =new LeaveAdyearDaoImpl();
		//取得所有 年度ID
		List<LeaveAdyear> leaveadyearidlist = null;
		leaveadyearidlist= lai.getAllAdyearId();
		System.out.println(leaveadyearidlist);
		
	}

}
