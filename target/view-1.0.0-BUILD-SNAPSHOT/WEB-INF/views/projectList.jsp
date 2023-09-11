<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="sidebar.jsp"%>
</head>
<body>
<div>
    <c:forEach var="project" items ="${projectList}">
        <div class="item">
            <p>${project.projectName}</p>
            <p>${project.position}</p>
            <p>${project.role}</p>
            <p>${project.projectProgress}</p>
            <p>${project.memberProgress}</p>
        </div>
    </c:forEach>
</div>
</body>
</html>
