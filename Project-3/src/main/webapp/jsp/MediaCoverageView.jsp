<%@page import="in.co.rays.project_3.controller.MediaCoverageCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<title>Media Coverage</title>

<style type="text/css">
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

	<form action="<%=ORSView.MEDIA_COVERAGE_CTL%>" method="post">

		<jsp:useBean id="dto"
			class="in.co.rays.project_3.dto.MediaCoverageDTO" scope="request" />

		<div class="row pt-3">

			<div class="col-md-4 mb-4"></div>

			<div class="col-md-4 mb-4">

				<div class="card input-group-addon">

					<div class="card-body">

						<%
							long id = DataUtility.getLong(request.getParameter("id"));

							if (dto.getMediaName() != null && id > 0) {
						%>

						<h3 class="text-center text-primary">Update Media Coverage</h3>

						<%
							} else {
						%>

						<h3 class="text-center text-primary">Add Media Coverage</h3>

						<%
							}
						%>

						<!-- Success -->
						<H4 align="center">
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
						</H4>

						<!-- Error -->
						<H4 align="center">
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
						</H4>

						<!-- Hidden fields -->
						<input type="hidden" name="id"
							value="<%=dto.getMediaCoverageId()%>"> <input
							type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
						<input type="hidden" name="modifiedBy"
							value="<%=dto.getModifiedBy()%>"> <input type="hidden"
							name="createdDatetime"
							value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

						<!-- Media Name -->
						<span class="pl-sm-5"><b>Media Name</b><span
							style="color: red;">*</span></span>
						<div class="col-sm-12">
							<div class="input-group">
								<input type="text" class="form-control" name="mediaName"
									placeholder="Media Name"
									value="<%=DataUtility.getStringData(dto.getMediaName())%>">
							</div>
						</div>
						<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("mediaName", request)%>
						</font>

						<!-- Reporter -->
						<span class="pl-sm-5"><b>Reporter</b><span
							style="color: red;">*</span></span>
						<div class="col-sm-12">
							<div class="input-group">
								<input type="text" class="form-control" name="reporter"
									placeholder="Reporter"
									value="<%=DataUtility.getStringData(dto.getReporter())%>">
							</div>
						</div>
						<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("reporter", request)%>
						</font>

						<!-- Coverage Date -->
						<span class="pl-sm-5"><b>Coverage Date</b><span
							style="color: red;">*</span></span>
						<div class="col-sm-12">
							<div class="input-group">
								<input type="text" id="udate5" name="coverageDate"
									class="form-control" placeholder="Coverage Date"
									readonly="readonly"
									value="<%=DataUtility.getDateString(dto.getCoverageDate())%>">
							</div>
						</div>
						<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("coverageDate", request)%>
						</font> <br>

						<!-- Buttons -->
						<%
							if (dto.getMediaName() != null && id > 0) {
						%>
						<div class="text-center">
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=MediaCoverageCtl.OP_UPDATE%>"> <input
								type="submit" name="operation" class="btn btn-warning"
								value="<%=MediaCoverageCtl.OP_CANCEL%>">
						</div>
						<%
							} else {
						%>
						<div class="text-center">
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=MediaCoverageCtl.OP_SAVE%>"> <input
								type="submit" name="operation" class="btn btn-warning"
								value="<%=MediaCoverageCtl.OP_RESET%>">
						</div>
						<%
							}
						%>

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