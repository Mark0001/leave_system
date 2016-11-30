package com.tku.leave.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tku.leave.dao.inter.LeaveClassDaoInter;
import com.tku.leave.dao.jdbc.DaoJDBC;
import com.tku.leave.domain.LeaveClass;
import com.tku.leave.exception.DataAccessException;


public class LeaveClassDaoImpl implements LeaveClassDaoInter {

	//根據clssId查詢 假別名稱和ClassId
	public LeaveClass getClassNameByClassId(Serializable classId) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select classid,classname from leave_class where classid=?",
				new Object[] { classId });

		LeaveClass leaveclass = null;

		try {
			if (rset.next()) {
				leaveclass = new LeaveClass();
				// 数据封装
				leaveclass.setClassId(rset.getLong("classId"));
				leaveclass.setClassName(rset.getString("className"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leaveclass;
	}

	// 根據假別名稱查詢 classID
	public long getClassIdByClassName(String className) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select classId from leave_class where className=?",
				new Object[] { className });

		LeaveClass leaveclass = null;

		try {
			if (rset.next()) {
				leaveclass = new LeaveClass();
				// 数据封装
				leaveclass.setClassId(rset.getLong("classId"));
				//leaveclass.setClassName(rset.getString("className"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leaveclass.getClassId();
	}

	// 新增假別名稱
	public void addClassName(LeaveClass leaveclass) {
		// TODO Auto-generated method stub
		DaoJDBC.executeUpdate(
				"insert into leave_class values(seq_leave.nextval,?)",
				new Object[] { leaveclass.getClassName() });

	}

	//更新假別名稱
	public void updateClassName(LeaveClass leaveclass) {
		// TODO Auto-generated method stub
		DaoJDBC.executeUpdate(
				"update leave_class set className=? where classId=?",
				new Object[] { leaveclass.getClassName(),
								leaveclass.getClassId()
						});

	}

	@Override
	public void deleteClass(LeaveClass leaveclass) {
		// TODO Auto-generated method stub

	}


	//列出所有 假別名稱
	public List<LeaveClass> getAll() {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select classId,className from leave_class",
				new Object[] {});

		
		List<LeaveClass> leaveclasslist = null;
		try {
			leaveclasslist = new ArrayList<LeaveClass>();
			while (rset.next()) {
				LeaveClass leaveclass = new LeaveClass();
				// 数据封装
				leaveclass.setClassId(rset.getLong("classId"));
				leaveclass.setClassName(rset.getString("className"));
				
				leaveclasslist.add(leaveclass);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return leaveclasslist;
	}

	@Override
	// 假查假別名稱
	public String checkClassName(String className) {
		// TODO Auto-generated method stub
		ResultSet rset = DaoJDBC.executeQuery(
				"select classname from leave_class where classname=?",
				new Object[] { className });
		String checkclassname = null;

		try {
			if (rset.next()) {

				checkclassname = rset.getString("className");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		} finally {
			DaoJDBC.close(rset, DaoJDBC.getPs(), DaoJDBC.getCt());
		}

		return checkclassname;
	}

	@Override
	//取得所有假別Id
	public List<LeaveClass> getAllClassId() {
		// TODO Auto-generated method stub
		return null;
	}



}
