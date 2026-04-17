<%@page import="in.co.rays.project_3.controller.LabTestCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Lab Test View</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	padding-top: 100px;
}

.card {
	box-shadow: 5px 5px 10px #888888;
}
</style>

</head>

<body class="hm">

	<%@include file="Header.jsp"%>

	<form action="<%=ORSView.LABTEST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.LabTestDTO"
			scope="request" />

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-5">

					<div class="card">
						<div class="card-body">

							<%
								if (dto.getLabTestId() != null && dto.getLabTestId() > 0) {
							%>
							<h3 class="text-center text-primary">Update Lab Test</h3>
							<%
								} else {
							%>
							<h3 class="text-center text-primary">Add Lab Test</h3>
							<%
								}
							%>

							<!-- Messages -->
							<div class="text-center text-success">
								<%=ServletUtility.getSuccessMessage(request)%>
							</div>
							<div class="text-center text-danger">
								<%=ServletUtility.getErrorMessage(request)%>
							</div>

							<input type="hidden" name="id" value="<%=dto.getLabTestId()%>">

							<!-- Test Name -->
							<div class="form-group">
								<label>Test Name *</label> <input type="text"
									class="form-control" name="testName"
									value="<%=DataUtility.getStringData(dto.getTestName())%>">
								<font color="red"> <%=ServletUtility.getErrorMessage("testName", request)%>
								</font>
							</div>

							<!-- Cost -->
							<div class="form-group">
								<label>Cost *</label> <input type="number" step="0.01"
									class="form-control" name="cost"
									value="<%=DataUtility.getStringData(dto.getCost())%>">
								<font color="red"> <%=ServletUtility.getErrorMessage("cost", request)%>
								</font>
							</div>

							<!-- Test Date -->
							<div class="form-group">
								<label>Test Date *</label> <input type="date"
									class="form-control" name="testDate"
									value="<%=DataUtility.getDateString(dto.getTestDate())%>">
								<font color="red"> <%=ServletUtility.getErrorMessage("testDate", request)%>
								</font>
							</div>

							<!-- Buttons -->
							<div class="text-center">

								<%
									if (dto.getLabTestId() != null && dto.getLabTestId() > 0) {
								%>
								<input type="submit" class="btn btn-success" name="operation"
									value="<%=LabTestCtl.OP_UPDATE%>"> <input type="submit"
									class="btn btn-warning" name="operation"
									value="<%=LabTestCtl.OP_CANCEL%>">
								<%
									} else {
								%>
								<input type="submit" class="btn btn-success" name="operation"
									value="<%=LabTestCtl.OP_SAVE%>"> <input type="submit"
									class="btn btn-warning" name="operation"
									value="<%=LabTestCtl.OP_RESET%>">
								<%
									}
								%>

							</div>

						</div>
					</div>
				</div>
			</div>
	</form>

	<%@include file="FooterView.jsp"%>

</body>
</html>