<%@ page language="java"
	import="java.util.List,com.tku.leave.domain.LeaveDetail,com.tku.leave.service.impl.AdministratorServiceImpl,com.tku.leave.service.inter.AdministratorServiceInter"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>查詢假單</title>
<jsp:include page="/layout/meta.jsp" />
<%if("0".equals( request.getSession().getAttribute("identity"))){  //主管跳轉%>
<jsp:include page="/layout/AdministratorHeader.jsp" />
<%}else if("1".equals( request.getSession().getAttribute("identity"))){  //管理員跳轉%>
<jsp:include page="/layout/ManagerHeader.jsp" />
<%}else if("2".equals( request.getSession().getAttribute("identity"))){// 員工跳轉%>
<jsp:include page="/layout/UserHeader.jsp" />
<%}else{
	response.sendRedirect("/test01/LoginServlet?act=logout"); //沒有身分進入網頁，跳回登入頁面
}%>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="inputPassword" class="col-sm-1 control-label">姓名 </label>
				<%if("0".equals( request.getSession().getAttribute("identity"))){  //主管跳轉%>
				<div class="col-sm-2">
					<input class="form-control" id="disabledInput" type="text" name="name">
				</div>
				<%}else{%>
				<div class="col-sm-2">
					<input class="form-control" id="disabledInput" type="text"
						placeholder="${user.name}" disabled>
				</div>
				<%}%>
				<label class="col-sm-1 control-label">年度 </label>
				<div class="col-sm-2">
					<select class="form-control" name=adyear>
						<c:forEach items="${leaveadyear}" var="LeaveAdyear">
							<option>${LeaveAdyear.adyear}</option>
						</c:forEach>
							<option value="">ALL</option>
					</select>
				</div>

				<div class="col-sm-2">
					<input type="hidden" name="act" value="Query" />
					<button type="submit" class="btn btn-primary">
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
						<th><input type="checkbox" name="CheckboxAll" id="CheckAll" /></th>
						<th>姓名</th>
						<th>假單編號</th>
						<th>申請時間</th>
						<th><button type="button" name="act" id="send" value="Excel" class="btn btn-success">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-plus"></span>匯出
							</button></th>
					</tr>
				</thead>
				<c:forEach items="${leavemainlist}" var="LeaveMain">
					<tr>
						<th><input type="checkbox"  value="${LeaveMain.mainId}"  id="checkbox" name="Checkbox" /></th>
						<th>${LeaveMain.name}</th>
						<th>${LeaveMain.mainId}</th>
						<th>${LeaveMain.applytime}</th>
						
						<td>
						<input type="hidden" value="${LeaveMain.mainId}" />
						<button type="button" class="list btn btn-link"
								data-toggle="modal" data-target="#myModal">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-th-list"></span>檢視
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
				<h4 class="modal-title" id="myModalLabel">假單詳細資料</h4>
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
					<tbody class=listDetail >
						
					</tbody>
					
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(function() {
		
		$(".list").click(function(){
		
				var mainId = $(this).prev().val();
				alert(mainId);
				
				
				//var newTr = $(document.createElement('tr')).attr("class", 'remove');
				//$(".listDetail").remove();
				$.post("${pageContext.request.contextPath}/UserServlet?act=listDetail",
								{
									"mainId" : mainId
								}, function(data) {
									alert(data);
									data = eval(data);//!!!!!!
									for(var i=0;i<data.length;i++){
						
								
								var Tr="<tr>\
											<th>"+ data[i].name+"</th>\
											<th>"+data[i].className+"</th>\
											<th>" +data[i].beginTime+"</th>\
											<th>" +data[i].endTime+"</th>\
											<th>"+data[i].leaveTime+"</th>\
											<th>"+data[i].reason+"</th>\
										</tr>";
										$(".listDetail").append(Tr);
									}				
								});
				});
			
		
		$('#myModal').on('hide.bs.modal', function () {//當關閉modal,執行
		  //alert("123");
		  $(".listDetail tr").remove();//移除tr下的 元素
		})
		
		 $("#CheckAll").click(function(){
	   if($("#CheckAll").prop("checked")){//如果全選按鈕有被選擇的話（被選擇是true）
	    $("input[name='Checkbox']").each(function(){
	     $(this).prop("checked",true);//把所有的核取方框的property都變成勾選
	    })
	   }else{
	    $("input[name='Checkbox']").each(function(){
	     $(this).prop("checked",false);//把所有的核方框的property都取消勾選
	    })
	   }
	  });
	

	 
	  $("#send").click(function(){
		  
		  $("input[name=Checkbox]:checked").each(function(){ //循環輸出值  
			  var mainId=$(this).val();
			 // alert("ID"+mainId);
			 $.post("${pageContext.request.contextPath}/UserServlet?act=Excel",
						{
							"mainId":mainId
						}, function(data) {
							//alert("OK");									
						});

		  
			}); 
		  alert("成功匯出至 C://");	
	  });
		  

		

	});
</script>
</html>