<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="sidebar.jsp"%>
<head>
    <link rel="stylesheet" href="/resources/css/issue.css">
</head>
<body>

<div class="mv-100 container">
	<div class="mt-5">
		<h1 class="mb-5">${project.projectName} 이슈 목록</h1>
		<div class="d-flex flex-row-reverse">
			<button type="submit" onclick="location.href='/issue/write.do?projectId=${projectId}'" class="btn btn-primary mx-3">작성</button>
		</div>
	    <div class="row">
			<c:forEach var="status" items="${statusarr}">
			    <div class="col-md-4">
			    <div class = "m-2 card">
			        <div class="card-header">
					    ${status}
					  </div>
			        <c:forEach items="${issueList}" var="issue">
			            <c:if test="${issue.status eq status}">
			                <div class="item card-body">
				                <div class="card text-bg-light">
					                <a href="/issue/detail.do?issueId=${issue.issueId}" class="text-reset text-decoration-none">
									  <div class="card-header">${issue.title}</div>
									  <div class="card-body">
									    <small class="text-body-secondary">${issue.date}<br>${issue.name}</small>
									  </div>
									</a>
								</div>
			                </div>
			            </c:if>
			        </c:forEach>
			    </div>
			    </div>
			</c:forEach>
	    </div>
    </div>
</div>
</body>
</html>