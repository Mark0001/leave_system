<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工設定</title>
<jsp:include page="/layout/meta.jsp" />
<jsp:include page="/layout/ManagerHeader.jsp" />
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<div class="container">

		<div class="starter-template">
			<table class="table table-hover"
				style="border-bottom: 2px solid #ddd">
				<thead class="thead-inverse">
					<tr>
						<th>員工編號</th>
						<th>姓名</th>
						<th>帳號</th>
						<th>密碼</th>
						<th>信箱</th>
						<th>權限</th>
						<th><button type="button" class="btn btn-primary">
								<span style="padding-right: 5px;"
									class="glyphicon glyphicon-plus"></span>新增
							</button></th>

					</tr>
				</thead>
				<tr>

					<th>000112</th>
					<th>葉子其</th>
					<th>abc123</th>
					<th>123</th>
					<th>green7916328@gmail.com</th>
					<th>員工</th>

					<td><button type="button" class="btn btn-danger "
							data-dept='${deptJSON}' data-toggle="modal"
							data-target="#myModal">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-th-list"></span>修改
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
				
				
				<tr>

					<th>000132</th>
					<th>施智升</th>
					<th>abc456</th>
					<th>456</th>
					<th>ggininger@gmail.com</th>
					<th>主管</th>

					<td><button type="button" class="btn btn-danger "
							data-dept='${deptJSON}' data-toggle="modal"
							data-target="#myModal">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-th-list"></span>修改
						</button>
				</tr>
				
					<tr>

					<th>000172</th>
					<th>潘仁傑</th>
					<th>cba123</th>
					<th>cba</th>
					<th>hi5987@gmail.com</th>
					<th>管理員</th>

					<td><button type="button" class="btn btn-danger "
							data-dept='${deptJSON}' data-toggle="modal"
							data-target="#myModal">
							<span style="padding-right: 5px;"
								class="glyphicon glyphicon-th-list"></span>修改
						</button>
				</tr>

			</table>
		</div>
	</div>
</body>
</html>