<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet"  href="/resources/css/leaderDetail.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="full container">
    <div class="mx-4">
        <div class="row">
            <h1 class="col-auto">프로젝트 목록</h1>
            <button class="btn btn-outline-success col-auto" type="button" onclick="location.href='/project/completeProjectList.do'">완료프로젝트</button>
        </div>
        <div class="row">
                <div class="">
                        <c:forEach items="${projectList}" var="project">
                                <div class="">
                                    <a href="/project/detail.do?projectId=${project.projectId}" class="text-reset text-decoration-none">
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
                        </c:forEach>
                </div>
        </div>
    </div>
</div>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>