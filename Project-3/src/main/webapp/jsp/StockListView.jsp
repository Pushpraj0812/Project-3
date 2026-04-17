<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.dto.StockDTO"%>
<%@page import="in.co.rays.project_3.controller.StockListCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Stock List</title>

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

	<form action="<%=ORSView.STOCK_LIST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.StockDTO"
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
			Iterator<StockDTO> it = list.iterator();
		%>

		<div class="container mt-5">

			<!-- Heading -->
			<div class="text-center mb-3">
				<h2 class="text-dark">
					<u>Stock List</u>
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
					<input type="text" name="stockName" class="form-control"
						placeholder="Stock Name"
						value="<%=ServletUtility.getParameter("stockName", request)%>">
				</div>

				<div class="col-md-2">
					<input type="number" name="price" class="form-control"
						placeholder="Price"
						value="<%=ServletUtility.getParameter("price", request)%>">
				</div>

				<div class="col-md-2">
					<input type="number" name="quantity" class="form-control"
						placeholder="Quantity"
						value="<%=ServletUtility.getParameter("quantity", request)%>">
				</div>

				<div class="col-md-3">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=StockListCtl.OP_SEARCH%>"> <input type="submit"
						class="btn btn-dark" name="operation"
						value="<%=StockListCtl.OP_RESET%>">
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
							<th class="text">Stock Name</th>
							<th class="text">Price</th>
							<th class="text">Quantity</th>
							<th class="text">Edit</th>
						</tr>
					</thead>

					<tbody>
						<%
							if (list != null && list.size() > 0) {
								while (it.hasNext()) {
									StockDTO sdto = it.next();
						%>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=sdto.getStockId()%>"></td>

							<td class="text"><%=index++%></td>
							<td class="text"><%=sdto.getStockName()%></td>
							<td class="text"><%=sdto.getPrice()%></td>
							<td class="text"><%=sdto.getQuantity()%></td>

							<td class="text"><a
								href="StockCtl?id=<%=sdto.getStockId()%>">Edit</a></td>
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
						value="<%=StockListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>>
				</div>

				<div class="col-md-3 text-center">
					<input type="submit" name="operation" class="btn btn-primary"
						value="<%=StockListCtl.OP_NEW%>">
				</div>

				<div class="col-md-3 text-center">
					<input type="submit" name="operation" class="btn btn-danger"
						value="<%=StockListCtl.OP_DELETE%>">
				</div>

				<div class="col-md-3 text-right">
					<input type="submit" name="operation" class="btn btn-warning"
						value="<%=StockListCtl.OP_NEXT%>"
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