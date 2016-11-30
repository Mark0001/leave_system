package com.tku.leave.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tku.leave.domain.LeaveAdyear;
import com.tku.leave.domain.LeaveClass;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.domain.LeaveUserMax;
import com.tku.leave.exception.DataAccessException;
import com.tku.leave.service.impl.AdministratorServiceImpl;
import com.tku.leave.service.impl.ManagerServiceImpl;
import com.tku.leave.service.impl.UserServiceImpl;
import com.tku.leave.service.inter.AdministratorServiceInter;
import com.tku.leave.service.inter.ManagerServiceInter;
import com.tku.leave.service.inter.UserServiceInter;

public class ManagerServletMax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServiceInter userservice = new UserServiceImpl();
	ManagerServiceInter managerservice = new ManagerServiceImpl();
	AdministratorServiceInter administratorservice = new AdministratorServiceImpl();

	public ManagerServletMax() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("act");
		if ("initialAndQuery".equals(act)) {// 顯示出查詢選項,假別&年度& 初始化沒有最大時數的資料
			List<LeaveUser> leaveuser = null;
			List<LeaveAdyear> leaveadyear = null;
			List<LeaveClass> leaveclass = null;
//			LeaveUserMax leaveusermax = new LeaveUserMax();

			try {

				// leaveadyear.get(0).getAdyear();
				// leaveadyear.size();

				leaveadyear = managerservice.getAllAdyear();// 取得所有年度資訊
				leaveclass = managerservice.getAll();// 取得所有假別資訊
				leaveuser = managerservice.getAllUserId();// 取得所有年度ID
				request.setAttribute("leaveadyear", leaveadyear); // 存進下拉式選單中
				request.setAttribute("leaveclass", leaveclass);

				// int counter=1;//計錄筆數
				/*
				for (int i = 0; i < leaveuser.size(); i++) {// 初始化沒有最大時數的資料

					long[] userid;
					userid = new long[leaveuser.size() + 1];
					userid[i] = leaveuser.get(i).getUserId();
					for (int j = 0; j < leaveadyear.size(); j++) {
						long[] adyearid;
						adyearid = new long[leaveadyear.size() + 1];
						adyearid[j] = leaveadyear.get(j).getAdyearId();
						for (int k = 0; k < leaveclass.size(); k++) {
							long[] classId;
							classId = new long[leaveclass.size() + 1];
							classId[k] = leaveclass.get(k).getClassId();
							// System.out.println("------ :" + counter);
							// System.out.println("UserId : " + userid[i]);
							// System.out.println("adyearid : " +adyearid[j]);
							// System.out.println("classId : " +classId[k]);
							if (managerservice.isExistByCompositeKey(userid[i],
									adyearid[j], classId[k]) == false) {// 複合建沒資料再輸入預設值
								leaveusermax.setUserId(userid[i]);
								leaveusermax.setAdYearId(adyearid[j]);
								leaveusermax.setClassId(classId[k]);
								System.out.println("---");
								managerservice.initialUserMax(leaveusermax);
								// counter ++;
							}
						}

					}

				}
				*/
				// 取值test
				// for(int i=0; i<leaveuser.size();i++){
				// long[] userid;
				// userid = new long[leaveuser.size() + 1];
				// userid[i] = leaveuser.get(i).getUserId();
				// System.out.println("UserId : " + userid[i]);
				// }
				// for(int i=0; i<leaveadyear.size();i++){
				// long[] adyearid;
				// adyearid = new long[leaveadyear.size() + 1];
				// adyearid[i] = leaveadyear.get(i).getAdyearId();
				// System.out.println("adyearid : " +adyearid[i]);
				// }
				// for(int i=0; i<leaveclass.size();i++){
				// long[] classId;
				// classId = new long[leaveclass.size() + 1];
				// classId[i] = leaveclass.get(i).getClassId();
				// System.out.println("classId : " +classId[i]);
				// }

				// System.out.println("年度 : " + leaveadyear);
				// System.out.println("size : " + leaveuser.size());
				request.getRequestDispatcher("jsps/manager/EditMax.jsp")
						.forward(request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		} else if ("Query".equals(act)) {// 查詢要修改的最大時數資料
			String radio = request.getParameter("radio");
			List<LeaveUserMax> leaveusermaxlist=null; 
			try {
				if (radio.equals("0")) {//使用姓名查詢
					String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "utf-8");// 亂碼處理
					leaveusermaxlist=managerservice.getleaveUserMaxByName(name);
					// System.out.println("name : " + name);
				}
				if (radio.equals("1")) {//使用年度&假別名稱查詢
					String adyear = new String(request.getParameter("adyear").getBytes("ISO-8859-1"), "utf-8");// 亂碼處理
					String className = new String(request.getParameter("className").getBytes("ISO-8859-1"), "utf-8");// 亂碼處理
					leaveusermaxlist=managerservice.getleaveUserMaxByAdyearClassName(adyear, className);
					// System.out.println("adyear : " +adyear);
					// System.out.println("className : " +className);
				}
				request.setAttribute("leaveusermaxlist", leaveusermaxlist);
//				request.getRequestDispatcher("jsps/manager/EditMax.jsp")
//				.forward(request, response);
				 request.getRequestDispatcher("ManagerServletMax?act=initialAndQuery")
				 .forward(request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		}else if("update".equals(act)){
			
			List<LeaveUserMax> leaveusermaxlist=null; 
			try {
				
					String userId =request.getParameter("userId");
					String adYearId = request.getParameter("adYearId");
					String classId =request.getParameter("classId");
					String approveMax =request.getParameter("approveMax");
					System.out.println("userId : " + userId);
					System.out.println("adYearId : " +adYearId);
					System.out.println("classId : " +classId);
					System.out.println("approveMax : " +approveMax);
					LeaveUserMax leaveusermax = new LeaveUserMax();
					leaveusermax.setUserId(Long.parseLong(userId));
					leaveusermax.setAdYearId(Long.parseLong(adYearId));
					leaveusermax.setClassId(Long.parseLong(classId));
					leaveusermax.setApproveMax(approveMax);
					managerservice.updateLeaveUserMax(leaveusermax);
					
				
				}catch (DataAccessException de) { // 數據訪問異常
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
