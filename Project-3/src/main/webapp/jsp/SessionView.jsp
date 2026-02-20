<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@page import="in.co.rays.project_3.controller.SessionCtl"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Session View</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
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
	</div>

	<main>
	<form action="<%=ORSView.SESSION_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.SessionDTO"
			scope="request" />

		<div class="row pt-3">
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<div class="card">
					<div class="card-body">

						<%
							if (dto.getSessionId() != null && dto.getSessionId() > 0) {
						%>
						<h3 class="text-center text-primary">Update Session</h3>
						<%
							} else {
						%>
						<h3 class="text-center text-primary">Add Session</h3>
						<%
							}
						%>

						<!-- Messages -->
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

						<!-- Hidden Fields -->
						<input type="hidden" name="sessionId"
							value="<%=DataUtility.getStringData(dto.getSessionId())%>">

						<input type="hidden" name="createdBy"
							value="<%=dto.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

						<!-- Session Token -->
						<label><b>Session Token</b><span style="color: red">*</span></label>
						<input type="text" class="form-control" name="sessionToken"
							placeholder="Session Token"
							value="<%=DataUtility.getStringData(dto.getSessionToken())%>">
						<font color="red"> <%=ServletUtility.getErrorMessage("sessionToken", request)%>
						</font><br>

						<!-- User Name -->
						<label><b>User Name</b><span style="color: red">*</span></label> <input
							type="text" class="form-control" name="userName"
							placeholder="User Name"
							value="<%=DataUtility.getStringData(dto.getUserName())%>">
						<font color="red"> <%=ServletUtility.getErrorMessage("userName", request)%>
						</font><br>

						<!-- Session Status -->
						<label><b>Session Status</b><span style="color: red">*</span></label>
						<%
							HashMap statusMap = new HashMap();
							statusMap.put("ACTIVE", "ACTIVE");
							statusMap.put("INACTIVE", "INACTIVE");
						%>
						<%=HTMLUtility.getList("sessionStatus", DataUtility.getStringData(dto.getSessionStatus()), statusMap)%>
						<font color="red"> <%=ServletUtility.getErrorMessage("sessionStatus", request)%>
						</font><br> <br>

						<!-- Buttons -->
						<div class="text-center">
							<%
								if (dto.getSessionId() != null && dto.getSessionId() > 0) {
							%>
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=SessionCtl.OP_UPDATE%>"> <input type="submit"
								name="operation" class="btn btn-warning"
								value="<%=SessionCtl.OP_CANCEL%>">
							<%
								} else {
							%>
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=SessionCtl.OP_SAVE%>"> <input type="submit"
								name="operation" class="btn btn-warning"
								value="<%=SessionCtl.OP_RESET%>">
							<%
								}
							%>
						</div>

					</div>
				</div>
			</div>

			<div class="col-md-4"></div>
		</div>

	</form>
	</main>

	<%@include file="FooterView.jsp"%>
</body>
</html>