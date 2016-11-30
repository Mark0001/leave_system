package com.tku.leave.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tku.leave.domain.LeaveAdyear;
import com.tku.leave.domain.LeaveClass;
import com.tku.leave.domain.LeaveUser;
import com.tku.leave.domain.LeaveUserMax;
import com.tku.leave.exception.DataAccessException;
import com.tku.leave.service.impl.ManagerServiceImpl;
import com.tku.leave.service.inter.ManagerServiceInter;

public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerServiceInter managerservice = new ManagerServiceImpl();

	public ManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("act");
		if ("listClass".equals(act)) {
			// 修改假別頁面
			List<LeaveClass> leaveclasslist = null;
			try {
				leaveclasslist = managerservice.getAll();// 取出所有假別
				request.setAttribute("leaveclasslist", leaveclasslist);// 放入leaveclasslist中
				// 頁面跳轉
				request.getRequestDispatcher("jsps/manager/EditClass.jsp")
						.forward(request, response);

			} catch (DataAccessException de) { // 數據訪問異常
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		} else if ("update".equals(act)) {
			// 執行修改假別名稱
			doUpdateAction(request, response);
			// return;
		} else if ("add".equals(act)) {
			//request.setCharacterEncoding("GBK");
		
			
			String className = request.getParameter("className");
			// 封裝
			 String x = new String(className.getBytes("ISO-8859-1"), "utf-8"); //亂碼解決
			 System.out.println("亂碼測試：" +x);
			 
			LeaveClass newleaveclass =new LeaveClass();
			newleaveclass.setClassName(x);
			try {
				managerservice.addClassName(newleaveclass);
				//等新資料存完再初始化
				List<LeaveUser> leaveuser = null;
				List<LeaveClass> leaveclass = null;
				List<LeaveAdyear> leaveadyear = null;
				LeaveUserMax leaveusermax = new LeaveUserMax();
				leaveadyear = managerservice.getAllAdyear();// 取得所有年度資訊
				leaveclass = managerservice.getAll();// 取得所有假別資訊
				leaveuser = managerservice.getAllUserId();// 取得所有年度ID
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
				response.sendRedirect("ManagerServlet?act=listClass");
				
			} catch (DataAccessException de) {
				// 數據訪問失敗
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			
		}else if ("check".equals(act)) {
			// 檢查假別名稱
			String className = request.getParameter("className");	
			//System.out.println("ajax測試(check)：" + className);
			PrintWriter out = response.getWriter();		
			boolean check = managerservice.checkClassName(className);
			out.print(check);
			out.flush();
			out.close();
		}else if ("checkad".equals(act)) {
			// 檢查假別名稱
			String adyearName = request.getParameter("adyearName");	
			//System.out.println("ajax測試(check)：" + className);
			PrintWriter out = response.getWriter();		
			boolean check = managerservice.checkAdyearName(adyearName);
			out.print(check);
			out.flush();
			out.close();
		}

	}




	private void doUpdateAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();

		Map<String, String> errorMsgs = validateRequest(request);
		String paramclassId = request.getParameter("classId");
		if (isBlank(paramclassId) || !isNaturalNumbers(paramclassId)) {
			errorMsgs.put("classId", "請填寫假別編號");
		}
		if (!errorMsgs.isEmpty()) {
			pw.write(new JSONObject(errorMsgs).toString());
			pw.flush();
			return;
		}

		LeaveClass leaveclass = retrieveLeaveClass(request);
		leaveclass.setClassId(Integer.parseInt(request.getParameter("classId")));
		managerservice.updateClassName(leaveclass);
		updateContext();
		pw.println(new JSONObject("{res:0}"));
		pw.flush();
	}

	private LeaveClass retrieveLeaveClass(HttpServletRequest request) {
		LeaveClass leaveclass = new LeaveClass();
		leaveclass.setClassName(request.getParameter("className"));
		return leaveclass;
	}

	private Map<String, String> validateRequest(HttpServletRequest request) {
		Map<String, String> errorMsgs = new HashMap<String, String>();
		if (isBlank(request.getParameter("className"))) {
			errorMsgs.put("className", "請填寫假別名稱");
		}
		return errorMsgs;
	}

	private void updateContext() {
		List<LeaveClass> leaveclasslist = managerservice.getAll();
		Map<Integer, String> classMap = new HashMap<Integer, String>();
		for (LeaveClass leaveclass : leaveclasslist) {
			classMap.put((int) leaveclass.getClassId(),
					leaveclass.getClassName());
		}
		getServletContext().setAttribute("leaveclasslist", leaveclasslist);
		getServletContext().setAttribute("classMap", classMap);
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().length() == 0;
	}

	private boolean isNaturalNumbers(String number) {
		return number.matches("\\d*") && Integer.parseInt(number) > 0;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
