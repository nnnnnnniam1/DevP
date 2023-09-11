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
    <div class="ml-auto project-form">
    <form method="post" action="/project/insert.do" >
<%--        <div class="row mb-3">--%>
<%--            <label class="col-sm-2 col-form-label input-border">프로젝트 아이디</label>--%>
<%--            <input name="projectId" class="col-sm-10">--%>
<%--        </div>--%>
        <div class="row mb-3">
            <label for="projectId" class="col-sm-2 col-form-label">프로젝트 아이디</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="projectId">
            </div>
        </div>
        <br>
        <div class="row mb-3">
            <label for="projectName" class="col-sm-2 col-form-label">프로젝트</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="projectName">
            </div>
        </div>
        <br>
        <c:set var="ymd" value="<%=new java.util.Date()%>" />
        <div class="row mb-3">
            <label for="startDate" class="col-sm-2 col-form-label">시작일</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="<fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd" /> " id="startDate">
            </div>
        </div>
        <div class="row mb-3">
            <label for="endDate" class="col-sm-2 col-form-label">종료일</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="endDate">
            </div>
        </div>
        <br>
        <div class="row mb-3">
            <label for="addMember" class="col-sm-2 col-form-label">멤버추가</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="addMember">
            </div>
        </div>
        <br>
        <div class="row mb-3">
            <label for="script" class="col-sm-2 col-form-label">설명</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="script"></textarea>
            </div>
        </div>
        <br>
<%--        <div class="col mb-3">--%>
<%--            <label class="col-sm-2 col-form-label input-border">시작일</label>--%>
<%--            <input name="startDate" class="col-sm-10" value="<fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd" />">--%>
<%--        </div><br>--%>
<%--        <div class="row mb-3">--%>
<%--            <label class="col-sm-2 col-form-label input-border">설명</label>--%>
<%--            <br>--%>
<%--            <textarea name="script" class="col-sm-10"></textarea>--%>
<%--        </div>--%>
<%--        <br>--%>
        <button type="submit" class="col-sm-12">프로젝트 추가</button>
    </form>
    </div>
</div>
</body>
</html>