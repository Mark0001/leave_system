<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>假單審核</title>
<jsp:include page="/layout/meta.jsp" />
<jsp:include page="/layout/AdministratorHeader.jsp" />
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$(".btn").click(function(){
  				var mainId = $(this).prev().val();
  				alert(mainId);
  				
  				window.location.href = '${pageContext.request.contextPath}/AdministratorServlet?act=listDetail&mainId='+mainId;
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
						<th>姓名</th>
						<th>申請時間</th>
						<th>狀態</th>
						<th>功能</th>
					</tr>
				</thead>

<c:forEach items="${leavemainlist}" var="LeaveMain">
				<tr>
					<th>${LeaveMain.name}</th>
					
					<th>${LeaveMain.applytime}</th>
					<c:if test="${LeaveMain.approveStatus == '0'}">
					<th>未審核</th>
					</c:if>
					<td>
					<input type="hidden" value="${LeaveMain.mainId}"/>
					<button type="button" class="btn btn-danger ">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-pencil"></span>審核
						</button></td>
						</tr>
							</c:forEach>
	

			</table>
		</div>
	</div>
	
</body>
</html>