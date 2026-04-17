<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.controller.MediaCoverageListCtl"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@page import="in.co.rays.project_3.dto.MediaCoverageDTO"%>

<!DOCTYPE html>
<html>

<head>

<title>Media Coverage List</title>

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

	<form action="<%=ORSView.MEDIA_COVERAGE_LIST_CTL%>" method="post">

		<jsp:useBean id="dto"
			class="in.co.rays.project_3.dto.MediaCoverageDTO" scope="request" />

		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List list = ServletUtility.getList(request);
			Iterator<MediaCoverageDTO> it = list.iterator();
		%>

		<center>
			<h1 class="text-dark font-weight-bold pt-3">
				<u>Media Coverage List</u>
			</h1>
		</center>

		<!-- Search -->
		<div class="row">

			<div class="col-sm-3">
				<input type="text" name="mediaName" placeholder="Media Name"
					class="form-control"
					value="<%=ServletUtility.getParameter("mediaName", request)%>">
			</div>

			<div class="col-sm-3">
				<input type="text" name="reporter" placeholder="Reporter"
					class="form-control"
					value="<%=ServletUtility.getParameter("reporter", request)%>">
			</div>

			<div class="col-sm-2">
				<input type="submit" class="btn btn-primary" name="operation"
					value="<%=MediaCoverageListCtl.OP_SEARCH%>"> <input
					type="submit" class="btn btn-dark" name="operation"
					value="<%=MediaCoverageListCtl.OP_RESET%>">
			</div>

		</div>

		<br>

		<!-- Table -->
		<div class="table-responsive">

			<table class="table table-bordered table-dark table-hover">

				<thead>
					<tr style="background-color: #8C8C8C;">
						<th width="10%"><input type="checkbox" id="select_all">
							Select All</th>
						<th class="text">S.NO</th>
						<th class="text">Media Name</th>
						<th class="text">Reporter</th>
						<th class="text">Coverage Date</th>
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
							name="ids" value="<%=dto.getMediaCoverageId()%>"></td>

						<td class="text"><%=index++%></td>

						<td class="text"><%=dto.getMediaName()%></td>

						<td class="text"><%=dto.getReporter()%></td>

						<td class="text"><%=DataUtility.getDateString(dto.getCoverageDate())%>
						</td>

						<td class="text"><a
							href="MediaCoverageCtl?id=<%=dto.getMediaCoverageId()%>">Edit</a>
						</td>

					</tr>

					<%
						}
					%>

				</tbody>

			</table>

		</div>

		<!-- Pagination + Actions -->
		<table width="100%">

			<tr>

				<td><input type="submit" name="operation"
					class="btn btn-warning"
					value="<%=MediaCoverageListCtl.OP_PREVIOUS%>"
					<%=pageNo > 1 ? "" : "disabled"%>></td>

				<td><input type="submit" name="operation"
					class="btn btn-primary" value="<%=MediaCoverageListCtl.OP_NEW%>">
				</td>

				<td><input type="submit" name="operation"
					class="btn btn-danger" value="<%=MediaCoverageListCtl.OP_DELETE%>">
				</td>

				<td align="right"><input type="submit" name="operation"
					class="btn btn-warning" value="<%=MediaCoverageListCtl.OP_NEXT%>"
					<%=(nextPageSize != 0) ? "" : "disabled"%>></td>

			</tr>

		</table>

		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">

	</form>

</body>

<%@include file="FooterView.jsp"%>

</html>