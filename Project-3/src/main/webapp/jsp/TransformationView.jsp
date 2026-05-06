<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.controller.TransformationCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>

<title>Transformation</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	padding-top: 75px;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
}
</style>

</head>

<body class="hm">

	<div class="header">
		<%@include file="Header.jsp"%>
	</div>

	<main>

	<form action="<%=ORSView.TRANSFORMATION_CTL%>" method="post">

		<jsp:useBean id="dto"
			class="in.co.rays.project_3.dto.TransformationDTO" scope="request" />

		<div class="row pt-3">

			<div class="col-md-4"></div>

			<div class="col-md-4">

				<div class="card input-group-addon">
					<div class="card-body">

						<%
							long id = DataUtility.getLong(request.getParameter("id"));
							if (dto.getTransformCode() != null && dto.getId() > 0) {
						%>
						<h3 class="text-center text-primary">Update Transformation</h3>
						<%
							} else {
						%>
						<h3 class="text-center text-primary">Add Transformation</h3>
						<%
							}
						%>

						<br>

						<!-- SUCCESS -->
						<%
							if (!ServletUtility.getSuccessMessage(request).equals("")) {
						%>
						<div class="alert alert-success alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<%=ServletUtility.getSuccessMessage(request)%>
						</div>
						<%
							}
						%>

						<!-- ERROR -->
						<%
							if (!ServletUtility.getErrorMessage(request).equals("")) {
						%>
						<div class="alert alert-danger alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<%=ServletUtility.getErrorMessage(request)%>
						</div>
						<%
							}
						%>

						<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
							type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
						<input type="hidden" name="modifiedBy"
							value="<%=dto.getModifiedBy()%>">

						<!-- Transform ID -->
						<span><b>Transform Id</b></span>
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fa fa-id-badge"></i>
								</div>
							</div>
							<input type="text" class="form-control" name="transformId"
								value="<%=DataUtility.getStringData(dto.getTransformId())%>">
						</div>

						<font color="red"><%=ServletUtility.getErrorMessage("transformId", request)%></font>

						<!-- Transform Code -->
						<span><b>Transform Code</b></span>
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fa fa-code"></i>
								</div>
							</div>
							<input type="text" class="form-control" name="transformCode"
								value="<%=DataUtility.getStringData(dto.getTransformCode())%>">
						</div>

						<font color="red"><%=ServletUtility.getErrorMessage("transformCode", request)%></font>

						<!-- Rule Name -->
						<span><b>Rule Name</b></span>
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fa fa-cogs"></i>
								</div>
							</div>
							<input type="text" class="form-control" name="ruleName"
								value="<%=DataUtility.getStringData(dto.getRuleName())%>">
						</div>

						<font color="red"><%=ServletUtility.getErrorMessage("ruleName", request)%></font>

						<!-- Logic -->
						<span><b>Logic</b></span>
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fa fa-brain"></i>
								</div>
							</div>
							<input type="text" class="form-control" name="logic"
								value="<%=DataUtility.getStringData(dto.getLogic())%>">
						</div>

						<font color="red"><%=ServletUtility.getErrorMessage("logic", request)%></font>

						<!-- Status -->
						<span><b>Status</b></span>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fa fa-info-circle"></i>
								</div>
							</div>

							<%
								HashMap map = new HashMap();
								map.put("ACTIVE", "ACTIVE");
								map.put("INACTIVE", "INACTIVE");

								String htmlList = HTMLUtility.getList("status", dto.getStatus(), map);
							%>

							<%=htmlList%>

						</div>

						<!-- Buttons -->
						<div class="text-center mt-3">

							<%
								if (dto.getTransformCode() != null && dto.getId() > 0) {
							%>

							<input type="submit" name="operation" class="btn btn-success"
								value="<%=TransformationCtl.OP_UPDATE%>"> <input
								type="submit" name="operation" class="btn btn-warning"
								value="<%=TransformationCtl.OP_CANCEL%>">

							<%
								} else {
							%>

							<input type="submit" name="operation" class="btn btn-success"
								value="<%=TransformationCtl.OP_SAVE%>"> <input
								type="submit" name="operation" class="btn btn-warning"
								value="<%=TransformationCtl.OP_RESET%>">

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

	</main>

	<%@include file="FooterView.jsp"%>

</body>

</html>