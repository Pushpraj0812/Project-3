<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.dto.LabTestDTO"%>
<%@page import="in.co.rays.project_3.controller.LabTestListCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Lab Test List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/al.jpg');
	background-size: cover;
	padding-top: 85px;
}

.text {
	text-align: center;
}
</style>

</head>

<body class="hm">

	<%@include file="Header.jsp"%>

	<form action="<%=ORSView.LABTEST_LIST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.LabTestDTO"
			scope="request" />

		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			int nextPageSize = 0;
			if (request.getAttribute("nextListSize") != null) {
				nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
			}

			List list = ServletUtility.getList(request);
			Iterator<LabTestDTO> it = list.iterator();
		%>

		<div class="container mt-5">

			<!-- Heading -->
			<div class="text-center mb-3">
				<h2 class="text-dark">
					<u>Lab Test List</u>
				</h2>
			</div>

			<!-- Messages -->
			<div class="text-center text-success">
				<%=ServletUtility.getSuccessMessage(request)%>
			</div>
			<div class="text-center text-danger">
				<%=ServletUtility.getErrorMessage(request)%>
			</div>

			<!-- Search -->
			<div class="row justify-content-center mt-4">

				<div class="col-md-3">
					<input type="text" name="testName" class="form-control"
						placeholder="Test Name"
						value="<%=ServletUtility.getParameter("testName", request)%>">
				</div>

				<div class="col-md-2">
					<input type="number" name="cost" class="form-control"
						placeholder="Cost"
						value="<%=ServletUtility.getParameter("cost", request)%>">
				</div>

				<div class="col-md-3">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=LabTestListCtl.OP_SEARCH%>"> <input
						type="submit" class="btn btn-dark" name="operation"
						value="<%=LabTestListCtl.OP_RESET%>">
				</div>

			</div>

			<!-- Table -->
			<div class="table-responsive mt-4">
				<table class="table table-bordered table-hover table-dark">

					<thead>
						<tr style="background-color: #6c757d;">
							<th><input type="checkbox" id="select_all"> Select
								All</th>
							<th class="text">S.No</th>
							<th class="text">Test Name</th>
							<th class="text">Cost</th>
							<th class="text">Test Date</th>
							<th class="text">Edit</th>
						</tr>
					</thead>

					<tbody>
						<%
							if (list != null && list.size() > 0) {
								while (it.hasNext()) {
									LabTestDTO dto1 = it.next();
						%>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=dto1.getLabTestId()%>"></td>

							<td class="text"><%=index++%></td>
							<td class="text"><%=dto1.getTestName()%></td>
							<td class="text"><%=dto1.getCost()%></td>
							<td class="text"><%=dto1.getTestDate()%></td>

							<td class="text"><a
								href="LabTestCtl?id=<%=dto1.getLabTestId()%>">Edit</a></td>
						</tr>
						<%
							}
							}
						%>
					</tbody>

				</table>
			</div>

			<!-- Buttons -->
			<div class="row mt-3">

				<div class="col-md-3">
					<input type="submit" name="operation" class="btn btn-warning"
						value="<%=LabTestListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>>
				</div>

				<div class="col-md-3 text-center">
					<input type="submit" name="operation" class="btn btn-primary"
						value="<%=LabTestListCtl.OP_NEW%>">
				</div>

				<div class="col-md-3 text-center">
					<input type="submit" name="operation" class="btn btn-danger"
						value="<%=LabTestListCtl.OP_DELETE%>">
				</div>

				<div class="col-md-3 text-right">
					<input type="submit" name="operation" class="btn btn-warning"
						value="<%=LabTestListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>>
				</div>

			</div>

		</div>

		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">

	</form>

	<%@include file="FooterView.jsp"%>

</body>
</html>