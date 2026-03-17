<%@page import="in.co.rays.project_3.controller.PodcastCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<title>Podcast View</title>

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

	<form action="<%=ORSView.PODCAST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PodcastDTO"
			scope="request" />

		<div class="row pt-3">

			<div class="col-md-4 mb-4"></div>

			<div class="col-md-4 mb-4">

				<div class="card input-group-addon">

					<div class="card-body">

						<%
							long id = DataUtility.getLong(request.getParameter("id"));

							if (dto.getEpisode_title() != null && dto.getId() > 0) {
						%>

						<h3 class="text-center text-primary">Update Podcast</h3>

						<%
							} else {
						%>

						<h3 class="text-center text-primary">Add Podcast</h3>

						<%
							}
						%>


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


						<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
							type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">

						<input type="hidden" name="modifiedBy"
							value="<%=dto.getModifiedBy()%>"> <input type="hidden"
							name="createdDatetime"
							value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">

						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


						<!-- Episode Title -->

						<span class="pl-sm-5"><b>Episode Title</b><span
							style="color: red;">*</span></span>

						<div class="col-sm-12">

							<div class="input-group">

								<input type="text" class="form-control" name="episode_title"
									placeholder="Episode Title"
									value="<%=DataUtility.getStringData(dto.getEpisode_title())%>">

							</div>

						</div>

						<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("episode_title", request)%>
						</font>


						<!-- Host Name -->

						<span class="pl-sm-5"><b>Host Name</b><span
							style="color: red;">*</span></span>

						<div class="col-sm-12">

							<div class="input-group">

								<input type="text" class="form-control" name="host_name"
									placeholder="Host Name"
									value="<%=DataUtility.getStringData(dto.getHost_name())%>">

							</div>

						</div>

						<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("host_name", request)%>
						</font>


						<!-- Duration -->

						<span class="pl-sm-5"><b>Duration</b><span
							style="color: red;">*</span></span>

						<div class="col-sm-12">

							<div class="input-group">

								<input type="text" class="form-control" name="duration"
									placeholder="Duration"
									value="<%=DataUtility.getStringData(dto.getDuration())%>">

							</div>

						</div>

						<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("duration", request)%>
						</font>


						<!-- Release Date -->

						<span class="pl-sm-5"><b>Release Date</b><span
							style="color: red;">*</span></span>

						<div class="col-sm-12">

							<div class="input-group">

								<input type="text" id="datepicker3" name="release_date"
									class="form-control" placeholder="Release Date"
									readonly="readonly"
									value="<%=DataUtility.getDateString(dto.getRelease_date())%>">

							</div>

						</div>

						<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("release_date", request)%>
						</font> <br>


						<%
							if (dto.getEpisode_title() != null && dto.getId() > 0) {
						%>

						<div class="text-center">

							<input type="submit" name="operation"
								class="btn btn-success btn-md" value="<%=PodcastCtl.OP_UPDATE%>">

							<input type="submit" name="operation"
								class="btn btn-warning btn-md" value="<%=PodcastCtl.OP_CANCEL%>">

						</div>

						<%
							} else {
						%>

						<div class="text-center">

							<input type="submit" name="operation"
								class="btn btn-success btn-md" value="<%=PodcastCtl.OP_SAVE%>">

							<input type="submit" name="operation"
								class="btn btn-warning btn-md" value="<%=PodcastCtl.OP_RESET%>">

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