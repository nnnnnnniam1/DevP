<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/manageMemberStyles.css">
<link rel="stylesheet"  href="/resources/css/base.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@include file="sidebar.jsp"%>
<head>
    <title>완료프로젝트</title>
</head>
<body>
<div class="container">
    <p class="semiTitle">완료/삭제된 프로젝트</p>
    <div class="member-wrapper">
        <p class="manageMemberWrapper formLabel col-form-label">완료 프로젝트</p>
        <div>
            <c:if test="${empty completeProjectList}">
                <div style="height:10em;"></div>
            </c:if>
            <c:forEach items="${completeProjectList}" var="project">
                <div class="">
                    <a href="#" class="text-reset text-decoration-none">
                        <div class="card wd-75 text-bg-light">
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
                                <div class="memberProgress">
                                    <span class="text-body-secondary">개인진행률</span>
                                    <div class="progress">
                                        <div style="width: ${project.memberProgress}%;" class="progress-bar" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                    </div>
                <br>
            </c:forEach>
        </div>
        <div  class="member-wrapper">
        <p class="manageMemberWrapper formLabel col-form-label">삭제 프로젝트</p>
        <div>
            <c:if test="${empty deleteProjectList}">
                <div style="height:10em;"></div>
            </c:if>
            <c:forEach items="${deleteProjectList}" var="project">
                <div class="">
                    <a href="#" class="text-reset text-decoration-none">
                        <div class="card wd-75 text-bg-light">
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
                                <div class="memberProgress">
                                    <span class="text-body-secondary">개인진행률</span>
                                    <div class="progress">
                                        <div style="width: ${project.memberProgress}%;" class="progress-bar" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                    </div>
                <br>
            </c:forEach>
        </div>
    </div>
</div>
<script src="/resources/js/manageMemberScript.js"></script>
</body>
</html>
