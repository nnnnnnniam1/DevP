<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>

<link rel="stylesheet" href="/resources/css/issue.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="mv-100 container">
<h2 class="pTitle">${project.projectName}</h2>

	<div class="issue-list">
		<div class="d-flex flex-row-reverse">
			<button type="submit" onclick="location.href='/issue/write.do?projectId=${projectId}'" class="btn main mx-3">작성</button>
		</div>
	    <div class="row">
			<c:forEach var="status" items="${statusarr}">
			    <div class="col-md-4">
			    <div class = "m-2 card">
			        <div class="card-header">
					    ${status}
					  </div>
					  <div class="card-body">
			        <c:forEach items="${issueList}" var="issue">
			            <c:if test="${issue.status eq status}">
			            	<div class="item">
			            		<a href="/issue/detail.do?issueId=${issue.issueId}" class="text-reset text-decoration-none">
								  <h2 class="title">${issue.title}</h2>
								  
								  <small class="text-body-secondary">${issue.date}<br>${issue.name}</small>
								  
								</a>
			            	</div>
			            	
			                
			            </c:if>
			        </c:forEach>
			    </div>
			    </div>
			    </div>
			</c:forEach>
	    </div>
    </div>
</div>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>