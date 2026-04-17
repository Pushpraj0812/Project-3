<%@page import="in.co.rays.project_3.controller.PolicyCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Policy View</title>

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

	<form action="<%=ORSView.POLICY_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PolicyDTO"
			scope="request" />

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-5">

					<div class="card">
						<div class="card-body">

							<%
								if (dto.getPolicyId() != null && dto.getPolicyId() > 0) {
							%>
							<h3 class="text-center text-primary">Update Policy</h3>
							<%
								} else {
							%>
							<h3 class="text-center text-primary">Add Policy</h3>
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

							<input type="hidden" name="id" value="<%=dto.getPolicyId()%>">

							<!-- Policy Name -->
							<div class="form-group">
								<label>Policy Name *</label> <input type="text"
									class="form-control" name="policyName"
									value="<%=DataUtility.getStringData(dto.getPolicyName())%>">
								<font color="red"> <%=ServletUtility.getErrorMessage("policyName", request)%>
								</font>
							</div>

							<!-- Premium Amount -->
							<div class="form-group">
								<label>Premium Amount *</label> <input type="number"
									class="form-control" name="premiumAmount"
									value="<%=DataUtility.getStringData(dto.getPremiumAmount())%>">
								<font color="red"> <%=ServletUtility.getErrorMessage("premiumAmount", request)%>
								</font>
							</div>

							<!-- Start Date -->
							<div class="form-group">
								<label>Start Date *</label> <input type="date"
									class="form-control" name="startDate"
									value="<%=DataUtility.getDateString(dto.getStartDate())%>">
								<font color="red"> <%=ServletUtility.getErrorMessage("startDate", request)%>
								</font>
							</div>

							<!-- Buttons -->
							<div class="text-center">

								<%
									if (dto.getPolicyId() != null && dto.getPolicyId() > 0) {
								%>
								<input type="submit" class="btn btn-success" name="operation"
									value="<%=PolicyCtl.OP_UPDATE%>"> <input type="submit"
									class="btn btn-warning" name="operation"
									value="<%=PolicyCtl.OP_CANCEL%>">
								<%
									} else {
								%>
								<input type="submit" class="btn btn-success" name="operation"
									value="<%=PolicyCtl.OP_SAVE%>"> <input type="submit"
									class="btn btn-warning" name="operation"
									value="<%=PolicyCtl.OP_RESET%>">
								<%
									}
								%>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

	</form>

	<%@include file="FooterView.jsp"%>

</body>
</html>
f
