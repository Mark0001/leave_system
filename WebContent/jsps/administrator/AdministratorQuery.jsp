<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>假單查詢</title>
<jsp:include page="/layout/meta.jsp" />
<jsp:include page="/layout/AdministratorHeader.jsp" />
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="inputPassword" class="col-sm-1 control-label">
					姓名 </label>
				<div class="col-sm-2">
					<input class="form-control" id="disabledInput" type="text">
				</div>

				<label class="col-sm-1 control-label">年度 </label>
				<div class="col-sm-2">
					<select class="form-control">
						<option>2016</option>
					</select>
				</div>

				<label class="col-sm-1 control-label">假別 </label>
				<div class="col-sm-2">
					<select class="form-control">
						<option>ALL</option>
					</select>
				</div>

				<div class="col-sm-2">

					<button type="button" class="btn btn-primary">
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
						<th><input type="checkbox"></th>
						<th>姓名</th>
						<th>申請時間</th>
						<th>假別</th>
						<th>開始時間</th>
						<th>結束時間</th>
						<th><button type="button" class="btn btn-primary">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-plus"></span>匯出
							</button></th>
					</tr>
				</thead>
				<tr>
					<th><input type="checkbox"></th>
					<th>施智升</th>
					<th>2016/5/1 19:22</th>
					<th>事假</th>
					<th>2016/5/5 08:00</th>
					<th>2016/5/5 17:00</th>
					<td><button type="button" class="btn btn-info btnShowDept"
							data-dept='${deptJSON}' data-toggle="modal"
							data-target="#deptModal">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-th-list"></span>檢視
						</button></td>
				</tr>

				<tr>
					<th><input type="checkbox"></th>
					<th>施智升</th>
					<th>2016/5/1 12:22</th>
					<th>病假</th>
					<th>2016/5/5 08:00</th>
					<th>2016/5/5 17:00</th>
					<td><button type="button" class="btn btn-info btnShowDept"
							data-dept='${deptJSON}' data-toggle="modal"
							data-target="#myModal">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-th-list"></span>檢視
						</button>

						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">close</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">假單詳細資料</h4>
									</div>
									<div class="modal-body">....</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">關閉</button>

									</div>
								</div>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>