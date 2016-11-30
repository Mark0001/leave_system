<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.li_hover {
	background: #e8e8e8;
}
</style>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/LoginServlet?act=userLogin">員工請假系統</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li id="index"><a href="${pageContext.request.contextPath}/jsps/user/LeaveDescription.jsp">填寫假單</a></li>
				<li id="nomvc"><a href="${pageContext.request.contextPath}/UserServlet?act=initialQuery">查詢假單</a></li>
				<li id="empMvc"><a href="${pageContext.request.contextPath}/UserServlet?act=listEdit">修改假單</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> 設定 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/ManagerServlet?act=listClass">假別設定</a></li>
						<li><a href="${pageContext.request.contextPath}/ManagerServletAdyear?act=listAdyear">年度設定</a></li>
					<!--	<li><a href="#">員工設定</a></li>-->
						 <li><a href="${pageContext.request.contextPath}/ManagerServletMax?act=initialAndQuery">員工年度假別時數設定</a></li>
					</ul>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="index"><a href="${pageContext.request.contextPath}/LoginServlet?act=logout">登出</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
<script>
	$(function() {
		$('.nav li').hover(function() {
			$(this).addClass('li_hover');

		}, function() {
			$(this).removeClass('li_hover');

		});
		$.post("${pageContext.request.contextPath}/UserServlet?act=noticenoapprove",
				{
					"userId" : ${user.userId}
					
				
				}, function(data) {
					var img = $('a[href="${pageContext.request.contextPath}/UserServlet?act=listEdit"]').append('<img id =img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTWm8ooBn7Lni_ISJSXsn2ZQpDGuroiweAYH6rj3Mp6RWqoNzFgA" width="20"/>');
					if(data=="notice"){
						img
					}else{
						//remove(img);
						$('#img').remove();
					}
					});
	});
</script>
