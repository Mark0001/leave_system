package com.tku.leave.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tku.leave.domain.LeaveDetail;
import com.tku.leave.domain.LeaveMain;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.exception.DataAccessException;
import com.tku.leave.service.impl.AdministratorServiceImpl;
import com.tku.leave.service.inter.AdministratorServiceInter;
import com.tku.leave.utils.MailUtils;

public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdministratorServiceInter administratorservice = new AdministratorServiceImpl();

	public AdministratorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("act");
		if ("listNotCheck".equals(act)) {
			List<LeaveMain> leavemainlist = null;// 把所有Main假單資訊,裝進List

			try {
				leavemainlist = administratorservice.getAllNotCheck();// 取出所有未審假單Main
				request.setAttribute("leavemainlist", leavemainlist);// 放入leavemainlist中

				// 頁面跳轉
				request.getRequestDispatcher(
						"jsps/administrator/AdministratorApprove.jsp").forward(
						request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}

		} else if ("check".equals(act)) {// 審核+寄送Email,再返回審核畫面 !!!!!補寄信
			String mainId = request.getParameter("mainId");
			String approvestatus =request.getParameter("approvestatus");// 取得准假or不准
			String feedback = request.getParameter("feedback");
			 

			// System.out.println(approvestatus);//測試

			System.out.println(mainId);
			LeaveMain leavemain = null;
			String mail = null;
			try {
				// 根據MainId查假單
				leavemain = administratorservice.getLeaveByMainId(Long
						.parseLong(mainId));// 轉成long
				mail = administratorservice.getMailByMainId(Long
						.parseLong(mainId));
				//System.out.println("mail :" + mail);// 測試
				//System.out.println("feedback 亂碼:" + feedback);// 測試
				 String feedbackOk = new String(feedback.getBytes("ISO-8859-1"), "utf-8");
				// System.out.println("feedback :" + x);// 測試
				// if (leavemain != null) {

				if (approvestatus.equals("1")) {// 假單通過 寄信
					//System.out.println("approvestatus OK:" + approvestatus);// 測試
					leavemain.setApproveStatus(approvestatus);// 改變審核狀態 0 未審 1 通過 2不通過
					//leavemain.getApplytime();
					System.out.println("Applytime :" + leavemain.getApplytime());// 測試
					MailUtils.MailOk(mail,feedbackOk);
					administratorservice.checkLeave(leavemain);// 審核
					// 頁面跳轉
					request.getRequestDispatcher(
							"AdministratorServlet?act=listNotCheck").forward(
							request, response);
				} else if (approvestatus.equals("2")) {
					//System.out.println("approvestatus No:" + approvestatus);// 測試
					leavemain.setApproveStatus(approvestatus);// 改變審核狀態 0 未審 1 通過 2不通過
					MailUtils.MailNo(mail,feedbackOk);
					administratorservice.checkLeave(leavemain);// 審核
					// 頁面跳轉
					request.getRequestDispatcher(
							"AdministratorServlet?act=listNotCheck").forward(
							request, response);
					// }

				}
			} catch (DataAccessException de) { // 数据访问异常，系统级错误
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}

		} else if ("listDetail".equals(act)) {// 顯示假單細節
			String mainId = request.getParameter("mainId");
			// System.out.println(" 取直測試: " + mainId);//取直測試
			List<LeaveDetail> leavedetaillist = null;

			try {

				leavedetaillist = administratorservice.getLeaveDetail(Long
						.parseLong(mainId));
				request.setAttribute("leavedetaillist", leavedetaillist);

				request.getRequestDispatcher(
						"jsps/administrator/AdministratorCheck.jsp").forward(
						request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
