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


public class ManagerServletAdyear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerServiceInter managerservice = new ManagerServiceImpl();

	public ManagerServletAdyear() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("act");
		if ("listAdyear".equals(act)) {
			// 修改假別頁面
			List<LeaveAdyear> leaveadyearlist = null;
			try {
				leaveadyearlist = managerservice.getAllAdyear();// 取出所有假別
				request.setAttribute("leaveadyearlist", leaveadyearlist);// 放入leaveclasslist中
				// 頁面跳轉
				request.getRequestDispatcher("jsps/manager/EditAdYear.jsp")
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
			
			String adyear = request.getParameter("adyear");
			// 封裝
			 String x = new String(adyear.getBytes("ISO-8859-1"), "utf-8"); //亂碼解決
			 System.out.println("亂碼測試：" +x);
			 
			LeaveAdyear newleaveadyear =new LeaveAdyear();
			newleaveadyear.setAdyear(x);
			

			try {
				managerservice.addAdyear(newleaveadyear);//寫入新年度
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
				response.sendRedirect("ManagerServletAdyear?act=listAdyear");
				
			} catch (DataAccessException de) {
				// 數據訪問失敗
				request.setAttribute("err", de.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			
		}

	}




	private void doUpdateAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");//解決亂碼
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();

		Map<String, String> errorMsgs = validateRequest(request);
		String paramadyear = request.getParameter("adyearId");
		if (isBlank(paramadyear) || !isNaturalNumbers(paramadyear)) {
			errorMsgs.put("adyearId", "請填寫年度編號");
		}
		if (!errorMsgs.isEmpty()) {
			pw.write(new JSONObject(errorMsgs).toString());
			pw.flush();
			return;
		}

		LeaveAdyear leaveadyear = retrieveLeaveAdyear(request);
		leaveadyear
				.setAdyearId(Integer.parseInt(request.getParameter("adyearId")));
		managerservice.updateAdyear(leaveadyear);
		updateContext();
		pw.println(new JSONObject("{res:0}"));
		pw.flush();
	}

	private LeaveAdyear retrieveLeaveAdyear(HttpServletRequest request) {
		LeaveAdyear leaveadyear= new LeaveAdyear();
		leaveadyear.setAdyear(request.getParameter("adyear"));
		return leaveadyear;
	}

	private Map<String, String> validateRequest(HttpServletRequest request) {
		Map<String, String> errorMsgs = new HashMap<String, String>();
		if (isBlank(request.getParameter("adyear"))) {
			errorMsgs.put("adyear", "請填寫年度");
		}
		return errorMsgs;
	}

	private void updateContext() {
		List<LeaveAdyear> leaveadyearlist = managerservice.getAllAdyear();
		Map<Integer, String> adyearMap = new HashMap<Integer, String>();
		for (LeaveAdyear leaveadyear : leaveadyearlist) {
			adyearMap.put((int) leaveadyear.getAdyearId(),
					leaveadyear.getAdyear());
		}
		getServletContext().setAttribute("leaveadyearlist", leaveadyearlist);
		getServletContext().setAttribute("adyearMap", adyearMap);
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

