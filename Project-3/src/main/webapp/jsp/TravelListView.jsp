<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.dto.TravelDTO"%>
<%@page import="in.co.rays.project_3.controller.TravelListCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>

<meta charset="ISO-8859-1">
<title>Travel List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

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

	<form action="<%=ORSView.TRAVEL_LIST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.TravelDTO"
			scope="request"></jsp:useBean>

		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			int nextPageSize = 0;

			if (request.getAttribute("nextListSize") != null) {
				nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
			}

			List list = ServletUtility.getList(request);

			if (list == null) {
				list = new java.util.ArrayList();
			}

			Iterator<TravelDTO> it = list.iterator();
		%>

		<center>
			<h1 class="text-dark font-weight-bold pt-3">
				<u>Travel List</u>
			</h1>
		</center>

		<br>

		<div class="row">

			<div class="col-sm-3"></div>

			<div class="col-sm-2">
				<input type="text" name="traveler_Name" placeholder="Traveler Name"
					class="form-control"
					value="<%=ServletUtility.getParameter("traveler_Name", request)%>">
			</div>

			<div class="col-sm-2">
				<input type="text" name="Destination" placeholder="Destination"
					class="form-control"
					value="<%=ServletUtility.getParameter("Destination", request)%>">
			</div>

			<div class="col-sm-2">
				<input type="submit" class="btn btn-primary" name="operation"
					value="<%=TravelListCtl.OP_SEARCH%>"> <input type="submit"
					class="btn btn-dark" name="operation"
					value="<%=TravelListCtl.OP_RESET%>">

			</div>

		</div>

		<br>

		<div class="table-responsive">

			<table class="table table-bordered table-dark table-hover">

				<thead>

					<tr style="background-color: #8C8C8C">

						<th width="10%"><input type="checkbox" id="select_all">
							Select All</th>

						<th class="text">S.NO</th>

						<th class="text">Traveler Name</th>

						<th class="text">Destination</th>

						<th class="text">Start Date</th>

						<th class="text">End Date</th>

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

						<td class="text"><%=dto.getTraveler_Name()%></td>

						<td class="text"><%=dto.getDestination()%></td>

						<td class="text"><%=DataUtility.getDateString(dto.getStart_Date())%></td>

						<td class="text"><%=DataUtility.getDateString(dto.getEnd_Date())%></td>

						<td class="text"><a
							href="<%=ORSView.TRAVEL_CTL%>?id=<%=dto.getId()%>">Edit</a></td>

					</tr>

					<%
						}
					%>

				</tbody>

			</table>

		</div>

		<table width="100%">

			<tr>

				<td><input type="submit" name="operation"
					class="btn btn-warning" value="<%=TravelListCtl.OP_PREVIOUS%>"
					<%=pageNo > 1 ? "" : "disabled"%>></td>

				<td><input type="submit" name="operation"
					class="btn btn-primary" value="<%=TravelListCtl.OP_NEW%>">

				</td>

				<td><input type="submit" name="operation"
					class="btn btn-danger" value="<%=TravelListCtl.OP_DELETE%>">

				</td>

				<td align="right"><input type="submit" name="operation"
					class="btn btn-warning" value="<%=TravelListCtl.OP_NEXT%>"
					<%=(nextPageSize != 0) ? "" : "disabled"%>></td>

			</tr>

		</table>

		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">

	</form>

</body>

<%@include file="FooterView.jsp"%>

</html>
