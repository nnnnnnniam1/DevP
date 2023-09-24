<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="sidebar.jsp"%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="script.js"></script>
</head>
<body>
<div class="mw-100 container">
    <div class="main-text">
        ${title}
        <button type="button"
                <c:if test="${login eq '로그인'}"> onclick="location.href='login.do'"</c:if>
                <c:if test="${login eq '로그아웃'}"> onclick="location.href='logout.do'"</c:if>>
            ${login}
        </button>
    </div>
    <div class="calender"> Item1 </div>
    <div class="project_list position-relative" onclick="location.href='/project/list.do'">
        <div>진행 중인 프로젝트</div>
        <a href='/project/insert.do' class="btn btn-outline-dark position-absolute top-50 start-50">프로젝트 추가</a>
    </div>
    <div class="issue">Item3</div>
    <div class="task">Item4</div>
</div>
</body>
</html>