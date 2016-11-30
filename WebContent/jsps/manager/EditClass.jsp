<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>假別設定</title>
<jsp:include page="/layout/meta.jsp" />
<jsp:include page="/layout/ManagerHeader.jsp" />
<!--<script type="text/javascript" src="jquery/jquery-1.11.1.js"></script>-->

<style>

  tr.edited td {
        background-color: #EC971F;
        color: white;
</style>

</head>
<body>
	<br>
	<br>
	<br>
	<br>

	<div class="container">

		<div class="starter-template">
		
			<table id="classes" class="table table-hover"
				style="border-bottom: 2px solid #ddd">
				<thead class="thead-inverse">
					<tr>
						<th>假別編號</th>
						<th>假別名稱</th>
						<th><button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/jsps/manager/AddClass.jsp';return false;">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-plus"></span>新增
							</button></th>
					</tr>
				</thead>



				<c:forEach items="${leaveclasslist}" var="LeaveClass">

					<c:set var="LeaveClass" value="${LeaveClass}" scope="page" />
					<%
						pageContext.setAttribute("classJSON",
									new JSONObject(pageContext.getAttribute("LeaveClass")));
					%>

					<tr data-classId="${LeaveClass.classId}" >
						<td>${LeaveClass.classId}</td>
						<td>${LeaveClass.className}</td>





						<td><button type="button" class="btn btn-danger"
								data-classe='${classJSON}' data-toggle="modal"
								data-target="#upateclassModal">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-pencil btnShowClass"></span>修改
							</button></td>

					</tr>
				</c:forEach>
			</table>
${classJSON}

		</div>
	</div>


	<script type="text/javascript">
		//alert("12333");
		      $(function() {
		    	  //alert("123");
		            var classMap = JSON.parse('<%=new JSONObject((java.util.Map) application.getAttribute("classMap"))%>');
			$("#upateclassModal").attr({
				'data-keyboard' : false

			}).on(
					'show.bs.modal',
					function(event) {
						var btn = $(event.relatedTarget); // Button that triggered the modal
						var classe = btn.data('classe');
						var $classTable = $('#upateclassModal');
						for ( var key in classe) {
							switch (key) {
							case 'classId':
							case 'className':
								$classTable.find('span.' + key).html(classe[key]);
							default:
								$classTable.find('input[name="' + key + '"]')
										.val(classe[key]);
								break;
							}
						}
					}).on('hide.bs.modal', function(event) {
				$('span.error').empty().addClass('hide');
			});

			$('#btnUpdate')
					.on(
							'click',
							function() {
								//alert("12333");
								var $form = $('#upateclassModal form');
								var classId = $('#upateclassModal .classId').html();
								$
										.ajax({
											url : '${pageContext.request.contextPath}/ManagerServlet',//修改
											method : 'post',
											data : $form.serialize()
										})
										.done(
												function(msg) {
													var result = JSON
															.parse(msg);
													if (result.res == 0) {
														alert('修改成功!');
														$('.delete').removeClass('delete');
														$('.edited').removeClass('edited');
														var classTds = $('#classes tr[data-classId="'+ classId+ '"]').addClass('edited').find('td');
														// for update View Button
														var editedClass = {};
														var attrs = [ 'classId','className', 'loc' ];
														for ( var i = 0; i < attrs.length; i++) {
															var value = $form.find('input,select').filter('[name="'+ attrs[i]+ '"]').val();
															editedClass[attrs[i]] = value;
															$(classTds[i]).html(value);
														}
														$(classTds[classTds.length - 1]).find('.btnShowClass').data('classe',JSON.stringify(editedClass));
														$('#upateclassModal .close').click();
														} else {
															$('span.error').empty().addClass('hide');
														for ( var key in result) {
															$('input,select').filter('[name="'+ key+ '"]').next('span.error').html(result[key]).removeClass('hide');
															}
														}
														}).fail(function() {
																alert('修改失敗！');
													});
												});

	
											});
	</script>




	<!--修改 Modal -->
	<script  type="text/javascript">
	$(document).ready(function(){
		$('#updateblock').blur(function(){
			
			var className = $('#updateblock').val();
			if(className.trim()==""||className.trim()==null){
				$('#btnUpdate').attr('disabled',true);
			}
		});
		
		$('#updateblock').keyup(function(){
			var className = $('#updateblock').val();
			if(className==null){//if  null  lock submit
				$('#btnUpdate').attr('disabled',true);
			}
			else{									//can submit
				$('#btnUpdate').attr('disabled',false);
			}
			$.ajax({
				type : 'POST',
	   	        data:{"className":className},
				url:'${pageContext.request.contextPath}/ManagerServlet?act=check',
				success:function(result){
					
					if(result=="true"){
						$('#errortip').text('');
						$('#btnUpdate').attr('disabled',false);
					}else {
						$('#errortip').text('資料重複，無法使用');
						$('#btnUpdate').attr('disabled',true);
					}
				}	
			});
		});
	});
</script>
<!-- 互動式窗開始 -->
	<div id="upateclassModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">&times;</a>
					<h4 class="modal-title">
						假別資料 - <span class="className"></span>
					</h4>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/ManagerServlet?act=listClass"
						method="post" autocomplete="off">
						<table class="table table-bordered">
							<tr>
								<th>假別編號</th>
								<td><span class="classId"></span></td>
							</tr>
							<tr>
								<th>假別名稱</th>
								<td><input id="updateblock" type="text" name="className" value="${param.className}" />
								<span class="error hide"></span>
								<span id="errortip" ></span></td>
									
							</tr>
						
						</table>
						<input  type="hidden" name="act" value="update" /> <input
							type="hidden" name="classId" class="classId" />
					</form>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-success" data-dismiss="modal">
						<span style="padding-right: 5px;" class="glyphicon glyphicon-ok"></span>確認
					</button>
					<button id="btnUpdate" type="button" class="btn btn-primary">
						<span style="padding-right: 5px;"
							class="glyphicon glyphicon-pencil"></span>修改
					</button>
				
				</div>
			</div>
		</div>
	</div>




</body>



</html>