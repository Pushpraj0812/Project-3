<%@page import="in.co.rays.project_3.controller.StockCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Stock View</title>

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

	<form action="<%=ORSView.STOCK_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.StockDTO"
			scope="request" />

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-5">

					<div class="card">
						<div class="card-body">

							<%
								if (dto.getStockId() != null && dto.getStockId() > 0) {
							%>
							<h3 class="text-center text-primary">Update Stock</h3>
							<%
								} else {
							%>
							<h3 class="text-center text-primary">Add Stock</h3>
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

							<input type="hidden" name="id" value="<%=dto.getStockId()%>">

							<!-- Stock Name -->
							<div class="md-form">

								<div class="form-group">
									<label>Stock Name *</label> <input type="text"
										class="form-control" name="stockName"
										value="<%=DataUtility.getStringData(dto.getStockName())%>">
									<font color="red"> <%=ServletUtility.getErrorMessage("stockName", request)%>
									</font>
								</div>

								<!-- Price -->
								<div class="form-group">
									<label>Price *</label> <input type="number" step="0.01"
										class="form-control" name="price"
										value="<%=DataUtility.getStringData(dto.getPrice())%>">
									<font color="red"> <%=ServletUtility.getErrorMessage("price", request)%>
									</font>
								</div>

								<!-- Quantity -->
								<div class="form-group">
									<label>Quantity *</label> <input type="number"
										class="form-control" name="quantity"
										value="<%=DataUtility.getStringData(dto.getQuantity())%>">
									<font color="red"> <%=ServletUtility.getErrorMessage("quantity", request)%>
									</font>
								</div>

								<!-- Buttons -->
								<div class="text-center">

									<%
										if (dto.getStockId() != null && dto.getStockId() > 0) {
									%>
									<input type="submit" class="btn btn-success" name="operation"
										value="<%=StockCtl.OP_UPDATE%>"> <input type="submit"
										class="btn btn-warning" name="operation"
										value="<%=StockCtl.OP_CANCEL%>">
									<%
										} else {
									%>
									<input type="submit" class="btn btn-success" name="operation"
										value="<%=StockCtl.OP_SAVE%>"> <input type="submit"
										class="btn btn-warning" name="operation"
										value="<%=StockCtl.OP_RESET%>">
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