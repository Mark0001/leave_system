package com.tku.leave.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tku.leave.dao.inter.LeaveAdyearDaoInter;
import com.tku.leave.dao.jdbc.DaoJDBC;
import com.tku.leave.domain.LeaveAdyear;
import com.tku.leave.exception.DataAccessException;

public class LeaveAdyearDaoImpl implements LeaveAdyearDaoInter {
	// 根據clssID查詢 假別名稱
		public LeaveAdyear getAdYearByAdYearId(Serializable adyearId) {
			// TODO Auto-generated method stub
			ResultSet rset = DaoJDBC.executeQuery(
					"select adyearid,adyear from leave_adyear where adyearid=?",
					new Object[] { adyearId });
			LeaveAdyear leaveadyear = null;
			try {
				if (rset.next()) {
					leaveadyear = new LeaveAdyear();
					// 数据封装
					leaveadyear.setAdyearId(rset.getLong("adyearId"));
					leaveadyear.setAdyear(rset.getString("adyear"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
			}
			return leaveadyear;
		}
		// 根據假別名稱查詢 classID
		public LeaveAdyear getAdYearIdByAdYear(String adyear) {
			// TODO Auto-generated method stub
			ResultSet rset = DaoJDBC.executeQuery(
					"select adyearid,adyear from leave_adyear where adyear=?",
					new Object[] { adyear });
			LeaveAdyear leaveadyear = null;
			try {
				if (rset.next()) {
					leaveadyear = new LeaveAdyear();
					// 数据封装
					leaveadyear.setAdyearId(rset.getLong("adyearId"));
					leaveadyear.setAdyear(rset.getString("adyear"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
			}
			return leaveadyear;
		}
		// 新增假別名稱
		public void addAdyear(LeaveAdyear leaveadyear) {
			// TODO Auto-generated method stub
			DaoJDBC.executeUpdate(
					"insert into leave_adyear values(seq_leave.nextval,?)",
					new Object[] { leaveadyear.getAdyear() });
			 System.out.println("year測試：" +leaveadyear.getAdyear());
		}
		//更新假別名稱
		public void updateAdyear(LeaveAdyear leaveadyear) {
			// TODO Auto-generated method stub
			DaoJDBC.executeUpdate(
					"update leave_adyear set adyear=? where adyearId=?",
					new Object[] { 
							leaveadyear.getAdyear(),
							leaveadyear.getAdyearId()
					});
		}
		public void deleteAdyear(LeaveAdyear leaveadyear) {
			// TODO Auto-generated method stub
			
		}
		//列出所有 假別名稱
		public List<LeaveAdyear> getAllAdyear() {
			// TODO Auto-generated method stub
			ResultSet rset = DaoJDBC.executeQuery(
					"select adyearid,adyear from leave_adyear",
					new Object[] {});
			
			List<LeaveAdyear> leaveadyearlist = null;
			try {
				leaveadyearlist = new ArrayList<LeaveAdyear>();
				while (rset.next()) {
					LeaveAdyear leaveadyear = new LeaveAdyear();
					// 数据封装
					leaveadyear.setAdyearId(rset.getLong("adyearId"));
					leaveadyear.setAdyear(rset.getString("adyear"));
					
					leaveadyearlist.add(leaveadyear);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
			}
			return leaveadyearlist;
		}
		
		@Override
		//取得所有 年度ID
		public List<LeaveAdyear> getAllAdyearId() {
			// TODO Auto-generated method stub
			ResultSet rset = DaoJDBC.executeQuery(
					"select adyearid from leave_adyear",
					new Object[] {});
			
			List<LeaveAdyear> leaveadyearidlist = null;
			try {
				leaveadyearidlist = new ArrayList<LeaveAdyear>();
				while (rset.next()) {
					LeaveAdyear leaveadyear = new LeaveAdyear();
					// 数据封装
					leaveadyear.setAdyearId(rset.getLong("adyearId"));
					//leaveadyear.setAdyear(rset.getString("adyear"));
					
					leaveadyearidlist.add(leaveadyear);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
			}
			return leaveadyearidlist;
		}
		@Override
		//檢查年度名稱
		public String checkAdyearName(String adyearName) {
			// TODO Auto-generated method stub
			ResultSet rset = DaoJDBC.executeQuery(
					"select adyear from leave_adyear where adyear=?",
					new Object[] { adyearName });
			String checkadyearname = null;

			try {
				if (rset.next()) {

					checkadyearname = rset.getString("adyear");

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
			}

			return checkadyearname;
		}

	}