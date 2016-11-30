<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>新增年度</title>

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
<jsp:include page="/layout/ManagerHeader.jsp" />
<script type="text/javascript">
$(document).ready(function() {

$('#btnInsert').attr('disabled',true);
    $('#adyear1').keyup(function () {
    	if( this.value.trim()==""){
    		$('#btnInsert').attr('disabled',true);
    	}
    	else{
    		$('#btnInsert').attr('disabled',false);
    	}
        if (/[^0-9\.-]/g.test(this.value)) {
            this.value = this.value.replace(/[^0-9\.-]/g, '');
        }
        var adyearName = $('#adyear1').val();
		$.ajax({
			type : 'POST',
   	        data:{"adyearName":adyearName},
			url:'${pageContext.request.contextPath}/ManagerServlet?act=checkad',
			success:function(result){
				
				if(result=="true"){
					$('#snotip').text('可使用');
					$('#btnInsert').attr('disabled',false);
				}else {
					$('#snotip').text('資料重複，無法使用');
					$('#btnInsert').attr('disabled',true);
				}
			}
			
		});
    });
    
});
</script>
</head>
<body>

	<form action="${pageContext.request.contextPath}/ManagerServletAdyear?act=add" method="post">
		<div class="container">
			<div class="starter-template">
				<br> <br> <br>
				<h1 align="center">年度設定</h1>
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">
								DEPT - 新增年度<span class="dname"></span>
							</h4>
						</div>
						<div class="modal-body">
							<table class="table table-bordered">
								<tr>
									<th>西元年</th>
									<td><input type="text" name="adyear" id="adyear1"/><span
										id="snotip"></span></td>
								</tr>

							</table>
						</div>
						<div class="modal-footer" style="text-align: center;">
							<button id="btnInsert" type="submit" class="btn btn-primary">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-pencil"></span>新增
							</button>
							<button id="btncancel" type="button" class="btn btn-danger"
								onclick="location.href='${pageContext.request.contextPath}/ManagerServletAdyear?act=listAdyear';">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-remove"></span>取消
							</button>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>