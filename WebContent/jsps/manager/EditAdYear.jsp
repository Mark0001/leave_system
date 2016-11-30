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
		
			<table id="adyears" class="table table-hover"
				style="border-bottom: 2px solid #ddd">
				<thead class="thead-inverse">
					<tr>
						<th>年度編號</th>
						<th>西元年</th>
						<th><button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/jsps/manager/AddAdyear.jsp';return false;">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-plus"></span>新增
							</button></th>
					</tr>
				</thead>



				<c:forEach items="${leaveadyearlist}" var="LeaveAdyear">

					<c:set var="LeaveAdyear" value="${LeaveAdyear}" scope="page" />
					<%
						pageContext.setAttribute("adyearJSON",
									new JSONObject(pageContext.getAttribute("LeaveAdyear")));
					%>

					<tr data-adyearId="${LeaveAdyear.adyearId}" >
						<td>${LeaveAdyear.adyearId}</td>
						<td>${LeaveAdyear.adyear}</td>





						<td><button type="button" class="btn btn-danger"
								data-adyeare='${adyearJSON}' data-toggle="modal"
								data-target="#upateadyearModal">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-pencil btnShowAdyear"></span>修改
							</button></td>

					</tr>
				</c:forEach>
			</table>


		</div>
	</div>


	<script type="text/javascript">
		//alert("12333");
		      $(function() {
		    	  //alert("123");
		            var adyearMap = JSON.parse('<%=new JSONObject((java.util.Map) application.getAttribute("adyearMap"))%>');
			$("#upateadyearModal").attr({
				'data-keyboard' : false

			}).on(
					'show.bs.modal',
					function(event) {
						var btn = $(event.relatedTarget); // Button that triggered the modal
						var adyeare = btn.data('adyeare');
						var $adyearTable = $('#upateadyearModal');
						for ( var key in adyeare) {
							switch (key) {
							case 'adyearId':
							case 'adyear':
								$adyearTable.find('span.' + key).html(adyeare[key]);
							default:
								$adyearTable.find('input[name="' + key + '"]')
										.val(adyeare[key]);
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
								var $form = $('#upateadyearModal form');
								var adyearId = $('#upateadyearModal .adyearId').html();
								$
										.ajax({
											url : '${pageContext.request.contextPath}/ManagerServletAdyear',//修改
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
														var adyearTds = $('#adyears tr[data-adyearId="'+ adyearId+ '"]').addClass('edited').find('td');
														// for update View Button
														var editedAdyear = {};
														var attrs = [ 'adyearId','adyear', 'loc' ];
														for ( var i = 0; i < attrs.length; i++) {
															var value = $form.find('input,select').filter('[name="'+ attrs[i]+ '"]').val();
															editedAdyear[attrs[i]] = value;
															$(adyearTds[i]).html(value);
														}
														$(adyearTds[adyearTds.length - 1]).find('.btnShowAdyear').data('adyeare',JSON.stringify(editedAdyear));
														$('#upateadyearModal .close').click();
														} else 
														
														{
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
	
	<div id="upateadyearModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">&times;</a>
					<h4 class="modal-title">
						年度資料 - <span class="adyear"></span>
					</h4>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/ManagerServletAdyear?act=listAdyear"
						method="post" autocomplete="off">
						<table class="table table-bordered">
							<tr>
								<th>年度編號</th>
								<td><span class="adyearId"></span></td>
							</tr>
							<tr>
								<th>西元年</th>
								<td><input type="text" name="adyear" value="${param.adyear}" /><span
									class="error hide"></span></td>
							</tr>
						
						</table>
						<input type="hidden" name="act" value="update" /> <input
							type="hidden" name="adyearId" class="adyearId" />
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