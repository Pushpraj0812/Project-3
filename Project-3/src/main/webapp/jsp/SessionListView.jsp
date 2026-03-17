<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.controller.SessionListCtl"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Session List</title>

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
	<div>

		<form class="pb-5" action="<%=ORSView.SESSION_LIST_CTL%>"
			method="post">

			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.SessionDTO"
				scope="request" />

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List list = ServletUtility.getList(request);
				Iterator<in.co.rays.project_3.dto.SessionDTO> it = list.iterator();
			%>

			<%
				if (list.size() != 0) {
			%>

			<center>
				<h1 class="text-dark font-weight-bold pt-3">
					<u>Session List</u>
				</h1>
			</center>

			<%
				if (!ServletUtility.getSuccessMessage(request).equals("")) {
			%>
			<div class="alert alert-success text-center">
				<%=ServletUtility.getSuccessMessage(request)%>
			</div>
			<%
				}
			%>

			<%
				if (!ServletUtility.getErrorMessage(request).equals("")) {
			%>
			<div class="alert alert-danger text-center">
				<%=ServletUtility.getErrorMessage(request)%>
			</div>
			<%
				}
			%>

			<!-- Search Row -->
			<div class="row mb-3">
				<div class="col-sm-3"></div>

				<div class="col-sm-2">
					<input type="text" name="userName" placeholder="User Name"
						class="form-control"
						value="<%=ServletUtility.getParameter("userName", request)%>">
				</div>

				<div class="col-sm-2">
					<input type="text" name="sessionToken" placeholder="Session Token"
						class="form-control"
						value="<%=ServletUtility.getParameter("sessionToken", request)%>">
				</div>

				<div class="col-sm-3">
					<%
						HashMap map = new HashMap();
							map.put("ACTIVE", "ACTIVE");
							map.put("INACTIVE", "INACTIVE");
					%>
					<%=HTMLUtility.getList("sessionStatus", ServletUtility.getParameter("sessionStatus", request),
						map)%>
				</div>

				<div class="col-sm-2">
					<input type="submit" class="btn btn-primary btn-md"
						name="operation" value="<%=SessionListCtl.OP_SEARCH%>"> <input
						type="submit" class="btn btn-dark btn-md" name="operation"
						value="<%=SessionListCtl.OP_RESET%>">
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-bordered table-dark table-hover">
					<thead>
						<tr style="background-color: #8C8C8C;">
							<th width="8%"><input type="checkbox" id="select_all">
								Select</th>
							<th width="5%" class="text">S.NO</th>
							<th width="20%" class="text">Session Token</th>
							<th width="15%" class="text">User Name</th>
							<th width="10%" class="text">Status</th>
							<th width="15%" class="text">Login Time</th>
							<th width="15%" class="text">Logout Time</th>
							<th width="7%" class="text">Edit</th>
						</tr>
					</thead>

					<tbody>
						<%
							while (it.hasNext()) {
									in.co.rays.project_3.dto.SessionDTO sdto = it.next();
						%>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=sdto.getSessionId()%>"></td>
							<td class="text"><%=index++%></td>
							<td class="text"><%=sdto.getSessionToken()%></td>
							<td class="text"><%=sdto.getUserName()%></td>
							<td class="text"><%=sdto.getSessionStatus()%></td>
							<td class="text"><%=sdto.getLoginTime()%></td>
							<td class="text"><%=sdto.getLogoutTime()%></td>
							<td class="text"><a
								href="SessionCtl?id=<%=sdto.getSessionId()%>">Edit</a></td>
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
						class="btn btn-warning" value="<%=SessionListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>

					<td><input type="submit" name="operation"
						class="btn btn-primary" value="<%=SessionListCtl.OP_NEW%>">
					</td>

					<td><input type="submit" name="operation"
						class="btn btn-danger" value="<%=SessionListCtl.OP_DELETE%>">
					</td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-warning" value="<%=SessionListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
				} else {
			%>

			<center>
				<h2>No Sessions Found</h2>
			</center>

			<input type="submit" name="operation" class="btn btn-primary"
				value="<%=SessionListCtl.OP_BACK%>">

			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

		</form>
	</div>
</body>

<%@include file="FooterView.jsp"%>
</html>