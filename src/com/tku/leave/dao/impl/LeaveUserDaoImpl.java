package com.tku.leave.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tku.leave.dao.inter.LeaveUserDaoInter;
import com.tku.leave.dao.jdbc.DaoJDBC;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.exception.DataAccessException;

public class LeaveUserDaoImpl implements LeaveUserDaoInter {

	@Override
	//使用UserID查詢所有User資訊
	public LeaveUser getUserByUserId(Serializable userId) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC
				.executeQuery(
						"select userId,account,password,name,identity,mail from leave_user where userId=?",
						new Object[] { userId });

		LeaveUser leaveuser = null;
		try {
			if (rset.next()) { // 只有一條用if , 多條用while
				leaveuser = new LeaveUser();
				// 數據封裝
				leaveuser.setUserId(rset.getLong("userId"));
				leaveuser.setAccount(rset.getString("account"));
				leaveuser.setPassword(rset.getString("password"));
				leaveuser.setName(rset.getString("name"));
				leaveuser.setIdentity(rset.getString("identity"));
				leaveuser.setMail(rset.getString("mail"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace(); //給開發人員看
			throw new DataAccessException(e.getMessage()); // 給系統維護看的
		} finally {
			// 關閉資源
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}
		
		
		return leaveuser;
	}

	@Override
	public LeaveUser getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//使用帳號得到 user資訊
	public LeaveUser getUserByAccount(String account) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC
				.executeQuery(
						"select userId,account,password,name,identity,mail from leave_user where account=?",
						new Object[] { account });

		LeaveUser leaveuser = null;
		try {
			if (rset.next()) {
				leaveuser = new LeaveUser();
				//數據封裝
				leaveuser.setUserId(rset.getLong("userId"));
				leaveuser.setAccount(rset.getString("account"));
				leaveuser.setPassword(rset.getString("password"));
				leaveuser.setName(rset.getString("name"));
				leaveuser.setIdentity(rset.getString("identity"));
				leaveuser.setMail(rset.getString("mail"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace(); // 給開發人員看的
			throw new DataAccessException(e.getMessage()); // 給系統維護看的
		} finally {
			// 關閉資源
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}
		
		
		return leaveuser;
		
	}

	@Override
	//使用UserId查詢,使用者名稱
	public String getNameByUserId(long userId) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select name from leave_user where userId=?",
				new Object[] {userId});

		LeaveUser leaveuser = null;

		try {
			if (rset.next()) {
				leaveuser = new LeaveUser();
				// 数据封装
				leaveuser.setName(rset.getString("name"));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leaveuser.getName();
	}

	@Override
	//用 MainId 找使用者mail 
	public String getMailByMainId(long mainid) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select c.mail from  leave_main b,leave_user c where  c.userid = b.userid and b.mainid = ?",
				new Object[] {mainid});

		LeaveUser Leaveuser = null;

		try {
			if (rset.next()) {
				Leaveuser = new LeaveUser();
				// 数据封装
				Leaveuser.setMail(rset.getString("mail")); //記下來
				//leaveclass.setClassName(rset.getString("className"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return Leaveuser.getMail();
	}

	@Override
	//取得所有使用者ID
	public List<LeaveUser> getAllUserId() {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select userid from leave_user",
				new Object[] {});
		
		List<LeaveUser> leaveuseridlist = null;
		try {
			leaveuseridlist = new ArrayList<LeaveUser>();
			while (rset.next()) {
				LeaveUser leaveuser = new LeaveUser();
				// 数据封装
				leaveuser.setUserId(rset.getLong("userid"));
				//leaveadyear.setAdyear(rset.getString("adyear"));
				
				leaveuseridlist.add(leaveuser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}
		return leaveuseridlist;
	
	}
	

}
