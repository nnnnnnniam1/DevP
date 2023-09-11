<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/loginStyles.css">
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>
<%
String username = (String) session.getAttribute("name");
%>
<div class="container">
    <div class="issue-wrapper">
            <form method="post" action="write.do" id="issue-form">
            	<input type = "text" name="projectId" value = "5">
            	<input type = "text" name="taskId" value = "1">
            	<label id="inputLabel">프로젝트</label><input type = "text" name="projectTitle">
            	<br>
            	<label id="inputLabel">안건</label><input type = "text" name="category"><br>
            	<label id="inputLabel">작성자</label><input type = "text" name="writer" value = "<%= username %>"><br>
            	<label id="inputLabel">제목</label><input type = "text" name="title"><br>
            	<label id="inputLabel">알림</label><input type = "text" name="sendingEmail"><br>
            	<label id="inputLabel">내용</label><input type = "text" name="content"><br>
                <input type="submit" value="등록">
            </form>
        </div>
</div>
</body>
</html>