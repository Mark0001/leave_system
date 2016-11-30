package com.tku.leave.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tku.leave.domain.LeaveAdyear;
import com.tku.leave.domain.LeaveClass;
import com.tku.leave.domain.LeaveDetail;
import com.tku.leave.domain.LeaveMain;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.exception.DataAccessException;
import com.tku.leave.service.impl.AdministratorServiceImpl;
import com.tku.leave.service.impl.ManagerServiceImpl;
import com.tku.leave.service.impl.UserServiceImpl;
import com.tku.leave.service.inter.AdministratorServiceInter;
import com.tku.leave.service.inter.ManagerServiceInter;
import com.tku.leave.service.inter.UserServiceInter;
import com.tku.leave.utils.Csv0;
import com.tku.leave.utils.DateUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//
	UserServiceInter userservice = new UserServiceImpl();
	ManagerServiceInter managerservice = new ManagerServiceImpl();
	AdministratorServiceInter administratorservice = new AdministratorServiceImpl();

	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("act");

		if ("listLeave".equals(act)) {// 顯示假別
			List<LeaveClass> leaveclasslist = null;
			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");

			try {

				leaveclasslist = managerservice.getAll();// 取得所有假別
				request.setAttribute("leaveclasslist", leaveclasslist);

				request.getRequestDispatcher("jsps/user/AddLeave.jsp").forward(
						request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		} else if ("submit".equals(act)) {// 假單Detail送出
			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");
			LeaveMain editleavemain = new LeaveMain();
			editleavemain.setApproveStatus("0");// 將審核狀態設為未審
			editleavemain.setUserId(leaveuser.getUserId());// 取得該使用者的UserId
			userservice.addLeaveMain(editleavemain);// 新增main假單
			LeaveMain leavemain = new LeaveMain();
			long MainId = userservice.getFristMainById(leaveuser.getUserId());// 用session取得用戶第一個MainID
			leavemain.setMainId(MainId);

			LeaveDetail leavedetail = new LeaveDetail();
			int counter = 1;// 先設定初值,避免為NULL
			counter = Integer
					.parseInt((String) request.getParameter("counter"));// 取得假單張數
			// System.out.println("取值測試3：" + counter);
			// System.out.println("取值測試 2：" +MainId); //成功

			String[] className;
			className = new String[counter + 1];
			long[] classId;
			classId = new long[counter + 1];

			Date[] beginTime;
			beginTime = new Date[counter + 1];
			Date[] endTime;
			endTime = new Date[counter + 1];
			String[] leavetime;
			leavetime = new String[counter + 1];
			String[] reason;
			reason = new String[counter + 1];

			for (int i = 1; i < counter + 1; i++) { // 將假單一個一個新增

				// 將前端的假別名稱轉成ClassId,存入陣列
				className[i] = request.getParameter("select" + i);
				className[i] = new String(className[i].getBytes("ISO-8859-1"),
						"utf-8");// 亂碼處理
				classId[i] = managerservice.getClassIdByClassName(className[i]);
				System.out.println("selecti：" + classId[i]);

				// 封裝請假開始時間
				beginTime[i] = DateUtils.StrToDate(request.getParameter("begin"
						+ i));
				System.out.println("beginTime1：" + beginTime[i]);

				// 封裝結束時間
				endTime[i] = DateUtils.StrToDate(request
						.getParameter("end" + i));
				System.out.println("endTime：" + endTime[i]);

				// 封裝請假時數
				leavetime[i] = request.getParameter("leavetime" + i);
				System.out.println("leavetime：" + leavetime[i]);

				// 封裝假由
				reason[i] = request.getParameter("reason" + i);
				reason[i] = new String(reason[i].getBytes("ISO-8859-1"),
						"utf-8");// 亂碼處理
				System.out.println("reason：" + reason[i]);

			}

			for (int i = 1; i < counter + 1; i++) {// 相同假別請假時數判斷
				// float sum;
				float leavetimei = Float.parseFloat(leavetime[i]);

				for (int j = 1; j < counter; j++) {
					if (i != j && className[i].equals(className[j])) {
						leavetimei = leavetimei
								+ Float.parseFloat(leavetime[j]);
					}
				}
				float SumLeaveTime = managerservice
						.getSumLeaveTimeByNameClassName(leaveuser.getName(),
								className[i]);
				float ApproveMax = managerservice.getApproveMaxByNameClassName(
						leaveuser.getName(), className[i]);
				float remain = ApproveMax - SumLeaveTime;
				System.out.println("假別" + className[i]);
				System.out.println("請假總和" + leavetimei);
				System.out.println("剩餘" + remain);
				request.setCharacterEncoding("utf-8");// 解決亂碼
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				if (remain >= leavetimei) {// 時數足夠
					// out.print("OK");
				} else if (remain < leavetimei) {// 時數不足
					userservice.deleteMainSheet(leavemain);// 刪除產生的Main假單
					out.print("<script laguage='JavaScript'> alert('超過可請時數，請重新填寫。');location.href='http://localhost:8080/test01/jsps/user/LeaveDescription.jsp'; </script>");
					out.flush();
					out.close();
				}
				leavetimei = 0;
				remain = 0;
			}

			if (MainId == userservice.getFristMainById(leaveuser.getUserId())) {// 相等在新增
				for (int i = 1; i < counter + 1; i++) {
					leavedetail.setMainId(MainId);
					leavedetail.setClassId(classId[i]);
					leavedetail.setBeginTime(beginTime[i]);
					leavedetail.setEndTime(endTime[i]);
					leavedetail.setLeaveTime(leavetime[i]);
					leavedetail.setReason(reason[i]);
					userservice.addLeaveDetail(leavedetail); // 將封裝好的資料存入資料庫
				}
			}

			// response.sendRedirect("");依身分跳轉首頁,或再做確認送
			if ("0".equals(request.getSession().getAttribute("identity"))) { // 主管跳轉
				response.sendRedirect("LoginServlet?act=administratorLogin");
			} else if ("1"
					.equals(request.getSession().getAttribute("identity"))) { // 管理員跳轉
				response.sendRedirect("LoginServlet?act=userLogin");
			} else if ("2"
					.equals(request.getSession().getAttribute("identity"))) {// 員工跳轉
				response.sendRedirect("jsps/user/UserIndex.jsp");
			} else {
				response.sendRedirect("LoginServlet?act=userLogin"); // 沒有身分進入網頁，跳回登入頁面
			}

		} else if ("listEdit".equals(act)) {// 列出該使用者沒有通過的假單
			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");
			List<LeaveMain> leavemainlist = null;

			try {
				leavemainlist = userservice.getNotApproveByUserId(leaveuser
						.getUserId());// 拿到UserId取處退回假單
				request.setAttribute("leavemainlist", leavemainlist);// 將退回Main假單放入leavemainlist

				request.getRequestDispatcher("jsps/user/EditLeaveIndex.jsp")
						.forward(request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		} else if ("editDetailList".equals(act)) {// 列出未過假單detail,並給予新欄位填寫
			String mainId = request.getParameter("mainId");
			// System.out.println(" 取直測試: " + mainId);//取直測試
			List<LeaveClass> leaveclasslist = null;
			List<LeaveDetail> leavedetaillist = null;
			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");

			try {
				leaveclasslist = managerservice.getAll();// 取得所有假別
				request.setAttribute("leaveclasslist", leaveclasslist);
				leavedetaillist = administratorservice.getLeaveDetail(Long
						.parseLong(mainId));
				request.setAttribute("leavedetaillist", leavedetaillist);
				// List !!!
				// System.out.println("List 測試 1 : "+leavedetaillist.size());
				// System.out.println("List 測試 2 : "+leavedetaillist.get(0).getMainId());
				// for(int i=0;i<leavedetaillist.size();i++){
				// System.out.println("List 測試 for  : "+leavedetaillist.get(i).getReason());
				// }
				//
				request.getRequestDispatcher("jsps/user/EditLeave.jsp")
						.forward(request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}

		} else if ("initialQuery".equals(act)) {// 顯示出查詢選項,假別&名稱
			// LeaveUser leaveuser = (LeaveUser) request.getSession()
			// .getAttribute("user");

			List<LeaveAdyear> leaveadyear = null;
			try {

				leaveadyear = managerservice.getAllAdyear();// 取得所有年度
				request.setAttribute("leaveadyear", leaveadyear);
				request.getRequestDispatcher("jsps/user/UserQuery.jsp")
						.forward(request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		} else if ("Query".equals(act)) {// 列出查詢價單

			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");// 取得使用者資料
			String adyear = request.getParameter("adyear");
			List<LeaveMain> leavemainlist = null;
			List<LeaveAdyear> leaveadyear = null;
			if (leaveuser.getIdentity().equals("0")) {// 主管跳轉可查詢所有人假單
				String name = new String(request.getParameter("name").getBytes(
						"ISO-8859-1"), "utf-8");
				leavemainlist = userservice.getLeaveMainByNameAdyear(name,
						adyear);// 取得所有假別
			} else {// 其他身分只能查自己假單
				leavemainlist = userservice.getLeaveMainByNameAdyear(
						leaveuser.getName(), adyear);
			}

			try {
				leaveadyear = managerservice.getAllAdyear();// 取得所有年度(下拉式選單)
				request.setAttribute("leaveadyear", leaveadyear);
				request.setAttribute("leavemainlist", leavemainlist);
				request.getRequestDispatcher("jsps/user/UserQuery.jsp")
						.forward(request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}

		} else if ("listDetail".equals(act)) {// 將Main假單的detail傳入互動式窗中
			String mainId = request.getParameter("mainId");
			// System.out.println(" 取直測試: " + mainId);// 取直測試
			List<LeaveDetail> leavedetaillist = null;

			try {
				request.setCharacterEncoding("utf-8");// 解決亂碼
				response.setContentType("text/html;charset=utf-8");
				leavedetaillist = administratorservice.getLeaveDetail(Long
						.parseLong(mainId));
				request.setAttribute("leavedetaillist", leavedetaillist);
				Gson gson = new Gson();
				String list = gson.toJson(leavedetaillist);
				// 測試亂碼來源
				// for(LeaveDetail l:leavedetaillist){
				// System.out.println(l);
				// }
				PrintWriter out = response.getWriter();
				out.print(list);
				out.flush();
				out.close();

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}

		} else if ("edit".equals(act)) {// 修改假單 產生心Main假單
			String mainIddelete = request.getParameter("mainId");
			System.out.println("取attribute :" + mainIddelete);
			LeaveMain leavemaindelete = null;
			// try{//處理值時數超過 值接註銷
			// leavemaindelete =
			// administratorservice.getLeaveByMainId(Long.parseLong(mainIddelete));//用MainId取Main假單
			// 轉成long
			// leavemaindelete.setApproveStatus("3");// 改變審核狀態 0 未審 1 通過 2不通過
			// 3刪除
			// administratorservice.checkLeave(leavemaindelete);// 註銷

			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");
			LeaveMain editleavemain = new LeaveMain();
			editleavemain.setApproveStatus("0");// 將審核狀態設為未審
			editleavemain.setUserId(leaveuser.getUserId());// 取得該使用者的UserId
			userservice.addLeaveMain(editleavemain);// 新增main假單
			LeaveMain leavemain = new LeaveMain();
			long MainId = userservice.getFristMainById(leaveuser.getUserId());// 用session取得用戶第一個MainID
			leavemain.setMainId(MainId);

			LeaveDetail leavedetail = new LeaveDetail();
			int counter = 1;// 先設定初值,避免為NULL
			counter = Integer
					.parseInt((String) request.getParameter("counter"));// 取得假單張數
			// System.out.println("取值測試3：" + counter);
			// System.out.println("取值測試 2：" +MainId); //成功

			String[] className;
			className = new String[counter + 1];
			long[] classId;
			classId = new long[counter + 1];

			Date[] beginTime;
			beginTime = new Date[counter + 1];
			Date[] endTime;
			endTime = new Date[counter + 1];
			String[] leavetime;
			leavetime = new String[counter + 1];
			String[] reason;
			reason = new String[counter + 1];

			for (int i = 1; i < counter + 1; i++) { // 將假單一個一個新增

				// 將前端的假別名稱轉成ClassId,存入陣列
				className[i] = request.getParameter("select" + i);
				className[i] = new String(className[i].getBytes("ISO-8859-1"),
						"utf-8");// 亂碼處理
				classId[i] = managerservice.getClassIdByClassName(className[i]);
				System.out.println("selecti：" + classId[i]);

				// 封裝請假開始時間
				beginTime[i] = DateUtils.StrToDate(request.getParameter("begin"
						+ i));
				System.out.println("beginTime1：" + beginTime[i]);

				// 封裝結束時間
				endTime[i] = DateUtils.StrToDate(request
						.getParameter("end" + i));
				System.out.println("endTime：" + endTime[i]);

				// 封裝請假時數
				leavetime[i] = request.getParameter("leavetime" + i);
				System.out.println("leavetime：" + leavetime[i]);

				// 封裝假由
				reason[i] = request.getParameter("reason" + i);
				reason[i] = new String(reason[i].getBytes("ISO-8859-1"),
						"utf-8");// 亂碼處理
				System.out.println("reason：" + reason[i]);

			}

			for (int i = 1; i < counter + 1; i++) {// 相同假別請假時數判斷
				// float sum;
				float leavetimei = Float.parseFloat(leavetime[i]);

				for (int j = 1; j < counter; j++) {
					if (i != j && className[i].equals(className[j])) {
						leavetimei = leavetimei
								+ Float.parseFloat(leavetime[j]);
					}
				}
				float SumLeaveTime = managerservice
						.getSumLeaveTimeByNameClassName(leaveuser.getName(),
								className[i]);
				float ApproveMax = managerservice.getApproveMaxByNameClassName(
						leaveuser.getName(), className[i]);
				float remain = ApproveMax - SumLeaveTime;
				System.out.println("假別" + className[i]);
				System.out.println("請假總和" + leavetimei);
				System.out.println("剩餘" + remain);

				request.setCharacterEncoding("utf-8");// 解決亂碼
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				if (remain >= leavetimei) {// 時數足夠
					// out.print("OK");
				} else if (remain < leavetimei) {// 時數不足

					userservice.deleteMainSheet(leavemain);// 刪除產生的Main假單
					out.print("<script laguage='JavaScript'> alert('超過可請時數，請重新填寫。');location.href='http://localhost:8080/test01/UserServlet?act=listEdit'; </script>");
					out.flush();
					out.close();
				}
				leavetimei = 0;
				remain = 0;
			}

			if (MainId == userservice.getFristMainById(leaveuser.getUserId())) {// 相等在新增新假單
																				// 再住消救假單
				leavemaindelete = administratorservice.getLeaveByMainId(Long
						.parseLong(mainIddelete));// 用MainId取Main假單 轉成long
				leavemaindelete.setApproveStatus("3");// 改變審核狀態 0 未審 1 通過 2不通過
														// 3刪除
				administratorservice.checkLeave(leavemaindelete);// 註銷
				for (int i = 1; i < counter + 1; i++) {
					leavedetail.setMainId(MainId);
					leavedetail.setClassId(classId[i]);
					leavedetail.setBeginTime(beginTime[i]);
					leavedetail.setEndTime(endTime[i]);
					leavedetail.setLeaveTime(leavetime[i]);
					leavedetail.setReason(reason[i]);
					userservice.addLeaveDetail(leavedetail); // 將封裝好的資料存入資料庫
				}
			}

			// response.sendRedirect("");依身分跳轉首頁,或再做確認送
			if ("0".equals(request.getSession().getAttribute("identity"))) { // 主管跳轉
				response.sendRedirect("LoginServlet?act=administratorLogin");
			} else if ("1"
					.equals(request.getSession().getAttribute("identity"))) { // 管理員跳轉
				response.sendRedirect("LoginServlet?act=userLogin");
			} else if ("2"
					.equals(request.getSession().getAttribute("identity"))) {// 員工跳轉
				response.sendRedirect("LoginServlet?act=userLogin");
			} else {
				response.sendRedirect("test01/LoginServlet?act=logout"); // 沒有身分進入網頁，跳回登入頁面
			}

		} else if ("delete".equals(act)) {// 刪除假單,把審核狀態設成3 為刪除
			String mainId = request.getParameter("mainId");
			String approvestatus = request.getParameter("approvestatus");
			LeaveMain leavemain = null;
			try {
				leavemain = administratorservice.getLeaveByMainId(Long
						.parseLong(mainId));// 用MainId取Main假單 轉成long
				leavemain.setApproveStatus(approvestatus);// 改變審核狀態 0 未審 1 通過
															// 2不通過 3刪除
				administratorservice.checkLeave(leavemain);// 註銷
				request.getRequestDispatcher("UserServlet?act=listEdit")
						.forward(request, response);
			} catch (DataAccessException de) { // 数据访问异常，系统级错误
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("jsps/error.jsp").forward(request,
						response);
			}
		} else if ("checkApproveMax".equals(act)) {// 確認是否超過最大可請時數
			LeaveUser leaveuser = (LeaveUser) request.getSession()
					.getAttribute("user");

			// int counter = Integer.parseInt(request.getParameter("counter"));
			String className = request.getParameter("className");
			float leaveTime = Float.parseFloat(request
					.getParameter("leaveTime"));
			System.out.println(className + leaveTime);
			float SumLeaveTime = managerservice.getSumLeaveTimeByNameClassName(
					leaveuser.getName(), className);// 此年度此假別請過的時數
			float ApproveMax = managerservice.getApproveMaxByNameClassName(
					leaveuser.getName(), className);// 此年度此假別能請的時數
			float remain = ApproveMax - SumLeaveTime;
			request.setCharacterEncoding("utf-8");// 解決亂碼
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			// System.out.println(counter);
			// for(int i=1;i<=counter;i++){
			// String[] className;
			// className = new String[counter];
			// className[i]=request.getParameter("className");
			// }

			if (remain >= leaveTime) {// 請假時數足夠
				// out.print("OK");
			} else if (remain < leaveTime) {// 請假時數不足

				out.print(remain);

			}
			out.flush();
			out.close();
		} else if ("noticenoapprove".equals(act)) {// ajax 如果有未通過假單 給予警告圖事
			long userId = Long.parseLong(request.getParameter("userId"));
			int NoApproveNumber = userservice.returnNoApproveNumber(userId);
			PrintWriter out = response.getWriter();
			if (NoApproveNumber != 0) {
				out.print("notice");
				out.flush();
				out.close();
			}
		} else if ("notcheck".equals(act)) {// ajax 如果有未審假單 給予警告圖事
			int NotCheckNumber = administratorservice.returnNotCheckNumber();
			PrintWriter out = response.getWriter();
			if (NotCheckNumber != 0) {
				out.print("notcheck");
				out.flush();
				out.close();
			}
		}else if ("Excel".equals(act)) {//匯出
			String mainId =  request.getParameter("mainId");		           
			System.out.println("mainId取直測試: "+mainId);
			List<LeaveDetail> leavedetaillist = null;
			leavedetaillist=administratorservice.getLeaveDetail((Long.parseLong(mainId)));
		   
			Csv0.Excel(leavedetaillist);				
			}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
