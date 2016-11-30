package com.tku.leave.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tku.leave.dao.inter.LeaveMainDaoInter;
import com.tku.leave.dao.jdbc.DaoJDBC;
import com.tku.leave.domain.LeaveMain;
import com.tku.leave.exception.DataAccessException;


public class LeaveMainDaoImpl implements LeaveMainDaoInter {

	@Override
	//新增價單
	public void addLeaveMain(LeaveMain leavemain) {
		DaoJDBC.executeUpdate(
				"insert into leave_main values(seq_leave.nextval,sysdate,?,?)",
				new Object[] { 
						//seq_leave.nextval
						//leavemain.getMainId(),
						//new java.sql.Timestamp(leavemain.getApplytime().getTime()),
						leavemain.getApproveStatus(),
						leavemain.getUserId()
				
				
				});

	}

	@Override
	public List<LeaveMain> getApproveStatusById(Serializable userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	//用ID查詢,編號最大Main假單
	public long getFristMainById(long userId) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select  MAX(mainid)  from leave_main where userid=?",
				new Object[] {userId});

		LeaveMain Leavemain = null;

		try {
			if (rset.next()) {
				Leavemain = new LeaveMain();
				// 数据封装
				Leavemain.setMainId(rset.getLong("MAX(mainid)")); //記下來
				//leaveclass.setClassName(rset.getString("className"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return Leavemain.getMainId();
	}

	@Override
	//查出所有未審假單
	public List<LeaveMain> getAllNotCheck() {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select b.name,a.userId,mainId,applytime,approveStatus  from  leave_main  A, leave_user  B where approvestatus = 0 and A.userid=B.userid order by applyTime desc",//依時間排序
				new Object[] {});

		
		List<LeaveMain> leavemainlist = null;
		try {
			leavemainlist = new ArrayList<LeaveMain>();
			while (rset.next()) {
				LeaveMain leavemain = new LeaveMain();
				// 數據封裝
				leavemain.setName(rset.getString("name"));
				leavemain.setUserId(rset.getLong("userId"));
				leavemain.setMainId(rset.getLong("mainId"));
				leavemain.setApplytime(rset.getDate("applyTime"));
				//leavemain.setApplytime(rset.getTime("applyTime"));

				leavemain.setApproveStatus(rset.getString("approveStatus"));
				leavemainlist.add(leavemain);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leavemainlist;
		
	}

	@Override
	//傳回未審假單張數
	public int returnNotCheckNumber() {
		ResultSet rset = DaoJDBC.executeQuery(
				"select count(*) from leave_main where approvestatus='0'",//依時間排序
				new Object[] {});
		int number = 0;
		try {
			if (rset.next()) {
				// 数据封装
			 number = rset.getInt("count(*)");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return number;
	}

	@Override
	//主管審核假單 -- 0 未審 1 通過 2不通過 3刪除
	public void checkLeave(LeaveMain leavemain) {
		// TODO Auto-generated method stub
		DaoJDBC
		.executeUpdate(
				"update leave_main set MainId = ?,approvestatus = ?,Applytime=?, userid=? where MainId = ?",
				//LeaveMain leavemain = new LeaveMain();
				new Object[] {
					
						leavemain.getMainId(),//注意
						leavemain.getApproveStatus(),
						leavemain.getApplytime(),
						leavemain.getUserId(),
						leavemain.getMainId()//注意
						});
		
	}

	@Override
	//用MainId 查詢 Main假單
	public LeaveMain getLeaveByMainId(long mainId) {
		ResultSet rset = DaoJDBC
				.executeQuery(
						"select mainid , applytime ,approvestatus,userid from leave_main where mainId=?",
						new Object[] { mainId });
		LeaveMain leavemain = null;
		try {
			if (rset.next()) {
				leavemain = new LeaveMain();
				leavemain.setMainId(rset.getLong("mainId"));
				leavemain.setApplytime(rset.getDate("applytime"));		
				leavemain.setApproveStatus(rset.getString("approvestatus"));
				leavemain.setUserId(rset.getLong("userid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC
					.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leavemain;
	}

	@Override
	//用userId 查詢未通過Main假單
	public List<LeaveMain> getNotApproveByUserId(long userId) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select mainId, applytime,approveStatus,userId from leave_main where userId =? and approveStatus='2'",//依時間排序
				new Object[] {userId});

		
		List<LeaveMain> leavemainlist = null;
		try {
			leavemainlist = new ArrayList<LeaveMain>();
			while (rset.next()) {
				LeaveMain leavemain = new LeaveMain();
				// 數據封裝
				leavemain.setMainId(rset.getLong("mainId"));
				leavemain.setApplytime(rset.getDate("applyTime"));
				leavemain.setUserId(rset.getLong("userId"));
				leavemain.setApproveStatus(rset.getString("approveStatus"));
				leavemainlist.add(leavemain);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leavemainlist;
		
	}

	@Override
	//用name,adyear查詢假單
	public List<LeaveMain> getLeaveMainByNameAdyear(String name, String adyear) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select a.mainid,a.applytime,a.approvestatus,a.userid ,b.name from leave_main a ,leave_user b where a.userid=b.userid and approvestatus='1'  and to_char(a.applytime, 'yyyy') LIKE ? and b.name LIKE ? order by a.userid desc",
				new Object[] {"%"+adyear+"%","%"+name+"%"});

		
		List<LeaveMain> leavemainlist = null;
		try {
			leavemainlist = new ArrayList<LeaveMain>();
			while (rset.next()) {
				LeaveMain leavemain = new LeaveMain();
				// 數據封裝
				leavemain.setMainId(rset.getLong("mainId"));
				leavemain.setApplytime(rset.getDate("applyTime"));
				leavemain.setUserId(rset.getLong("userId"));
				leavemain.setApproveStatus(rset.getString("approveStatus"));
				leavemain.setName(rset.getString("name"));
				leavemainlist.add(leavemain);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leavemainlist;
	}

	@Override
	//用MainId 刪除 Main假單
	public void deleteMainSheet(LeaveMain leavemain) {
		// TODO Auto-generated method stub
		DaoJDBC
		.executeUpdate(
				"DELETE FROM leave_main WHERE mainid=?",
				//LeaveMain leavemain = new LeaveMain();
				new Object[] {
						leavemain.getMainId(),//注意
						
						});
		
	}

	@Override
	//用使用者userId 傳回未通過假單數
	public int returnNoApproveNumber(long userId) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select  count(*) from leave_main where approvestatus='2' and userid= ?",//依時間排序
				new Object[] {userId});
		int number = 0;
		try {
			if (rset.next()) {
				// 数据封装
			 number = rset.getInt("count(*)");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return number;
	}



}
