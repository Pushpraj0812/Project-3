<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.dto.PolicyDTO"%>
<%@page import="in.co.rays.project_3.controller.PolicyListCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Policy List</title>

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

	<form action="<%=ORSView.POLICY_LIST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PolicyDTO"
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
			Iterator<PolicyDTO> it = list.iterator();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		%>

		<div class="container mt-5">

			<!-- Heading -->
			<div class="text-center mb-3">
				<h2 class="text-dark">
					<u>Policy List</u>
				</h2>
			</div>

			<div class="text-center text-success">
				<%=ServletUtility.getSuccessMessage(request)%>
			</div>
			<div class="text-center text-danger">
				<%=ServletUtility.getErrorMessage(request)%>
			</div>

			<!-- Search -->
			<div class="row justify-content-center mt-4">

				<div class="col-md-4">
					<input type="text" name="policyName" class="form-control"
						placeholder="Policy Name"
						value="<%=ServletUtility.getParameter("policyName", request)%>">
				</div>

				<div class="col-md-3">
					<input type="number" name="premiumAmount" class="form-control"
						placeholder="Premium Amount"
						value="<%=ServletUtility.getParameter("premiumAmount", request)%>">
				</div>

				<div class="col-md-3">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=PolicyListCtl.OP_SEARCH%>"> <input type="submit"
						class="btn btn-dark" name="operation"
						value="<%=PolicyListCtl.OP_RESET%>">
				</div>

			</div>

			<div class="table-responsive mt-4">
				<table class="table table-bordered table-hover table-dark">

					<thead>
						<tr style="background-color: #6c757d;">
							<th><input type="checkbox" id="select_all"> Select
								All</th>
							<th class="text">S.No</th>
							<th class="text">Policy Name</th>
							<th class="text">Premium Amount</th>
							<th class="text">Start Date</th>
							<th class="text">Edit</th>
						</tr>
					</thead>

					<tbody>
						<%
							if (list != null && list.size() > 0) {
								while (it.hasNext()) {
									PolicyDTO pdto = it.next();
						%>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=pdto.getPolicyId()%>"></td>

							<td class="text"><%=index++%></td>
							<td class="text"><%=pdto.getPolicyName()%></td>
							<td class="text"><%=pdto.getPremiumAmount()%></td>

							
							<td class="text"><%=(pdto.getStartDate() != null) ? sdf.format(pdto.getStartDate()) : ""%>
							</td>

							<td class="text"><a
								href="PolicyCtl?id=<%=pdto.getPolicyId()%>">Edit</a></td>
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
						value="<%=PolicyListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>>
				</div>

				<div class="col-md-3 text-center">
					<input type="submit" name="operation" class="btn btn-primary"
						value="<%=PolicyListCtl.OP_NEW%>">
				</div>

				<div class="col-md-3 text-center">
					<input type="submit" name="operation" class="btn btn-danger"
						value="<%=PolicyListCtl.OP_DELETE%>">
				</div>

				<div class="col-md-3 text-right">
					<input type="submit" name="operation" class="btn btn-warning"
						value="<%=PolicyListCtl.OP_NEXT%>"
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