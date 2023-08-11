<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023/07/25
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <div class="main-text">${title}</div>
    <div class="calender">Item1</div>
    <div class="project_list">
        <div>진행 중인 프로젝트</div>
    </div>
    <div class="issue">Item3</div>
    <div class="task">Item4</div>
</div>
</body>
</html>
