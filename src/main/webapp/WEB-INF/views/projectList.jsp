<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">=
<link rel="stylesheet"  href="/resources/css/leaderDetail.css">
<head>
    <title>Title</title>
    <%@include file="sidebar.jsp"%>
</head>
<body>
<div class="container">
    <div class="">
        <h1 class="">프로젝트 목록</h1>
        <div class="row">
<%--            <c:forEach var="status" items="${statusarr}">--%>
                <div class="col-md-4">
<%--                    <div class = "m-2 card">--%>
<%--                        <div class="card-header">--%>
<%--                                ${status}--%>
<%--                        </div>--%>
                        <c:forEach items="${projectList}" var="project">
<%--                            <c:if test="${issue.status eq status}">--%>
                                <div class="">
                                    <div class="card wd-75 text-bg-light">
                                        <a href="/project/detail.do?projectId=${project.projectId}" class="text-reset text-decoration-none">
                                            <div class="card-header">${project.projectName}</div>
                                            <div class="card-body">
                                                <small class="text-body-secondary">${project.role}</small>
                                                <small class="text-body-secondary">${project.position}</small>
                                                <div class="projectProgress">
                                                    <span class="text-body-secondary ">전체진행률</span>
                                                    <div class="progress">
                                                        <div style="width: ${project.projectProgress}%;" class="progress-bar" />
                                                    </div>
                                                </div>
                                                <div class="projectProgress">
                                                    <span class="text-body-secondary">개인진행률</span>
                                                    <div class="progress">
                                                        <div style="width: ${project.memberProgress}%;" class="progress-bar" />
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            <br>
<%--                            </c:if>--%>
                        </c:forEach>
<%--                    </div>--%>
                </div>
<%--            </c:forEach>--%>
        </div>
    </div>
</div>
<%--<div class="container">--%>
<%--    <div class="list">--%>
<%--    <c:forEach var="project" items ="${projectList}">--%>
<%--        <div class="item">--%>
<%--            <p>${project.projectName}</p>--%>
<%--            <p>${project.position}</p>--%>
<%--            <p>${project.role}</p>--%>
<%--            <p>${project.projectProgress}</p>--%>
<%--            <p>${project.memberProgress}</p>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>
