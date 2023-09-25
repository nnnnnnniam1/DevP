<%@page import="java.io.Console"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="sidebar.jsp"%>
<head>
<style>
    .card:hover {
      background-color: #f8f9fa; /* 호버 시 배경색 변경 */
    }
  </style>
  <script>
    function sendGetRequest() {
        window.location.href = '/issue/list.do?projectId=1';
    }
  </script>
</head>
<body>
<%
String username = (String) session.getAttribute("name");
String userId = (String) session.getAttribute("userId");
%>
<div class="container">
	<h1 class="my-5">Moment</h1>
	<div class="row m-3">
		<div class = "col-8">
		<div class = "mb-1">
			<span class="badge rounded-pill bg-dark">Member</span>
			<span class="badge rounded-pill bg-light text-dark">BE</span>
		</div>
			<div class="progress mb-2">
			  <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
			<div class="progress">
			  <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
		</div>
		<div class = "col-4 taskBox" onclick="location.href='/project/myTask.do'">
			<div class = "card">
				<div class ="row">
					<div class="col-10">
						<small class="text-muted">지난 업무</small>
					</div>
					<div class="col-2">
						<span>0</span>
					</div>
					<div class="col-10">
						<small class="text-muted">진행 중인 업무</small>
					</div>
					<div class="col-2">
						<span>0</span>
					</div><div class="col-10">
						<small class="text-muted">완료된 업무</small>
					</div>
					<div class="col-2">
						<span>0</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class = "row m-3">
			<div class = "col-4 d-flex">
				<div class="card w-100">
					<div class="card-body">	
					    <h5 class="card-title">Todo</h5>
					    <a href="#" class="	">Card link</a><br>
					    
					    <a href="#" class="	">Card link</a><br>
					    
					    <a href="#" class="	">Card link</a><br>
					  </div>
				</div>
			</div>
			<div class = "col-8">
				<div class = "row">
					<a href="/issue/list.do?projectId=1" class="text-reset text-decoration-none">
					</a>
					<div class = "col-6 d-flex" style="min-height: 30vh">
						<div class="card w-100" onclick="sendGetRequest()">
								<div class="card-body">
								    <h5 class="card-title">Issue</h5>
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
							<div class="card-body">
							    <h5 class="card-title">Member</h5>
							    <a href="#" class="card-link">Card link</a>
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
</body>
</html>