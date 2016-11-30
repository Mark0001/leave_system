package com.tku.leave.dao.jdbc;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


import com.tku.leave.exception.DataAccessException;
public class DaoJDBC {
	// �������
		private static Connection ct = null;
		// ������������preparedstatement���statement
		private static PreparedStatement ps = null;
		private static ResultSet rs = null;


		private static String url = "";
		private static String username = "";
		private static String driver = "";
		private static String passwd = "";

		private static CallableStatement cs = null;

		public static CallableStatement getCs() {
			return cs;
		}

		private static Properties pp = null;
		private static InputStream fis = null;
		// ������ֻ��Ҫһ�Σ��þ�̬�����
		static {
			try {
				// ��dbinfo.properties
				pp = new Properties();
				fis = DaoJDBC.class.getClassLoader().getResourceAsStream(
						"database.properties");
				// fis = new FileInputStream();
				pp.load(fis);
				driver = pp.getProperty("driver");
				url = pp.getProperty("url");
				username = pp.getProperty("username");
				passwd = pp.getProperty("password");

				Class.forName(driver);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
				fis = null;// �������վ����ʰ
			}

		}

		/**
		 * �õ�����
		 * 
		 * @return
		 */
		public static Connection getConnection() {
			try {
				ct = DriverManager.getConnection(url, username, passwd);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			}
			return ct;
		}

		public static Connection getCt() {
			return ct;
		}

		public static PreparedStatement getPs() {
			return ps;
		}

		public static ResultSet getRs() {
			return rs;
		}

		// *************callPro1�洢��̺���1*************
		// public static CallableStatement callPro1(String sql, Object[] parameters)
		// {
		// try {
		// ct = getConnection();
		// cs = ct.prepareCall(sql);
		// if (parameters != null) {
		// for (int i = 0; i < parameters.length; i++) {
		// cs.setObject(i + 1, parameters[i]);
		// }
		// }
		// cs.execute();
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new DataAccessException(e.getMessage());
		// } finally {
		// close(rs, cs, ct);
		// }
		// return cs;
		// }

		// *******************callpro2�洢���2************************
		// public static CallableStatement callPro2(String sql, Object[]
		// inparameters,
		// Integer[] outparameters) {
		// try {
		// ct = getConnection();
		// cs = ct.prepareCall(sql);
		// if (inparameters != null) {
		// for (int i = 0; i < inparameters.length; i++) {
		// cs.setObject(i + 1, inparameters[i]);
		// }
		// }
		// // cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
		// if (outparameters != null) {
		// for (int i = 0; i < outparameters.length; i++) {
		// cs.registerOutParameter(inparameters.length + 1 + i,
		// outparameters[i]);
		// }
		// }
		// cs.execute();
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new DataAccessException(e.getMessage());
		// } finally {
		//
		// }
		// return cs;
		// }

		/**
		 * ͳһ�Ĺ�Ѷ���
		 * 
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public static ResultSet executeQuery(String sql, Object[] parameters) {
			try {
				// ������ݿ�
				ct = getConnection();
				// ׼��sql�������Ӧ�Ĳ���
				ps = ct.prepareStatement(sql);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						ps.setObject(i + 1, parameters[i]);
					}
				}
				// ִ�в�ѯ
				rs = ps.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				// close(rs, ps, ct);
			}
			return rs;
		}
		
		


		// public static void executeUpdate2(String[] sql, Object[][] parameters) {
		// try {
		// ct = getConnection();
		// ct.setAutoCommit(false);
		//
		// for (int i = 0; i < sql.length; i++) {
		//
		// if (null != parameters[i]) {
		// ps = ct.prepareStatement(sql[i]);
		// for (int j = 0; j < parameters[i].length; j++) {
		// ps.setObject(j + 1, parameters[i][j]);
		// }
		// ps.executeUpdate();
		// }
		//
		// }
		//
		// ct.commit();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// try {
		// ct.rollback();
		// } catch (SQLException e1) {
		// e1.printStackTrace();
		// throw new DataAccessException(e.getMessage());
		// }
		// throw new DataAccessException(e.getMessage());
		// } finally {
		// close(rs, ps, ct);
		// }
		//
		// }

		/**
		 * ��ҳ��ѯ
		 * 
		 * @param sql
		 * @param parameters
		 * @param begin
		 * @param end
		 * @return
		 */
		public static ResultSet executeQueryByPage(String sql, Object[] parameters,
				int pageNum, int pageSize) {
			// ����begin��end ����ҳ ��ÿҳ10�� 1-10 11-20 21-30 31-40
			Integer begin = (pageNum - 1) * pageSize + 1;
			Integer end = pageNum * pageSize;

			try {
				// ������ݿ�
				ct = getConnection();
				// ׼��sql�������Ӧ�Ĳ���
				String pagesql = "select * from (select a1.*,rownum rn from ("
						+ sql + ") a1 where rownum <= ?) where rn >= ?";
				ps = ct.prepareStatement(pagesql);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						ps.setObject(i + 1, parameters[i]);
					}
				}
				ps.setObject(parameters.length + 1, end);
				ps.setObject(parameters.length + 2, begin);
				// ִ�в�ѯ
				rs = ps.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataAccessException(e.getMessage());
			} finally {
				// close(rs, ps, ct);
			}
			return rs;
		}

		/**
		 * ͳһ����ݸ��·���
		 * 
		 * @param sql
		 * @param parameters
		 */
		public static void executeUpdate(String sql, Object[] parameters) {
			try {
				ct = getConnection();
				ps = ct.prepareStatement(sql);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						ps.setObject(i + 1, parameters[i]);
					}

				}
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();// �����׶�
				throw new DataAccessException(e.getMessage());
			} finally {
				close(rs, ps, ct);
			}
		}

		/**
		 * �ر������Դ
		 * 
		 * @param rs
		 * @param ps
		 * @param ct
		 */
		public static void close(ResultSet rs, Statement ps, Connection ct) {
			// �ر���Դ(�ȿ����)
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ps = null;
			}
			if (null != ct) {
				try {
					ct.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ct = null;
			}
		}
}
