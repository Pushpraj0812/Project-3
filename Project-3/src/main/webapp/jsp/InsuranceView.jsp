<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@page import="in.co.rays.project_3.controller.InsuranceCtl"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<head>

<title>Insurance View</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	padding-top: 75px;
}
</style>

</head>

<body class="hm">

	<div class="header">

		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>

	</div>

	<form action="<%=ORSView.INSURANCE_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.InsuranceDTO"
			scope="request" />

		<%
			long id = DataUtility.getLong(request.getParameter("id"));
		%>

		<div class="row pt-3">

			<div class="col-md-4"></div>

			<div class="col-md-4">

				<div class="card">

					<div class="card-body">

						<%
							if (id > 0) {
						%>

						<h3 class="text-center text-primary">Update Insurance</h3>

						<%
							} else {
						%>

						<h3 class="text-center text-primary">Add Insurance</h3>

						<%
							}
						%>


						<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
							type="hidden" name="createdBy"
							value="<%=dto.getCreatedBy() == null ? "" : dto.getCreatedBy()%>">

						<input type="hidden" name="modifiedBy"
							value="<%=dto.getModifiedBy() == null ? "" : dto.getModifiedBy()%>">

						<input type="hidden" name="createdDatetime"
							value="<%=dto.getCreatedDatetime() == null ? "" : DataUtility.getTimestamp(dto.getCreatedDatetime())%>">

						<input type="hidden" name="modifiedDatetime"
							value="<%=dto.getModifiedDatetime() == null ? "" : DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


						<!-- SUCCESS MESSAGE -->

						<h4 align="center">

							<%
								if (!ServletUtility.getSuccessMessage(request).equals("")) {
							%>

							<div class="alert alert-success">

								<%=ServletUtility.getSuccessMessage(request)%>

							</div>

							<%
								}
							%>

						</h4>


						<!-- ERROR MESSAGE -->

						<h4 align="center">

							<%
								if (!ServletUtility.getErrorMessage(request).equals("")) {
							%>

							<div class="alert alert-danger">

								<%=ServletUtility.getErrorMessage(request)%>

							</div>

							<%
								}
							%>

						</h4>


						<!-- POLICY NUMBER -->

						<b>Policy Number</b> <input type="text" class="form-control"
							name="policyNumber" placeholder="Policy Number"
							value="<%=DataUtility.getStringData(dto.getPolicyNumber())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("policyNumber", request)%>

						</font> <br>


						<!-- POLICY HOLDER -->

						<b>Policy Holder Name</b> <input type="text" class="form-control"
							name="policyHolderName" placeholder="Policy Holder Name"
							value="<%=DataUtility.getStringData(dto.getPolicyHolderName())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("policyHolderName", request)%>

						</font> <br>


						<!-- EXPIRY DATE -->

						<b>Expiry Date</b> <input type="text" id="datepicker2"
							name="expiryDate" class="form-control" readonly="readonly"
							value="<%=DataUtility.getDateString(dto.getExpiryDate())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("expiryDate", request)%>

						</font> <br>


						<!-- STATUS -->

						<b>Insurance Status</b>

						<%
							HashMap map = new HashMap();

							map.put("Active", "Active");
							map.put("Expired", "Expired");
							map.put("Cancelled", "Cancelled");

							String htmlList = HTMLUtility.getList("insuranceStatus", dto.getInsuranceStatus(), map);
						%>

						<%=htmlList%>

						<font color="red"> <%=ServletUtility.getErrorMessage("insuranceStatus", request)%>

						</font> <br>


						<div class="text-center">

							<%
								if (id > 0) {
							%>

							<input type="submit" name="operation" class="btn btn-success"
								value="<%=InsuranceCtl.OP_UPDATE%>"> <input
								type="submit" name="operation" class="btn btn-warning"
								value="<%=InsuranceCtl.OP_CANCEL%>">

							<%
								} else {
							%>

							<input type="submit" name="operation" class="btn btn-success"
								value="<%=InsuranceCtl.OP_SAVE%>"> <input type="submit"
								name="operation" class="btn btn-warning"
								value="<%=InsuranceCtl.OP_RESET%>">

							<%
								}
							%>

						</div>

					</div>

				</div>

			</div>

			<div class="col-md-4"></div>

		</div>

	</form>

	<%@include file="FooterView.jsp"%>

</body>

</html>