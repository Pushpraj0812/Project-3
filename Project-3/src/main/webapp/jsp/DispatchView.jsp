<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.controller.DispatchCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<head>

<title>Dispatch View</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
}

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

	<main>

	<form action="<%=ORSView.DISPATCH_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.DispatchDTO"
			scope="request"></jsp:useBean>

		<div class="row pt-3">

			<div class="col-md-4 mb-4"></div>

			<div class="col-md-4 mb-4">

				<div class="card input-group-addon">

					<div class="card-body">


						<%
							long id = DataUtility.getLong(request.getParameter("id"));

							if (dto.getId() != null && dto.getId() > 0) {
						%>

						<h3 class="text-center text-primary">Update Dispatch</h3>

						<%
							} else {
						%>

						<h3 class="text-center text-primary">Add Dispatch</h3>

						<%
							}
						%>


						<!-- SUCCESS MESSAGE -->

						<h4 align="center">

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

						</h4>


						<!-- ERROR MESSAGE -->

						<h4 align="center">

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

						</h4>


						<input type="hidden" name="id"
							value="<%=DataUtility.getStringData(dto.getId())%>"> <input
							type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">

						<input type="hidden" name="modifiedBy"
							value="<%=dto.getModifiedBy()%>"> <input type="hidden"
							name="createdDatetime"
							value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">

						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


						<div class="md-form">

							<!-- Dispatch ID -->

							<span class="pl-sm-5"><b>Dispatch Id</b></span>

							<div class="col-sm-12">

								<div class="input-group">

									<div class="input-group-prepend">

										<div class="input-group-text">

											<i class="fa fa-truck grey-text"></i>

										</div>

									</div>

									<input type="text" class="form-control" name="dispatchId"
										placeholder="Dispatch Id"
										value="<%=DataUtility.getStringData(dto.getDispatchId())%>">

								</div>

							</div>


							<!-- Dispatch Date -->

							<span class="pl-sm-5"><b>Dispatch Date</b></span>

							<div class="col-sm-12">

								<div class="input-group">

									<div class="input-group-prepend">

										<div class="input-group-text">

											<i class="fa fa-calendar"></i>

										</div>

									</div>
									<input type="text" id="datepicker3" name="dispatchDate"
										class="form-control" placeholder="Dispatch Date"
										readonly="readonly"
										value="<%=DataUtility.getDateString(dto.getDispatchDate())%>">

								</div>

							</div>


							<!-- Status -->

							<span class="pl-sm-5"><b>Status</b></span>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-info-circle"></i>
										</div>
									</div>

<%
											HashMap map = new HashMap();
											map.put("active", "active");
											map.put("inactive", "inactive");

											String htmlList = HTMLUtility.getList("status", dto.getStatus(), map);
										%>
										<%=htmlList%></div>
							</div>

							<span class="pl-sm-5"><b>Courier Name</b></span>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-user"></i>
										</div>

									</div>

									<input type="text" class="form-control" name="courierName"
										placeholder="Courier Name"
										value="<%=DataUtility.getStringData(dto.getCourierName())%>">

								</div>

							</div>


							<br>


							<div class="text-center">

								<%
									if (dto.getId() != null && dto.getId() > 0) {
								%>

								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%=DispatchCtl.OP_UPDATE%>"> <input
									type="submit" name="operation" class="btn btn-warning btn-md"
									style="font-size: 17px" value="<%=DispatchCtl.OP_CANCEL%>">

								<%
									} else {
								%>

								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%=DispatchCtl.OP_SAVE%>"> <input type="submit"
									name="operation" class="btn btn-warning btn-md"
									style="font-size: 17px" value="<%=DispatchCtl.OP_RESET%>">

								<%
									}
								%>

							</div>

						</div>

					</div>

				</div>

			</div>

			<div class="col-md-4 mb-4"></div>

		</div>

	</form>

	</main>

	<%@include file="FooterView.jsp"%>

</body>

</html>