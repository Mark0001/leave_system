<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改假單首頁</title>
<jsp:include page="/layout/meta.jsp" />
<%
	if ("0".equals(request.getSession().getAttribute("identity"))) { //主管跳轉
%>
<jsp:include page="/layout/AdministratorHeader.jsp" />
<%
	} else if ("1"
			.equals(request.getSession().getAttribute("identity"))) { //管理員跳轉
%>
<jsp:include page="/layout/ManagerHeader.jsp" />
<%
	} else if ("2"
			.equals(request.getSession().getAttribute("identity"))) {// 員工跳轉
%>
<jsp:include page="/layout/UserHeader.jsp" />
<%
	} else {
		response.sendRedirect("/test01/LoginServlet?act=logout"); //沒有身分進入網頁，跳回登入頁面
	}
%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(".btn")
								.click(
										function() {
											var mainId = $(this).prev().val();
											alert(mainId);

											window.location.href = '${pageContext.request.contextPath}/UserServlet?act=editDetailList&mainId='
													+ mainId;
											//打开一个页面

										});

					});
</script>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<div class="container">

		<div class="starter-template">
			<table class="table table-hover"
				style="border-bottom: 2px solid #ddd">
				<thead class="thead-inverse">
					<tr>
						<th>假單編號</th>
						<th>申請時間</th>
						<th>審核狀態</th>
						<th>功能</th>

					</tr>
				</thead>
				<c:forEach items="${leavemainlist}" var="LeaveMain">
					<tr>

						<th>${LeaveMain.mainId}</th>
						<th>${LeaveMain.applytime}</th>
						<c:if test="${LeaveMain.approveStatus == '2'}">
							<th>退回</th>
						</c:if>


						<td><input type="hidden" value="${LeaveMain.mainId}" />
							<button type="button" class="btn btn-danger ">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-th-list"></span>修改/註銷
							</button></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>
</html>