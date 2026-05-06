<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.dto.TransformationDTO"%>
<%@page import="in.co.rays.project_3.controller.TransformationListCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<head>

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Transformation List</title>

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/al.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	padding-top: 85px;
}

.text {
	text-align: center;
}
</style>

</head>

<%@include file="Header.jsp"%>

<body class="hm">

	<div>

		<form action="<%=ORSView.TRANSFORMATION_LIST_CTL%>" method="post">

			<jsp:useBean id="dto"
				class="in.co.rays.project_3.dto.TransformationDTO" scope="request"></jsp:useBean>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);

				int index = ((pageNo - 1) * pageSize) + 1;

				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List list = ServletUtility.getList(request);

				Iterator<TransformationDTO> it = list.iterator();
			%>

			<center>
				<h1 class="text-dark font-weight-bold pt-3">
					<u>Transformation List</u>
				</h1>
			</center>

			<!-- Success Message -->

			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>

				<%
					}
				%>

				<div class="col-md-4"></div>
			</div>

			<!-- Error Message -->

			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>

				<%
					}
				%>

				<div class="col-md-4"></div>
			</div>

			<!-- Search Section -->

			<div class="row">

				<div class="col-sm-2"></div>

				<div class="col-sm-2">
					<input type="text" name="transformCode"
						placeholder="Transform Code" class="form-control"
						value="<%=ServletUtility.getParameter("transformCode", request)%>">
				</div>

				<div class="col-sm-2">
					<input type="text" name="ruleName" placeholder="Rule Name"
						class="form-control"
						value="<%=ServletUtility.getParameter("ruleName", request)%>">
				</div>

				<div class="col-sm-2">
					<%
						HashMap map = new HashMap();
						map.put("ACTIVE", "ACTIVE");
						map.put("INACTIVE", "INACTIVE");
					%>

					<%=HTMLUtility.getList("status", ServletUtility.getParameter("status", request), map)%>
				</div>
				<div class="col-sm-3">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=TransformationListCtl.OP_SEARCH%>"> <input
						type="submit" class="btn btn-dark" name="operation"
						value="<%=TransformationListCtl.OP_RESET%>">
				</div>

			</div>

			<br>

			<!-- Table -->

			<div class="table-responsive">

				<table class="table table-bordered table-dark table-hover">

					<thead>
						<tr style="background-color: #8C8C8C">

							<th width="10%"><input type="checkbox" id="select_all">
								Select All</th>

							<th class="text">S.NO</th>
							<th class="text">Transform ID</th>
							<th class="text">Transform Code</th>
							<th class="text">Rule Name</th>
							<th class="text">Logic</th>
							<th class="text">Status</th>
							<th class="text">Edit</th>

						</tr>
					</thead>

					<tbody>

						<%
							while (it.hasNext()) {
								dto = it.next();
						%>

						<tr>

							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=dto.getId()%>"></td>

							<td class="text"><%=index++%></td>
							<td class="text"><%=dto.getTransformId()%></td>
							<td class="text"><%=dto.getTransformCode()%></td>
							<td class="text"><%=dto.getRuleName()%></td>
							<td class="text"><%=dto.getLogic()%></td>
							<td class="text"><%=dto.getStatus()%></td>

							<td class="text"><a
								href="TransformationCtl?id=<%=dto.getId()%>">Edit</a></td>

						</tr>

						<%
							}
						%>

					</tbody>

				</table>

			</div>

			<!-- Pagination -->

			<table width="100%">
				<tr>

					<td><input type="submit" name="operation"
						class="btn btn-warning"
						value="<%=TransformationListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>

					<td><input type="submit" name="operation"
						class="btn btn-primary" value="<%=TransformationListCtl.OP_NEW%>">
					</td>

					<td><input type="submit" name="operation"
						class="btn btn-danger"
						value="<%=TransformationListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-warning" value="<%=TransformationListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>

				</tr>
			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

		</form>

	</div>

</body>

<%@include file="FooterView.jsp"%>

</html>