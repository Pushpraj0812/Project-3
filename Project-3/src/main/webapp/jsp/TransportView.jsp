<%@page import="in.co.rays.project_3.controller.TransportCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@page import="in.co.rays.project_3.dto.TransportDTO"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>

<title>Transport</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	padding-top: 70px;
}

.card-shadow {
	box-shadow: 8px 8px 6px #001a33;
}
</style>

</head>

<body class="hm">

	<div class="header">
		<%@include file="Header.jsp"%>
	</div>

	<main>

	<form action="<%=ORSView.TRANSPORT_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.TransportDTO"
			scope="request" />

		<div class="row pt-3">
			<!-- Grid column -->
			<div class="col-md-4 mb-4"></div>
			<div class="col-md-4 mb-4">
				<div class="card input-group-addon">
					<div class="card-body">

						<%
							long id = DataUtility.getLong(request.getParameter("id"));

							if (dto.getDriverName() != null && dto.getId() > 0) {
						%>
						<h3 class="text-center default-text text-primary">Update User</h3>
						<%
							} else {
						%>
						<h3 class="text-center default-text text-primary">Add User</h3>
						<%
							}
						%>

						<br>

						<h5 align="center" style="color: green">
							<%=ServletUtility.getSuccessMessage(request)%>
						</h5>

						<h5 align="center" style="color: red">
							<%=ServletUtility.getErrorMessage(request)%>
						</h5>

						<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
							type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
						<input type="hidden" name="modifiedBy"
							value="<%=dto.getModifiedBy()%>"> <br>


						<!-- Vehicle Number -->

						<label><b>Vehicle Number</b></label> <input type="text"
							class="form-control" name="vehicleNumber"
							placeholder="Enter Vehicle Number"
							value="<%=DataUtility.getStringData(dto.getVehicleNumber())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("vehicleNumber", request)%>
						</font> <br>

						<!-- Driver Name -->

						<label><b>Driver Name</b></label> <input type="text"
							class="form-control" name="driverName"
							placeholder="Enter Driver Name"
							value="<%=DataUtility.getStringData(dto.getDriverName())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("driverName", request)%>
						</font> <br>

						<!-- Vehicle Type -->

						<label><b>Vehicle Type</b></label> <input type="text"
							class="form-control" name="vehicleType"
							placeholder="Enter Vehicle Type"
							value="<%=DataUtility.getStringData(dto.getVehicleType())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("vehicleType", request)%>
						</font> <br>

						<!-- Transport Status -->

						<label><b>Transport Status</b></label> <input type="text"
							class="form-control" name="transportStatus"
							placeholder="Enter Transport Status"
							value="<%=DataUtility.getStringData(dto.getTransportStatus())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("transportStatus", request)%>
						</font> <br>

						<div class="text-center">

							<%
								if (dto.getDriverName() != null && dto.getId() > 0) {
							%>

							<input type="submit" name="operation" class="btn btn-success"
								value="<%=TransportCtl.OP_UPDATE%>"> <input
								type="submit" name="operation" class="btn btn-warning"
								value="<%=TransportCtl.OP_CANCEL%>">

							<%
								} else {
							%>

							<input type="submit" name="operation" class="btn btn-success"
								value="<%=TransportCtl.OP_SAVE%>"> <input type="submit"
								name="operation" class="btn btn-warning"
								value="<%=TransportCtl.OP_RESET%>">

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

	</main>

</body>

<%@include file="FooterView.jsp"%>

</html>