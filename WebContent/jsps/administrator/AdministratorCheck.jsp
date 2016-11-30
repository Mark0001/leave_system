<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>審核假單</title>

<style>
table tr th {
	width: 150px;
	text-align: right;
}

table tr td {
	text-align: left;
}

table tr td input,table tr td select {
	border: 1px solid #ddd;
	padding: 2px 5px;
}

.error {
	color: red;
	margin-left: 1.5em;
}

.error:before {
	content: '*';
	padding-right: 0.5em;
}
</style>
<jsp:include page="/layout/meta.jsp" />
<jsp:include page="/layout/AdministratorHeader.jsp" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						
						
						$("#pass")
								.click(//通過
										function() {
											var feedback = $("textarea").val();
											var mainId = $(
													'input[name="mainId"]')
													.val();//取得mainID
											window.location.href = '${pageContext.request.contextPath}/AdministratorServlet?act=check&approvestatus=1&mainId='+mainId+'&feedback='+feedback;
										});

						$("#refuse")
								.click(//不通過
										function() {
											var feedback = $("textarea").val();
											var mainId = $(
													'input[name="mainId"]')
													.val();//取得mainID
											window.location.href = '${pageContext.request.contextPath}/AdministratorServlet?act=check&approvestatus=2&mainId='+mainId+'&feedback='+feedback;
													
										});

					});
</script>


</head>
<body>

	<form
		action="${pageContext.request.contextPath}/AdministratorServlet?act=check"
		method="post">
		<div class="container">
			<div class="starter-template">
				<br> <br> <br>
				<h1 align="center">假單審核</h1>
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">
								員工 - 詳細假單資料<span class="dname"></span>
							</h4>
						</div>
						<div class="modal-body">
							<table class="table table-bordered">
								<thead class="thead-inverse">
									<tr>
										<th>姓名</th>
										<th>假別</th>
										<th>開始時間</th>
										<th>結束時間</th>
										<th>時數</th>
										<th>假由</th>
									</tr>
								</thead>
								<c:forEach items="${leavedetaillist}" var="LeaveDetail">
									<tr>
										<th>${LeaveDetail.name}</th>

										<th>${LeaveDetail.className}</th>
										<th>${LeaveDetail.beginTime}</th>
										<th>${LeaveDetail.endTime}</th>
										<th>${LeaveDetail.leaveTime}</th>
										<th>${LeaveDetail.reason}</th>


									</tr>
								</c:forEach>
								<div class="col-sm-2">
							</table>

							<label>回覆</label>
							<textarea class="form-control" rows="5"></textarea>
						</div>
						<input name="mainId" type="hidden" value="${param.mainId}" />
					</div>


					<div class="modal-footer" style="text-align: center;">
						<button id="pass" type="button" class="btn btn-primary">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-pencil"></span>通過
						</button>
						<button id="refuse" type="button" class="btn btn-danger">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-remove"></span>退回
						</button>

					</div>
				</div>
			</div>
		</div>
		</div>
	</form>
</body>
</html>