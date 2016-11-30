package com.tku.leave.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tku.leave.domain.LeaveClass;
import com.tku.leave.domain.LeaveMain;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.domain.LeaveUserMax;
import com.tku.leave.exception.ApplicationException;
import com.tku.leave.exception.DataAccessException;
import com.tku.leave.service.impl.AdministratorServiceImpl;
import com.tku.leave.service.impl.ManagerServiceImpl;
import com.tku.leave.service.impl.UserServiceImpl;
import com.tku.leave.service.inter.AdministratorServiceInter;
import com.tku.leave.service.inter.ManagerServiceInter;
import com.tku.leave.service.inter.UserServiceInter;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserServiceInter userService = new UserServiceImpl();
	ManagerServiceInter managerservice = new ManagerServiceImpl();
	AdministratorServiceInter administratorservice = new AdministratorServiceImpl();

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String act = request.getParameter("act");
		if ("login".equals(act)) { // 登入動作
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			LeaveUser user = null;
			try {
				user = userService.checkerUser(account, password);
				// 登入成功
				request.getSession().setAttribute("user", user);
				LeaveUser leaveuser = (LeaveUser) request.getSession()
						.getAttribute("user");
				String identity = leaveuser.getIdentity();
				request.getSession().setAttribute("identity", identity);// 身分驗證

				// 主管 identity 為0
				if ("0".equals(identity)) {

					response.sendRedirect("LoginServlet?act=administratorLogin");
				}
				// 管理員 identity 為1
				if ("1".equals(identity)) {

					response.sendRedirect("LoginServlet?act=userLogin");
				}

				// 員工identity 為2
				if ("2".equals(identity)) {
					// request.getRequestDispatcher("jsps/user/UserIndex.jsp")
					// .forward(request, response);
					response.sendRedirect("LoginServlet?act=userLogin");

				}
			} catch (DataAccessException de) {
				// 數據訪問師敗
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} catch (ApplicationException ae) {
				// 登入失敗
				request.setAttribute("msg", ae.getMessage());
				request.getRequestDispatcher("Login.jsp").forward(request,
						response);
			}

		} else if ("logout".equals(act)) { // 登出
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("identity");
			request.getRequestDispatcher("Login.jsp")
					.forward(request, response);
		} else if ("userLogin".equals(act)) {// 員工&管理員 登入首頁顯示資訊
			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");
			List<LeaveClass> leaveclasslist = null;
			int noapprovenumber = userService.returnNoApproveNumber(leaveuser
					.getUserId());
			// List<LeaveUserMax> leavemaxlist=null;
			leaveclasslist = managerservice.getAll();// 取得所有假

			for (int i = 0; i < leaveclasslist.size(); i++) {// 依續封裝list中

				String name = leaveuser.getName();// 用session取得使用者名稱
				leaveclasslist.get(i).setApproveMax(
						managerservice.getApproveMaxByNameClassName(name,
								leaveclasslist.get(i).getClassName()));
				leaveclasslist.get(i).setSumLeaveTime(
						managerservice.getSumLeaveTimeByNameClassName(name,
								leaveclasslist.get(i).getClassName()));
				leaveclasslist.get(i).setRemain(
						leaveclasslist.get(i).getApproveMax()
								- leaveclasslist.get(i).getSumLeaveTime());// 剩餘可請時間
				// System.out.println(leaveclasslist.get(i).getClassName()+"--> 最大時數 :"+leaveclasslist.get(i).getApproveMax()+" 已請時數 :"+leaveclasslist.get(i).getSumLeaveTime()+"剩餘可請時數 : "+leaveclasslist.get(i).getRemain());
				request.setAttribute("noapprovenumber", noapprovenumber);// 將不通過假單數放入
				request.setAttribute("leaveclasslist", leaveclasslist);// 將假別可請時數資料放在list中

			}
			// 管理員 identity 為1
			if ("1".equals(leaveuser.getIdentity())) {
				request.getRequestDispatcher("jsps/manager/ManagerIndex.jsp")
						.forward(request, response);
			} else if ("2".equals(leaveuser.getIdentity())) {// 員工 identity 為2
				request.getRequestDispatcher("jsps/user/UserIndex.jsp")
						.forward(request, response);
			}
		}else if("administratorLogin".equals(act)){//管理員登入頁面
			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");
			List<LeaveClass> leaveclasslist = null;
			leaveclasslist = managerservice.getAll();// 取得所有假
			int notchecknumber = administratorservice.returnNotCheckNumber();
			
			for (int i = 0; i < leaveclasslist.size(); i++) {// 依續封裝list中

				String name = leaveuser.getName();// 用session取得使用者名稱
				leaveclasslist.get(i).setApproveMax(
						managerservice.getApproveMaxByNameClassName(name,
								leaveclasslist.get(i).getClassName()));
				leaveclasslist.get(i).setSumLeaveTime(
						managerservice.getSumLeaveTimeByNameClassName(name,
								leaveclasslist.get(i).getClassName()));
				leaveclasslist.get(i).setRemain(
						leaveclasslist.get(i).getApproveMax()
								- leaveclasslist.get(i).getSumLeaveTime());// 剩餘可請時間
				// System.out.println(leaveclasslist.get(i).getClassName()+"--> 最大時數 :"+leaveclasslist.get(i).getApproveMax()+" 已請時數 :"+leaveclasslist.get(i).getSumLeaveTime()+"剩餘可請時數 : "+leaveclasslist.get(i).getRemain());
				request.setAttribute("notchecknumber", notchecknumber);// 將未審假單數放入
				request.setAttribute("leaveclasslist", leaveclasslist);// 將假別可請時數資料放在list中

			}
			
			request.getRequestDispatcher("jsps/administrator/AdministratorIndex.jsp")
			.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
