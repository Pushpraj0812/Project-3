<%@page import="in.co.rays.project_3.controller.TravelCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>

<title>Travel View</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	padding-top: 80px;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
}
</style>

</head>

<body class="hm">

	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>


	<main>

	<form action="<%=ORSView.TRAVEL_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.TravelDTO"
			scope="request"></jsp:useBean>

		<div class="row pt-3">

			<div class="col-md-4"></div>

			<div class="col-md-4">

				<div class="card input-group-addon">

					<div class="card-body">


						<%
							long id = DataUtility.getLong(request.getParameter("id"));

							if (dto.getTraveler_Name() != null && dto.getId() > 0) {
						%>

						<h3 class="text-center text-primary">Update Travel</h3>

						<%
							} else {
						%>

						<h3 class="text-center text-primary">Add Travel</h3>

						<%
							}
						%>


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


						<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
							type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">

						<input type="hidden" name="modifiedBy"
							value="<%=dto.getModifiedBy()%>"> <input type="hidden"
							name="createdDatetime"
							value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">

						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


						<!-- Traveler Name -->

						<span class="pl-sm-5"><b>Traveler Name</b></span> <span
							style="color: red;">*</span></span> </br>
						<div class="col-sm-12">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-user grey-text" style="font-size: 1rem;"></i>
									</div>
								</div>

								<input type="text" class="form-control" name="traveler_Name"
									placeholder="Traveler Name"
									value="<%=DataUtility.getStringData(dto.getTraveler_Name())%>">

							</div>

							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("traveler_Name", request)%>

							</font> <br>


							<!-- Destination -->

							<span class="pl-sm-5"><b>Destination</b></span> <span
								style="color: red;">*</span></span> </br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-user grey-text" style="font-size: 1rem;"></i>
										</div>
									</div>

									<input type="text" class="form-control" name="Destination"
										placeholder="Destination"
										value="<%=DataUtility.getStringData(dto.getDestination())%>">

								</div>

								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("Destination", request)%>

								</font> <br>


								<!-- Start Date -->

								<span class="pl-sm-5"><b>Start Date</b></span> <span
									style="color: red">*</span><br>

								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-calendar grey-text"></i>
											</div>
										</div>

										<input type="text" id="datepicker3" name="start_Date"
											class="form-control" placeholder="Start Date"
											readonly="readonly"
											value="<%=DataUtility.getDateString(dto.getStart_Date())%>">
									</div>
								</div>


								<!-- End Date -->

								<span class="pl-sm-5"><b>End Date</b></span> </span> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>

										<input type="text" id="datepicker3" name="end_Date"
											class="form-control" placeholder="End Date"
											readonly="readonly"
											value="<%=DataUtility.getDateString(dto.getEnd_Date())%>">

									</div>

									<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("end_Date", request)%>

									</font> <br>


									<%
										if (dto.getTraveler_Name() != null && dto.getId() > 0) {
									%>

									<div class="text-center">

										<input type="submit" name="operation" class="btn btn-success"
											value="<%=TravelCtl.OP_UPDATE%>"> <input
											type="submit" name="operation" class="btn btn-warning"
											value="<%=TravelCtl.OP_CANCEL%>">

									</div>

									<%
										} else {
									%>

									<div class="text-center">

										<input type="submit" name="operation" class="btn btn-success"
											value="<%=TravelCtl.OP_SAVE%>"> <input type="submit"
											name="operation" class="btn btn-warning"
											value="<%=TravelCtl.OP_RESET%>">

									</div>

									<%
										}
									%>


								</div>
							</div>
						</div>

						<div class="col-md-4"></div>

					</div>
	</form>

	</main>

</body>

<%@include file="FooterView.jsp"%>

</html>