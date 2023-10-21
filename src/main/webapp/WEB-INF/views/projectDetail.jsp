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
<div class="mw-100 container">
    <div>
        <h1 class="my-5">${project.projectName}
            <c:if test="${myData.leader eq myData.userId}">
                <button class="btn btn-outline-success" type="button" onclick="location.href='/leader/detail.do?projectId=${project.projectId}'">leader</button>
            </c:if>
            <div class="btn-group">
                <button type="button" class="btn main dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">색상</button>
                <ul class="dropdown-menu">
                    <c:forEach items="${colorMap}" var="color" varStatus="loop">
                        <li><span class="Dropdown-item" style="background-color:#${color.value};" onclick="location.href='/project/color/set.do?projectColor=${color.value}'"></span></li>
                    </c:forEach>
                </ul>
            </div>
</h2>

	<div class="row m-b-1">
		<div class = "col-8 m-t-1">
		    <div class = "mb-1">
		    <c:choose>
		        <c:when test="${myData.leader eq myData.userId}">
		            <span class="badge rounded-pill tag type01">Leader</span>
		        </c:when>
		        <c:otherwise>
		            <span class="badge rounded-pill tag type01">Member</span>
		        </c:otherwise>
            </c:choose>
			<span class="badge rounded-pill tag type02">${myData.role}</span>
		    </div>
		    
			<div class="progress m-t-2">
			  <div class="progress-bar type01" role="progressbar" style="width: ${project.progress}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
			<div class="progress m-t-1">
			  <div class="progress-bar type02" role="progressbar" style="width: ${myData.progress}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
			
		</div>
		<div class = "col-4 summaryCount" onclick="location.href='/project/myTask.do'">
			<div class = "">
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
	<div class = "row m-b-1">
			<div class = "col-4 d-flex">
				<div class="card w-100 task" onclick="location.href='myTask.do'">
					<div class="card-body">	
					    <h5 class="card-title">Todo</h5>
					    <c:forEach items="${myTask}" var="task">
				                <div class="task-info">
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
											<small class="text-body-secondary  tag type03">${issue.name}</small>
											<small class="text-body-secondary"> ${issue.title}</small>
											</a>
						                </div>
							        </c:forEach>
								  </div>	
						</div>
					</div>
					<div class = "col-6 d-flex">
						<div class="card w-100">
							<div class="card-body" onclick="location.href='/project/member/list.do'">
							    <h5 class="card-title">멤버</h5>
							    <c:forEach items="${memberList}" var="member">
						                <div class="item">
							                <small class="text-body-secondary tag type03">${member.userName} </small>
							                <small class="text-body-secondary tag type02"> ${member.role}</small>
						                </div>
							        </c:forEach>
							  </div>
						</div>
					</div>
					<div class = "col-12">
						<div class="card mt-4">
							<div class="card-body">
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