package com.tku.leave.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tku.leave.dao.inter.LeaveUserMaxDaoInter;
import com.tku.leave.dao.jdbc.DaoJDBC;
import com.tku.leave.domain.LeaveUserMax;
import com.tku.leave.exception.DataAccessException;


public class LeaveUserMaxDaoImpl implements LeaveUserMaxDaoInter {

	@Override
	// 新增使用者年度假別最大時數
	public void initialUserMax(LeaveUserMax leaveusermax) {
		// TODO Auto-generated method stub
		DaoJDBC.executeUpdate("insert into leave_user_max values(?,?,?,'50')",
				new Object[] { 
						leaveusermax.getAdYearId(), 
						leaveusermax.getUserId(),
						leaveusermax.getClassId(),
					
						});
	}

	@Override
	//用複合建查詢資訊
	public LeaveUserMax getLeaveUserMaxByCompositeKey(long userId,
			long adyearId, long ClassId) {
		ResultSet rset = DaoJDBC
				.executeQuery(
						"select userid,adyearid,classid,approvemax from leave_user_max where userid=? and adyearid=? and classid=?",
						new Object[] { userId,adyearId,ClassId });

		LeaveUserMax leaveusermax = null;

		try {
			if (rset.next()) {
				leaveusermax = new LeaveUserMax();
				// 数据封装
				leaveusermax.setUserId(rset.getLong("userId"));
				leaveusermax.setAdYearId(rset.getLong("adyearId"));
				leaveusermax.setClassId(rset.getLong("classid"));
				leaveusermax.setApproveMax(rset.getString("approvemax"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC
					.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leaveusermax;
	}

	@Override
	//用姓名查詢最大時數資訊
	public List<LeaveUserMax> getleaveUserMaxByName(String name) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC
				.executeQuery(
						"select b.name,b.userid,c.adyear,c.adyearid,d.classname,d.classid,a.approvemax from leave_user_max a ,leave_user b,leave_adyear c, leave_class d where a.userid=b.userid and a.adyearid=c.adyearid and a.classid=d.classid and b.name LIKE ? order by c.adyearid,b.name",
						new Object[] { "%"+name+"%" });

		List<LeaveUserMax> leaveusermaxlist = null;

		try {
			leaveusermaxlist = new ArrayList<LeaveUserMax>();
			while (rset.next()) {
				LeaveUserMax leaveusermax = new LeaveUserMax();
				//leaveusermax = new LeaveUserMax();
				// 数据封装
				leaveusermax.setName(rset.getString("name"));
				leaveusermax.setAdyear(rset.getString("adyear"));
				leaveusermax.setClassName(rset.getString("classname"));
				leaveusermax.setUserId(rset.getLong("userid"));
				leaveusermax.setAdYearId(rset.getLong("adyearid"));
				leaveusermax.setClassId(rset.getLong("classid"));
				leaveusermax.setApproveMax(rset.getString("approvemax"));
				leaveusermaxlist.add(leaveusermax);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC
					.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leaveusermaxlist;
	}

	@Override
	//用年度&假別查詢最大時數資訊
	public List<LeaveUserMax> getleaveUserMaxByAdyearClassName(String adyear,
			String className) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC
				.executeQuery(
						"select b.name,b.userid,c.adyear,c.adyearid,d.classname,d.classid,a.approveMax from leave_user_max a ,leave_user b,leave_adyear c, leave_class d where a.userid=b.userid and a.adyearid=c.adyearid and a.classid=d.classid and c.adyear LIKE ? and d.classname LIKE ? order by a.userid desc",
						new Object[] { "%"+adyear+"%","%"+className+"%" });

		List<LeaveUserMax> leaveusermaxlist = null;

		try {
			leaveusermaxlist = new ArrayList<LeaveUserMax>();
			while (rset.next()) {
				LeaveUserMax leaveusermax = new LeaveUserMax();
				//leaveusermax = new LeaveUserMax();
				// 数据封装
				leaveusermax.setName(rset.getString("name"));
				leaveusermax.setAdyear(rset.getString("adyear"));
				leaveusermax.setClassName(rset.getString("classname"));
				leaveusermax.setUserId(rset.getLong("userid"));
				leaveusermax.setAdYearId(rset.getLong("adyearid"));
				leaveusermax.setClassId(rset.getLong("classid"));
				leaveusermax.setApproveMax(rset.getString("approveMax"));
				leaveusermaxlist.add(leaveusermax);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC
					.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leaveusermaxlist;
	}

	@Override
	//使用複合主建更新時數資料
	public void updateLeaveUserMax(LeaveUserMax leaveusermax) {
		// TODO Auto-generated method stub
		DaoJDBC
		.executeUpdate(
				"update leave_user_max set approvemax=? where userid = ? and adyearid = ? and classid= ?",
				
				new Object[] {
						
						leaveusermax.getApproveMax(),
						leaveusermax.getUserId(),
						leaveusermax.getAdYearId(),
						leaveusermax.getClassId(),
						
						});
	}

	@Override
	//使用姓名和假別稱查詢 請過當年度假別總時數
	public float getSumLeaveTimeByNameClassName(String name, String className) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select sum(c.leavetime) from leave_user a,leave_main b,leave_detail c,leave_class d,leave_user_max e,leave_adyear f where a.userid=b.userid and b.mainid=c.mainid and c.classid=d.classid and a.userid=e.userid  and d.classid=e.classid and f.adyearid=e.adyearid and b.approvestatus='1'and  to_char (sysdate, 'yyyy')=f.adyear and to_char (b.applytime, 'yyyy')=to_char (sysdate, 'yyyy')  and a.name=? and d.classname=? group by d.classname",
				new Object[] { name,className });
	
		int SumLeaveTime = 0;
		try {
			if (rset.next()) {
				
				SumLeaveTime = rset.getInt("sum(c.leavetime)");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return SumLeaveTime;
	}

	@Override
	//使用姓名和假別稱查詢 該年度假准許的時數
	public float getApproveMaxByNameClassName(String name, String className) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select a.approvemax from leave_user_max a,leave_user b,leave_class c,leave_adyear d where a.userid=b.userid and a.classid=c.classid and a.adyearid=d.adyearid and to_char(sysdate, 'yyyy')=d.adyear  and b.name=? and c.classname=? ",
				new Object[] { name,className });
	
		int ApproveMax = 0;
		try {
			if (rset.next()) {
				
				ApproveMax = rset.getInt("approvemax");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return ApproveMax;
	}

}
