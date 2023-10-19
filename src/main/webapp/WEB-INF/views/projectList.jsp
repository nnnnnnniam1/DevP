<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet"  href="/resources/css/leaderDetail.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="full container">
	<h2>프로젝트 목록</h2>
	<ul class="tab type01">
		<li class="active">진행중</li>
		<li><a href="/project/completeProjectList.do">완료/삭제</a></li>
	</ul>
    <div class="list-wrap">
    	<ul>
    	 <c:forEach items="${projectList}" var="project">
    		<li>
			
    			<div class="project-list">
    			<a href="/project/detail.do?projectId=${project.projectId}" class="text-reset text-decoration-none">
    				<h3>프로젝트명 : ${project.projectName}</h3>
    				<small class="text-body-secondary tag type01">${project.role}</small>
                    <small class="text-body-secondary tag type02">${project.position}</small>
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
                    </a>
    			</div>
   			
    		</li>
    		</c:forEach>
    	</ul>
    </div>
    
</div>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>