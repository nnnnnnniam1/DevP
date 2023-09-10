<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023/09/10
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="insertProject.do">
        <label>
            프로젝트 아이디
            <input name="projectId">
        </label><br>
        <label>
            프로젝트
            <input name="projectName">
        </label><br>
        <c:set var="ymd" value="<%=new java.util.Date()%>" />
        <label>
            시작일
            <input name="startDate" value="<fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd" />">
        </label><br>
        <label>
            종료일
            <input name="endDate">
        </label><br>
        <label>
            진행률
            <input name="progress" value="0">
        </label><br>
        <label>
            멤버 추가
        </label><br>
        <label>
            설명
            <textarea name="script"></textarea>
        </label><br>
        <button type="submit"> 프로젝트 추가</button>
    </form>
</body>
</html>
