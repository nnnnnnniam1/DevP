<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<style>
    .card:hover {
      background-color: #f8f9fa; /* 호버 시 배경색 변경 */
    }
  </style>
<link rel="stylesheet" href="/resources/css/projectDetail.css">
<link rel="stylesheet"  href="/resources/css/leaderDetail.css">

<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<%
String username = (String) session.getAttribute("name");
String userId = (String) session.getAttribute("userId");
%>
<div class="container">
<h2 class="pTitle none">${project.projectName}</h2>
    <div>
        <h1 class="my-5">${project.projectName}
            <c:if test="${myData.leader eq myData.userId}">
                <button class="btn btn-outline-success" type="button" onclick="location.href='/project/leader.do?projectId=${project.projectId}'">leader</button>
            </c:if>
            <div class="btn-group">
                <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">색상</button>
                <ul class="dropdown-menu">
                    <c:forEach items="${colorMap}" var="color" varStatus="loop">
                        <li><span class="Dropdown-item" style="background-color:#${color.value};" onclick="location.href='/project/setColor.do?projectColor=${color.value}'"></span></li>
                    </c:forEach>
                </ul>
            </div>
        </h1>
	</div>
	<div class="row m-3">
		<div class = "col-8">
		    <div class = "mb-1">
		    <c:choose>
		        <c:when test="${myData.leader eq myData.userId}">
		            <span class="badge rounded-pill bg-dark">Leader</span>
		        </c:when>
		        <c:otherwise>
		            <span class="badge rounded-pill bg-dark">Member</span>
		        </c:otherwise>
            </c:choose>
			<span class="badge rounded-pill bg-light text-dark">${myData.role}</span>
		    </div>
			<div class="progress mb-2">
			  <div class="progress-bar bg-warning" role="progressbar" style="width: ${project.progress}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
			<div class="progress">
			  <div class="progress-bar bg-warning" role="progressbar" style="width: ${myData.progress}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
		</div>
		<div class = "col-4" onclick="location.href='/project/myTask.do'">
			<div class = "card">
				<div class ="row">
					<div class="col-10">
						<small class="text-muted">지난 업무</small>
					</div>
					<div class="col-2">
						<span style="color:red;">${pastTaskCnt}</span>
					</div>
					<div class="col-10">
						<small class="text-muted">진행 중인 업무</small>
					</div>
					<div class="col-2">
						<span>${progressTaskCnt}</span>
					</div><div class="col-10">
						<small class="text-muted">완료된 업무</small>
					</div>
					<div class="col-2">
						<span>${completeTaskCnt}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class = "row m-3">
			<div class = "col-4 d-flex">
				<div class="card w-100" onclick="location.href='myTask.do'">
					<div class="card-body">	
					    <h5 class="card-title">Todo</h5>
					    <c:forEach items="${myTask}" var="task">
				                <div class="item">
					                <small class="text-body-secondary">${task.title}</small>
								</div>
						</c:forEach>
					  </div>
				</div>
			</div>
			<div class = "col-8">
				<div class = "row">
					<div class = "col-6 d-flex" style="min-height: 30vh">
						<div class="card w-100" onclick="sendGetRequest()">
								<div class="card-body">
									<a href="/issue/list.do?projectId=${projectId}" class="text-reset text-decoration-none">
								    <h5 class="card-title">이슈</h5>
									</a>
								    <c:forEach items="${issueList}" var="issue">
						                <div class="item">
							                <a href="/issue/detail.do?issueId=${issue.issueId}" class="text-reset text-decoration-none">
											<small class="text-body-secondary">${issue.name} : ${issue.title}</small>
											</a>
						                </div>
							        </c:forEach>
								  </div>	
						</div>
					</div>
					<div class = "col-6 d-flex">
						<div class="card w-100">
							<div class="card-body" onclick="location.href='/project/member.do'">
							    <h5 class="card-title">멤버</h5>
							    <c:forEach items="${memberList}" var="member">
						                <div class="item">
							                <small class="text-body-secondary">${member.userName} : ${member.role}</small>
						                </div>
							        </c:forEach>
							  </div>
						</div>
					</div>
					<div class = "col-12">
						<div class="card mt-4">
							<div class="card-body">
							    <h5 class="card-title">달력</h5>
								<%@include file="calendar.jsp"%>
							  </div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>