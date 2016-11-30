<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/layout/meta.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%if("0".equals( request.getSession().getAttribute("identity"))){  //主管跳轉%>
<jsp:include page="/layout/AdministratorHeader.jsp" />
<%}else if("1".equals( request.getSession().getAttribute("identity"))){  //管理員跳轉%>
<jsp:include page="/layout/ManagerHeader.jsp" />
<%}else if("2".equals( request.getSession().getAttribute("identity"))){// 員工跳轉%>
<jsp:include page="/layout/UserHeader.jsp" />
<%}else{
	response.sendRedirect("/test01/LoginServlet?act=logout"); //沒有身分進入網頁，跳回登入頁面
}%>

<title>請假說明</title>
</head>
<body>
<br>
<br>
<br>
<br>
<h1 align="center">請假說明</h1>
<h2 align="center">.....</h2>
<h2 align="center">.....</h2>
<h2 align="center">.....</h2>

<form  class="form-inline col-md-offset-5"  action="${pageContext.request.contextPath}/UserServlet?act=listLeave" method="post">

  <button  id="apply" type="submit" class="btn btn-default">填寫</button>
</form>

</body>
</html>