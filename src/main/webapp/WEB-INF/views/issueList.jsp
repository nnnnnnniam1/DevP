<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>

<div class="container">
    <div class="issue-grid">
		<c:forEach var="status" items="${statusarr}">
		    <div class="grid">
		        <h2>${status}</h2>
		        <c:forEach items="${issueList}" var="issue">
		            <c:if test="${issue.status eq status}">
		                <div class="item">
		                    <p>${issue.title}</p>
		                    <p>${issue.date}</p>
		                    <p>${issue.name}</p>
		                </div>
		            </c:if>
		        </c:forEach>
		    </div>
		</c:forEach>
    </div>
</div>
</body>
</html>