<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>請假系統首頁</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/layout/meta.jsp" />
<jsp:include page="/layout/ManagerHeader.jsp" />

</head>
<body>
	<div class="jumbotron">
		<div class="container">

			<form class="form-horizontal" role="form">
				<div class="form-group">
					<h1>歡迎，親愛的 ${user.name}~</h1>
				</div>

				<div class="form-group">
					<label class="col-sm-1 control-label"><span
						class="glyphicon glyphicon-pushpin "></span>您有</label>
					<div class="col-sm-1">
						<input class="form-control" id="disabledInput" type="text"
							placeholder="${noapprovenumber}" disabled>
					</div>
					<label class="col-sm-2 control-label"> 筆假單未通過</label>
				</div>
				<c:forEach items="${leaveclasslist}" var="list">
					<div class="form-group">
						<label class="col-sm-1 control-label"><span
							class="glyphicon glyphicon-pushpin "></span> 尚有 </label>
						<div class="col-sm-1">
							<input class="form-control" id="disabledInput" type="text"
								placeholder="${list.remain}" disabled>
						</div>
						<label class="col-sm-2 control-label"> 小時${list.className}可請 </label>
					</div>
				</c:forEach>
			</form>
		</div>
	</div>
</body>
</html>