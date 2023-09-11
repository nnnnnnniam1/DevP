<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023/07/25
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="sidebar.jsp"%>

<html>
<head>
    <title>Title</title>
<%--    <link rel="stylesheet" href="/resources/css/styles.css">--%>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="script.js"></script>
</head>
<body>
<div class="container">
    <div class="main-text">
        ${title}
        <button type="button"
                <c:if test="${login eq '로그인'}"> onclick="location.href='login.do'"</c:if>
                <c:if test="${login eq '로그아웃'}"> onclick="location.href='logout.do'"</c:if>>
            ${login}
        </button>
    </div>
    <div class="calender"> Item1 </div>
    <div class="project_list">
        <div>진행 중인 프로젝트</div>
        <button type="button" onclick="location.href='/project/insert.do'">프로젝트 추가</button>
    </div>
    <div class="issue">Item3</div>
    <div class="task">Item4</div>
</div>
</body>
</html>
