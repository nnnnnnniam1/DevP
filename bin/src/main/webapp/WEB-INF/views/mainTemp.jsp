<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="sidebar.jsp"%>

<html>
<head>
    <title>개발자국 메인</title>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/member.css">
    <script src="script.js"></script>
</head>
<body>
<div class="mw-100 container">
    <div class="main-text">
        ${title}
        <button type="button" class="visually-hidden"
                <c:if test="${login eq '로그인'}"> onclick="location.href='/user/login/view.do'"</c:if>
                <c:if test="${login eq '로그아웃'}"> onclick="location.href='/user/logout.do'"</c:if>>
            ${login}
        </button>
    </div>
    <div class="calender">
        <c:forEach items="${taskList}" var="task">
            <div class="mx-4 my-4">
<%--                <a href="/task/detail.do?taskId=${task.taskId}" class="text-reset text-decoration-none">--%>
<%--                    <div class="">${task.projectName}</div>--%>
                    <div class="">
                        <small>${task.category}</small>
                        <smail>${task.detail}</smail>
                    </div>
<%--                </a>--%>
            </div>
        </c:forEach>
    </div>
    <div class="project_list position-relative" onclick="location.href='/project/list/view.do'">
        <div>진행 중인 프로젝트</div>
        <a href='/project/add/view.do' class="btn btn-outline-dark position-absolute top-50 start-50">프로젝트 추가</a>
    </div>
    <div class="issue">
        <c:forEach items="${issueList}" var="issue">
                    <div class="mx-4 my-4">
                        <a href="/issue/detail.do?issueId=${issue.issueId}" class="text-reset text-decoration-none">
                            <div class="">${issue.title}</div>
                            <div class="">
                                <small class="text-body-secondary">${issue.date}<br>${issue.name}</small>
                            </div>
                        </a>
                    </div>
        </c:forEach>
    </div>
    <div class="task">
        <div class="card mt-3">
            <div class="card-body">
                <h5 class="card-title">달력</h5>
                <%@include file="mainCalendar.jsp"%>
            </div>
        </div>
    </div>
</div>
</body>
</html>