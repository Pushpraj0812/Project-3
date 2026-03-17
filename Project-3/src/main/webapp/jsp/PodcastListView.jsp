<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.controller.PodcastListCtl"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@page import="in.co.rays.project_3.dto.PodcastDTO"%>

<!DOCTYPE html>
<html>

<head>

<title>Podcast List</title>

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

	<form action="<%=ORSView.PODCAST_LIST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PodcastDTO"
			scope="request" />

		<%
			int pageNo = ServletUtility.getPageNo(request);

			int pageSize = ServletUtility.getPageSize(request);

			int index = ((pageNo - 1) * pageSize) + 1;

			int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List list = ServletUtility.getList(request);

			Iterator<PodcastDTO> it = list.iterator();
		%>


		<center>

			<h1 class="text-dark font-weight-bold pt-3">

				<u>Podcast List</u>

			</h1>

		</center>


		<div class="row">

			<div class="col-sm-3">

				<input type="text" name="episode_title" placeholder="Episode Title"
					class="form-control"
					value="<%=ServletUtility.getParameter("episode_title", request)%>">

			</div>

			<div class="col-sm-3">

				<input type="text" name="host_name" placeholder="Host Name"
					class="form-control"
					value="<%=ServletUtility.getParameter("host_name", request)%>">

			</div>

			<div class="col-sm-2">

				<input type="text" name="duration" placeholder="Duration"
					class="form-control"
					value="<%=ServletUtility.getParameter("duration", request)%>">

			</div>

			<div class="col-sm-2">

				<input type="submit" class="btn btn-primary" name="operation"
					value="<%=PodcastListCtl.OP_SEARCH%>"> <input type="submit"
					class="btn btn-dark" name="operation"
					value="<%=PodcastListCtl.OP_RESET%>">

			</div>

		</div>


		<br>


		<div class="table-responsive">

			<table class="table table-bordered table-dark table-hover">

				<thead>

					<tr style="background-color: #8C8C8C;">

						<th width="10%"><input type="checkbox" id="select_all">

							Select All</th>

						<th class="text">S.NO</th>

						<th class="text">Episode Title</th>

						<th class="text">Host Name</th>

						<th class="text">Duration</th>

						<th class="text">Release Date</th>

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

						<td class="text"><%=dto.getEpisode_title()%></td>

						<td class="text"><%=dto.getHost_name()%></td>

						<td class="text"><%=dto.getDuration()%></td>

						<td class="text"><%=DataUtility.getDateString(dto.getRelease_date())%></td>

						<td class="text"><a href="PodcastCtl?id=<%=dto.getId()%>">Edit</a>

						</td>

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
					class="btn btn-warning" value="<%=PodcastListCtl.OP_PREVIOUS%>"
					<%=pageNo > 1 ? "" : "disabled"%>></td>

				<td><input type="submit" name="operation"
					class="btn btn-primary" value="<%=PodcastListCtl.OP_NEW%>">

				</td>

				<td><input type="submit" name="operation"
					class="btn btn-danger" value="<%=PodcastListCtl.OP_DELETE%>">

				</td>

				<td align="right"><input type="submit" name="operation"
					class="btn btn-warning" value="<%=PodcastListCtl.OP_NEXT%>"
					<%=(nextPageSize != 0) ? "" : "disabled"%>></td>

			</tr>

		</table>


		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">


	</form>

</body>

<%@include file="FooterView.jsp"%>

</html>