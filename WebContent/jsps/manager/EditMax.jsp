<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工假別時數設定</title>
<jsp:include page="/layout/meta.jsp" />
<jsp:include page="/layout/ManagerHeader.jsp" />
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-1 control-label"> <input type=radio
					name="radio" value=0>姓名
				</label> <label class="col-sm-2 control-label"><input type=radio
					name="radio" value=1>年度&假別 </label> <label id=name
					class="col-sm-1 control-label"> 姓名 </label>
				<div id=divname class="col-sm-2">
					<input class="form-control" name="name" type="text">
				</div>

				<label id=adyear class="col-sm-1 control-label">年度 </label>
				<div id=divadyear class="col-sm-2">
					<select class="form-control" name=adyear>
						<c:forEach items="${leaveadyear}" var="LeaveAdyear">
							<option>${LeaveAdyear.adyear}</option>
						</c:forEach>
							<option value="">ALL</option>
					</select>
				</div>

				<label id=class class="col-sm-1 control-label">假別 </label>
				<div id=divclass class="col-sm-2">
					<select class="form-control" name=className>
						<c:forEach items="${leaveclass}" var="LeaveClass">
							<option>${LeaveClass.className}</option>
						</c:forEach>
							<option value="">ALL</option>
					</select>
				</div>


				<div class="col-sm-2">
					<input type="hidden" name="act" value="Query" />
					<button type="submit" class="btn btn-primary" id=query>
						<span style="padding-right: 5px;"
							class="glyphicon glyphicon-search"></span>查詢
					</button>


				</div>

			</div>
		</form>






		<div class="starter-template">
			<table class="table table-hover"
				style="border-bottom: 2px solid #ddd">
				<thead class="thead-inverse">
					<tr>
						<th>姓名</th>
						<th>年度</th>
						<th>假別</th>
						<th>最大時數</th>
						<th>功能</th>
					</tr>
				</thead>

				<c:forEach items="${leaveusermaxlist}" var="LeaveMax">
					<tr>

						<th>${LeaveMax.name}</th>
						<th>${LeaveMax.adyear}</th>
						<th>${LeaveMax.className}</th>
						<th>${LeaveMax.approveMax}</th>

						<td><input type="hidden" id=name value="${LeaveMax.name}" />
							<input type="hidden" id=adyear value="${LeaveMax.adyear}" />
							<input type="hidden" id=className value="${LeaveMax.className}" />
							<input type="hidden" id=className value="${LeaveMax.userId}" />
							<input type="hidden" id=className value="${LeaveMax.adYearId}" />
							<input type="hidden" id=className value="${LeaveMax.classId}" />
							<input type="hidden" id=className value="${LeaveMax.approveMax}" />
							<button type="button"  class="list btn btn-link"
								data-toggle="modal" data-target="#myModal">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-th-list"></span>修改
							</button></td>
					</tr>
				</c:forEach>


			</table>
		</div>
	</div>
</body>

<!-- Modal 開始 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">年度最大假別時數</h4>
			</div>
			<div class="modal-body">
			
				<table class="table table-bordered">
					<thead class="thead-inverse">
						<tr>
							<th>姓名</th>
							<td><input type="text" name="Listname" id=Listname value="0" disabled/>
						</tr>
						<tr>
							<th>年度</th>
							<td><input type="text" name="Listadyear" id=Listadyear value="0" disabled/>
						</tr>
						<tr>
							<th>假別</th>
							<td><input type="text" name="ListclassName" id=ListclassName value="0" disabled/>
						</tr>
						<tr>
							<th>最大時數</th>
							<td><input type="text" name="ListapproveMax" id=ListapproveMax value="0"/>
						</tr>
					</thead>

				</table>
				
			</div>

			<div class="modal-footer" style="text-align: center;">
				<button type="button" class="btn btn-success" data-dismiss="modal">
					<span style="padding-right: 5px;" class="glyphicon glyphicon-ok"></span>確認
				</button>
				
				<button id="btnUpdate" type="submit" class="btn btn-primary" >
					<span style="padding-right: 5px;"
						class="glyphicon glyphicon-pencil"></span>修改
				</button>

			</div>

		</div>
	</div>
</div>
<script>
//onclick="location.href='${pageContext.request.contextPath}/ManagerServletMax?act=update'"
	$(function() {
		
		
		$('#name,#divname,#adyear,#divadyear,#class,#divclass').hide();
		$('#query').attr('disabled',true);//預設無法查詢
		$('input[name=radio]').click(function() {//顯示點選的radio
			var item_v = $('input[name=radio]:checked').val();//取得選取的radio
			if (item_v == 1) {//年度&假別
				$('#adyear,#divadyear,#class,#divclass').show();
				$('#name,#divname').hide();
				$('#query').attr('disabled',false);//打開 查詢建
			}
			if (item_v == 0) {//姓名
				$('#name,#divname').show();
				$('#adyear,#divadyear,#class,#divclass').hide();
				$('#query').attr('disabled',false);//打開 查詢建
				
			}
		});
		

		
		$('.list').click(function(){//點及修改列出資訊
			
			var name = $(this).prev().prev().prev().prev().prev().prev().prev().val();
			var adyear = $(this).prev().prev().prev().prev().prev().prev().val();
			var className =$(this).prev().prev().prev().prev().prev().val();
			var userId = $(this).prev().prev().prev().prev().val();
			var adYearId = $(this).prev().prev().prev().val();
			var classId =$(this).prev().prev().val();
			var approveMax=$(this).prev().val();
			$('#Listname').val(name);
			$('#Listadyear').val(adyear);
			$('#ListclassName').val(className);
			$('#ListapproveMax').val(approveMax);
			/*
			var dd =$('#ListapproveMax').keyup(function(){
				var ListapproveMax =$(this).val();
				alert(dd);
				
			});*/
			$('#btnUpdate').attr('disabled',true);
			$('#ListapproveMax').keyup(function () {
		    	if( this.value.trim()==""||this.value.trim()==null){
		    		$('#btnUpdate').attr('disabled',true);
		    	}
		    	else{
		    		$('#btnUpdate').attr('disabled',false);
		    	}
		        if (/[^0-9\.-]/g.test(this.value)) {
		            this.value = this.value.replace(/[^0-9\.-]/g, '');
		        }
		    });
			$('#btnUpdate').click(function(){
				var ListapproveMax=$('#ListapproveMax').val();
				alert(ListapproveMax);
				$.post("${pageContext.request.contextPath}/ManagerServletMax?act=update",
						{
							"userId" : userId,
							"adYearId" : adYearId,
							"classId" : classId,
							"approveMax":ListapproveMax
						}, function(data) {
							alert("修改成功");
									
						});
				
				 location.reload();
			});
			//alert(name);
			//alert(adyear);
			//alert(className);
			//alert(approveMax);
		});
		
		
		
	});
</script>
</html>