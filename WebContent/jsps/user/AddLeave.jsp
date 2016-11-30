<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填寫假單</title>
<jsp:include page="/layout/meta.jsp" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap-datetimepicker.min.js"></script>

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
	<%= request.getSession().getAttribute("user") %>
	<!-- 以後要刪! 身分識別 -->
	<br>
	<%= request.getSession().getAttribute("identity") %>
	<div class="container">
		<form id="myform" class="form-horizontal"
			action="${pageContext.request.contextPath}/UserServlet?act=submit"
			method="post">

			<div class="form-group" id=group>

				
				<ppp id="ppp"></ppp>

			</div>

			<div class="col-sm-2">
				<input type="hidden" name="counter" id="counter" value="1" />
				<button id=btn type="submit" class="btn btn-primary">
					<span style="padding-right: 5px;" class="glyphicon glyphicon-file"></span>送出
				</button>
			</div>
		</form>

		<div class="col-sm-2">
			<button id=addButton class="btn btn-success">
				<span style="padding-right: 5px;" class="glyphicon glyphicon-plus"></span>新增
			</button>
		</div>
		<div class="col-sm-2">
			<button id=removeButton type="submit" class="btn btn-danger">
				<span style="padding-right: 5px;" class="glyphicon glyphicon-minus"></span>刪除
			</button>
		</div>
	</div>

	<script>	
		
	 $(function(){
		 
	
			var counter_time = 1;
			
			var counter = 1;

		    $("#addButton").click(function () {
		    	
		    $("#counter").val(counter);//紀錄counter
		   
		   // alert( $("#counter").val());
				 
			if(counter>10){
		            alert("Only 10 textboxes allow");
		            return false;
			}   
				
			var newTextBoxDiv = $(document.createElement('div'))
			     .attr("id", 'TextBoxDiv' + counter);
		                
			newTextBoxDiv.after().html
			('<div class="col-sm-2"><label>'+'姓名 </label>'+
					'<input class="form-control" name="name'+counter+'" id="disabledInput" type="text"placeholder="${user.name}" disabled></div>'+	
				'<div class="col-sm-2">'+
				'<label > 假別 </label>'+
					'<select class="form-control" name="select'+counter+'" id="select'+counter+'" >'+
					'<c:forEach items="${leaveclasslist}" var="LeaveClass">'+
						'<option>${LeaveClass.className}</option>'+
						'</c:forEach>'+
					'</select>'+
				'</div>'+
				'<div class="col-sm-2">'+
				'<label>開始時間</label>'+
					'<input class="form-control" name="begin'+counter+'" id="begintime'+counter+'"  type="text"  />'+ 
				'</div>'+
				'<div class="col-sm-2">'+
				'<label>結束時間</label>'+
					'<input class="form-control" name="end'+counter+'" type="text" id="endtime'+counter+'"  />'+ 
				'</div>'+
				'<div class="col-sm-2">'+
				'<label>請假時數</label>'+
					'<input class="form-control" name="leavetime'+counter+'" type="text" id="leavetime'+counter+'"  readonly="readonly" /> '+
				'</div>'+		
				'<div class="col-sm-2">'+
				'<label>假由</label>'+
					'<textarea id="reason'+counter+'" name="reason'+counter+'" class="form-control" rows="1"></textarea>'+
				'</div>');	
			newTextBoxDiv.appendTo("#ppp");	///新增至區塊	
			
			for(var i=1;i<=counter;i++){///////////添上可點選的月曆
				$('#begintime'+i).datetimepicker({
				});
				$('#endtime'+i).datetimepicker({
				});
			}
			
//////////////////////////////////////////////////////////////////////////////////下方區塊的
	    	for ( var i = 1; i <= counter; i++) {
	    	 $('#endtime'+i+",begintime"+i)
				.on('change',
						function() {
							for ( var i = 1; i <= counter; i++) {
								var endtime = new Date($("#endtime" + i).val()); //結束時間
								var begintime = new Date($("#begintime" + i).val());//開始時間
								var starthouroflastday = new Date(endtime);
								starthouroflastday = starthouroflastday
										.setHours(9);//一天的開始時間  9點
								var endhouroffirstday = new Date(begintime);
								endhouroffirstday = endhouroffirstday
										.setHours(18);//一天的結束時間 18點
								var lunch1 = new Date(begintime);
								lunch1 = lunch1.setHours(12); //午休開始 12點
								var lunch2 = new Date(endtime);
								lunch2 = lunch2.setHours(13); //午休結束 13點

								var changetohour = 1000 * 60 * 60; //毫秒轉小時
								var difftime = 0; //請假時數

								if (begintime >= endtime) {
										
									//alert("起始時間晚於結束時間");
									$("#leavetime"+i).val("起始時間晚於結束時間");
									return false;
								}
								if (begintime.getHours() < 9
										|| endtime.getHours() > 18) {

									//alert("非正常上班時間"); //請假時間超出9~18的範圍
									$("#leavetime"+i).val("非正常上班時間");
									return false;
								}
								if (begintime.getHours() > 18
										|| endtime.getHours() <9 ) {

									//alert("非正常上班時間"); //請假時間超出9~18的範圍
									$("#leavetime"+i).val("非正常上班時間");
									return false;
								}
								if(begintime.getHours()<13&&begintime.getHours()>=12)
								{
									$("#leavetime"+i).val("非正常上班時間");
									return false;
								}
									
								if(endtime.getHours()==18 && endtime.getMinutes()!=0)
								{
									$("#leavetime"+i).val("非正常上班時間");
									return false;
								}
								if (begintime.getFullYear() != endtime
										.getFullYear()) {

									//alert("請什麼請 去上班"); //請假跨年
									$("#leavetime"+i).val("請什麼請 去上班");
									return false;
								}
								//只請一天
								if (endtime.getDate() == begintime
										.getDate()
										&& endtime.getMonth() == begintime
												.getMonth()) {
									if (endtime >= lunch2
											&& lunch1 >= begintime) { //請假經過午休的修正
										difftime = ((endtime - begintime) / changetohour) - 1;
										//alert("1 總請假時數: " + difftime		+ " HR");
										$("#leavetime"+i).val(difftime);
									}//請假經過午休的修正 
									else {
										difftime = ((endtime - begintime) / changetohour);
										//alert("2 總請假時數: " + difftim			+ " HR");
										$("#leavetime"+i).val(difftime);
									}//請假沒有經過午休
								}

								if (endtime.getDate() != begintime
										.getDate()
										&& //同月跨日請假
										endtime.getMonth() == begintime
												.getMonth()) {
									if (endtime >= lunch2
											&& lunch1 >= begintime) { //頭尾2天經過午休
										difftime = ((endtime - starthouroflastday) + //最後一天請的時間
										(endhouroffirstday - begintime) //第一天請的時間
										)
												/ changetohour //毫秒轉換小時
												+ (((endtime.getDate() - begintime
														.getDate()) - (1)) * 8)
												- 2;
										//天數差*8 中間請的時數 -2是頭尾2天午休的修正
										//30分的修正
										if (endtime.getMinutes() > begintime
												.getMinutes())
											difftime = difftime + 0.5;
										if (endtime.getMinutes() < begintime
												.getMinutes())
											difftime = difftime - 0.5;

										//alert("3 總請假時數: " + difftime
										//		+ " HR");
										$("#leavetime"+i).val(difftime);
									} else if (endtime >= lunch2
											|| lunch1 >= begintime) {
										difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
												/ changetohour //頭尾2天請假時數
												+ (((endtime.getDate() - begintime
														.getDate()) - (1)) * 8)
												- 1;
										//中間天數的請假時數 -1是午休修正
										if (endtime.getMinutes() > begintime
												.getMinutes())
											difftime = difftime + 0.5;
										if (endtime.getMinutes() < begintime
												.getMinutes())
											difftime = difftime - 0.5;

										//alert("4 總請假時數: " + difftime
										//		+ " HR");
										$("#leavetime"+i).val(difftime);
									} else if (endtime <= lunch2
											|| lunch1 <= begintime) {
										difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
												/ changetohour//頭尾2天請假時數
												+ (((endtime.getDate() - begintime
														.getDate()) - (1)) * 8);
										//中間天數的請假時數
										if (endtime.getMinutes() > begintime
												.getMinutes())
											difftime = difftime + 0.5;
										if (endtime.getMinutes() < begintime
												.getMinutes())
											difftime = difftime - 0.5;

										//alert("5 總請假時數: " + difftime
										//		+ " HR");
										$("#leavetime"+i).val(difftime);
									}
								}
								if (endtime.getMonth() != begintime
										.getMonth()) { //請假跨月
									var firstleavemonth = new Date(
											begintime); //起始請假的月份
									firstleavemonth = firstleavemonth
											.getMonth();
									var whatyaer = new Date(begintime);//請假年份
									whatyaer = whatyaer.getFullYear();
									var endofstartmonth = new Date(
											begintime);//起始月份的最後一天
									var startoflastmonth = new Date(endtime);//設定結束月份的第一天
									startoflastmonth.setDate(1);

									switch (endofstartmonth.getMonth()) //判斷幾月份
									{
									case 0:
									case 2:
									case 4:
									case 6:
									case 7:
									case 9:
									case 11:
										endofstartmonth.setDate(31); //大月31天
										break;
									case 3:
									case 5:
									case 8:
									case 10:
										endofstartmonth.setDate(30);//小月30天
										break;
									case 1:
										if (endofstartmonth.getFullYear() % 4 == 0) {//閏年判斷
											endofstartmonth.setDate(29);
											break;
										} else {
											endofstartmonth.setDate(28);
											break;
										}
									}

									if (endtime >= lunch2
											&& lunch1 >= begintime) {
										difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
												/ changetohour //頭尾2天請假時數
												+ (((endofstartmonth
														.getDate() - begintime
														.getDate())
												//起始月份的最後一天-起始日期
												+ (endtime.getDate() - startoflastmonth
														.getDate())) * 8)
												- 2;
										//結束日期-結束月份的第一天 午休修正

										if (endtime.getMinutes() > begintime
												.getMinutes())
											difftime = difftime + 0.5;
										if (endtime.getMinutes() < begintime
												.getMinutes())
											difftime = difftime - 0.5;

										//alert("6 總請假時數: " + difftime
										//		+ " HR");
										$("#leavetime"+i).val(difftime);
									}

									else if (endtime >= lunch2
											|| lunch1 >= begintime) {
										difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
												/ changetohour //頭尾2天請假時數
												+ (((endofstartmonth
														.getDate() - begintime
														.getDate())
												//起始月份的最後一天-起始日期
												+ (endtime.getDate() - startoflastmonth
														.getDate())) * 8)
												- 1;
										//結束日期-結束月份的第一天  午休修正
										if (endtime.getMinutes() > begintime
												.getMinutes())
											difftime = difftime + 0.5;
										if (endtime.getMinutes() < begintime
												.getMinutes())
											difftime = difftime - 0.5;

										//alert("7 總請假時數: " + difftime
										//		+ " HR");
										$("#leavetime"+i).val(difftime);
									} else if (endtime <= lunch2
											|| lunch1 <= begintime) {
										difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
												/ changetohour //頭尾2天請假時數
												+ (((endofstartmonth
														.getDate() - begintime
														.getDate())
												//起始月份的最後一天-起始日期
												+ (endtime.getDate() - startoflastmonth
														.getDate())) * 8);
										//結束日期-結束月份的第一天
										if (endtime.getMinutes() > begintime
												.getMinutes())
											difftime = difftime + 0.5;
										if (endtime.getMinutes() < begintime
												.getMinutes())
											difftime = difftime - 0.5;

										//alert("8 總請假時數: " + difftime
										//		+ " HR");
										$("#leavetime"+i).val(difftime);

									}

								}
								//alert("開始時間 : "+begintime + "\n" +
								// 	"結束時間 : "+endtime + "\n");
								var leaveTime=$("#leavetime"+i).val();
							//	alert(leaveTime);
								var className = $("#select"+i).val();
							//	alert(className);
								$.post("${pageContext.request.contextPath}/UserServlet?act=checkApproveMax",
										{
											"className" : className,
											"leaveTime": leaveTime,
										
										}, function(data) {
											if(data!=""){
											alert("超過可請時數 ，"+className+" 剩下 "+data+" (hr)可請");
											}
											});
								
							}
						});
	    }
	    	counter_time++;
			counter++;
	     
	     ///////////////////////////////
		    });
		    $("#addButton").click();
		     $("#removeButton").click(function () {
			if(counter==2){
		          alert("無法刪除");
		          return false;        
		       }
			counter_time--;
			counter--;		
		        $("#TextBoxDiv" + counter).remove();		
		     });

		$('#btn')
					.on(
							'click',
							function() {
								for ( var i = 1; i <= counter_time; i++) {
									var endtime = new Date($("#endtime" + i)
											.val()); //結束時間
									var begintime = new Date(
											$("#begintime" + i).val());//開始時間
									var endtime_check = $("#endtime" + i).val();
									var begintime_check = $("#begintime" + i)
											.val();
									if (begintime_check.trim() == ""
											|| endtime_check.trim() == "") {
										alert('請輸入時間');
										return false;
									}
									var starthouroflastday = new Date(endtime);
									starthouroflastday = starthouroflastday
											.setHours(9);//一天的開始時間  9點
									var endhouroffirstday = new Date(begintime);
									endhouroffirstday = endhouroffirstday
											.setHours(18);//一天的結束時間 18點
									var lunch1 = new Date(begintime);
									lunch1 = lunch1.setHours(12); //午休開始 12點
									var lunch2 = new Date(endtime);
									lunch2 = lunch2.setHours(13); //午休結束 13點

									var changetohour = 1000 * 60 * 60; //毫秒轉小時
									var difftime = 0; //請假時數

									if (begintime >= endtime) {

										alert("起始時間晚於結束時間");
										return false;
									}
									if (begintime.getHours() < 9
											|| endtime.getHours() > 18) {

										alert("非正常上班時間"); //請假時間超出9~18的範圍
										return false;
									}
									if (begintime.getHours() > 18
											|| endtime.getHours() <9 ) {

										alert("非正常上班時間"); //請假時間超出9~18的範圍
										return false;
									}
									if(begintime.getHours()<13&&begintime.getHours()>=12)
									{
										$("#leavetime"+i).val("非正常上班時間");
										return false;
									}
									if(endtime.getHours()==18 && endtime.getMinutes()!=0)
									{
										alert("非正常上班時間");
										return false;
									}
									if (begintime.getFullYear() != endtime
											.getFullYear()) {

										alert("請什麼請 去上班"); //請假跨年
										return false;
									}
									//只請一天
									if (endtime.getDate() == begintime
											.getDate()
											&& endtime.getMonth() == begintime
													.getMonth()) {
										if (endtime >= lunch2
												&& lunch1 >= begintime) { //請假經過午休的修正
											difftime = ((endtime - begintime) / changetohour) - 1;
											//alert("1 總請假時數: " + difftime
											//		+ " HR");
										}//請假經過午休的修正 
										else {
											difftime = ((endtime - begintime) / changetohour);
											//alert("2 總請假時數: " + difftime
											//		+ " HR");
										}//請假沒有經過午休
									}

									if (endtime.getDate() != begintime
											.getDate()
											&& //同月跨日請假
											endtime.getMonth() == begintime
													.getMonth()) {
										if (endtime >= lunch2
												&& lunch1 >= begintime) { //頭尾2天經過午休
											difftime = ((endtime - starthouroflastday) + //最後一天請的時間
											(endhouroffirstday - begintime) //第一天請的時間
											)
													/ changetohour //毫秒轉換小時
													+ (((endtime.getDate() - begintime
															.getDate()) - (1)) * 8)
													- 2;
											//天數差*8 中間請的時數 -2是頭尾2天午休的修正
											//30分的修正
											if (endtime.getMinutes() > begintime
													.getMinutes())
												difftime = difftime + 0.5;
											if (endtime.getMinutes() < begintime
													.getMinutes())
												difftime = difftime - 0.5;

											//alert("3 總請假時數: " + difftime
											//		+ " HR");
										} else if (endtime >= lunch2
												|| lunch1 >= begintime) {
											difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
													/ changetohour //頭尾2天請假時數
													+ (((endtime.getDate() - begintime
															.getDate()) - (1)) * 8)
													- 1;
											//中間天數的請假時數 -1是午休修正
											if (endtime.getMinutes() > begintime
													.getMinutes())
												difftime = difftime + 0.5;
											if (endtime.getMinutes() < begintime
													.getMinutes())
												difftime = difftime - 0.5;

											//alert("4 總請假時數: " + difftime
													//+ " HR");
										} else if (endtime <= lunch2
												|| lunch1 <= begintime) {
											difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
													/ changetohour//頭尾2天請假時數
													+ (((endtime.getDate() - begintime
															.getDate()) - (1)) * 8);
											//中間天數的請假時數
											if (endtime.getMinutes() > begintime
													.getMinutes())
												difftime = difftime + 0.5;
											if (endtime.getMinutes() < begintime
													.getMinutes())
												difftime = difftime - 0.5;

											//alert("5 總請假時數: " + difftime
											//		+ " HR");
										}
									}
									if (endtime.getMonth() != begintime
											.getMonth()) { //請假跨月
										var firstleavemonth = new Date(
												begintime); //起始請假的月份
										firstleavemonth = firstleavemonth
												.getMonth();
										var whatyaer = new Date(begintime);//請假年份
										whatyaer = whatyaer.getFullYear();
										var endofstartmonth = new Date(
												begintime);//起始月份的最後一天
										var startoflastmonth = new Date(endtime);//設定結束月份的第一天
										startoflastmonth.setDate(1);

										switch (endofstartmonth.getMonth()) //判斷幾月份
										{
										case 0:
										case 2:
										case 4:
										case 6:
										case 7:
										case 9:
										case 11:
											endofstartmonth.setDate(31); //大月31天
											break;
										case 3:
										case 5:
										case 8:
										case 10:
											endofstartmonth.setDate(30);//小月30天
											break;
										case 1:
											if (endofstartmonth.getFullYear() % 4 == 0) {//閏年判斷
												endofstartmonth.setDate(29);
												break;
											} else {
												endofstartmonth.setDate(28);
												break;
											}
										}

										if (endtime >= lunch2
												&& lunch1 >= begintime) {
											difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
													/ changetohour //頭尾2天請假時數
													+ (((endofstartmonth
															.getDate() - begintime
															.getDate())
													//起始月份的最後一天-起始日期
													+ (endtime.getDate() - startoflastmonth
															.getDate())) * 8)
													- 2;
											//結束日期-結束月份的第一天 午休修正

											if (endtime.getMinutes() > begintime
													.getMinutes())
												difftime = difftime + 0.5;
											if (endtime.getMinutes() < begintime
													.getMinutes())
												difftime = difftime - 0.5;

											//alert("6 總請假時數: " + difftime
											//		+ " HR");
										}

										else if (endtime >= lunch2
												|| lunch1 >= begintime) {
											difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
													/ changetohour //頭尾2天請假時數
													+ (((endofstartmonth
															.getDate() - begintime
															.getDate())
													//起始月份的最後一天-起始日期
													+ (endtime.getDate() - startoflastmonth
															.getDate())) * 8)
													- 1;
											//結束日期-結束月份的第一天  午休修正
											if (endtime.getMinutes() > begintime
													.getMinutes())
												difftime = difftime + 0.5;
											if (endtime.getMinutes() < begintime
													.getMinutes())
												difftime = difftime - 0.5;

											//alert("7 總請假時數: " + difftime
											//		+ " HR");
										} else if (endtime <= lunch2
												|| lunch1 <= begintime) {
											difftime = ((endtime - starthouroflastday) + (endhouroffirstday - begintime))
													/ changetohour //頭尾2天請假時數
													+ (((endofstartmonth
															.getDate() - begintime
															.getDate())
													//起始月份的最後一天-起始日期
													+ (endtime.getDate() - startoflastmonth
															.getDate())) * 8);
											//結束日期-結束月份的第一天
											if (endtime.getMinutes() > begintime
													.getMinutes())
												difftime = difftime + 0.5;
											if (endtime.getMinutes() < begintime
													.getMinutes())
												difftime = difftime - 0.5;

											//alert("8 總請假時數: " + difftime
											//		+ " HR");

										}

									}
									//alert("開始時間 : "+begintime + "\n" +
									// 	"結束時間 : "+endtime + "\n");
								}
							});
		});
	</script>
</body>
</html>



