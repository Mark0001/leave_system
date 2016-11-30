<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8>
<meta http-equiv=X-UA-Compatible content="IE=edge">
<meta name=viewport content="width=device-width, initial-scale=1">
<meta name=description content="">
<meta name=author content="">

<title>登入頁面</title>
<!--<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap3/js/bootstrap.min.js"></script>
<script src="$js/jquery-1.11.1.js"></script>  -->
<jsp:include page="/layout/meta.jsp" />
<body>
	<div class="container ">
	<div class="col-md-offset-4 col-md-4">
		<form class=form-signin action="http://localhost:8080/test01/LoginServlet?act=login" method="post">
			<h1 class="form-siganin-heading text-center">請假系統登入</h1>
			<br>
			<div class="form-group ">
				<label for=inputEmail class="sr-only">帳號 : </label> <input
					id=inputEmail class="form-control" placeholder="請輸入帳號" name="account" required
					autofocus>
			</div>
			
			<div class="form-group">
				<label for=inputPassword class=sr-only>密碼 :</label> <input
					type=password id=inputPassword class="form-control"
					placeholder="請輸入密碼" name="password" required>
			</div>
			
	
			<br>
			<button class="btn btn-lg btn-primary btn-block" type=submit>登入</button>
		</form>
	</div>
	</div>
	<script src=/Scripts/AssetsBS3/ie10-viewport-bug-workaround.js></script>