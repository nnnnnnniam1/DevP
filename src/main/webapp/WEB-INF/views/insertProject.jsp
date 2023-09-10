<%@include file="sidebar.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/insertProject.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
<div class="main-text">${title}</div>
    <div>
    <form method="post" action="insertProject.do" class="input-text">
        <div><label class="input-border">프로젝트 아이디<input name="projectId" class="">
        </label></div><br>
        <label class="input-border">
            프로젝트
            <input name="projectName" class="">
        </label><br>
        <c:set var="ymd" value="<%=new java.util.Date()%>" />
        <label class="input-border">
            시작일
            <input name="startDate" class="" value="<fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd" />">
        </label><br>
        <label class="input-border">
            종료일
            <input name="endDate" class="">
        </label><br>
        <label class="input-border">
            멤버 추가
        </label><br>
        <label class="input-border">
            설명
            <br><textarea name="script" class=""></textarea>
        </label><br>
        <button type="submit" class=""> 프로젝트 추가</button>
    </form>
    </div>
</div>
</body>
</html>
