<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="sidebar.jsp"%>

<html>
<head>
<title>Title</title>
<script src="script.js"></script>
</head>
<body>
	<div class="container">
		<div class="main-text">
			<h1 class="my-5">${title}</h1>
			<button type="button" class="visually-hidden"
				<c:if test="${login eq '로그인'}"> onclick="location.href='login.do'"</c:if>
				<c:if test="${login eq '로그아웃'}"> onclick="location.href='logout.do'"</c:if>>
				${login}</button>
		</div>
		<div class="row" >
			<div class="col-sm-4" >
				<div class="card" style="min-height: 300px;">
					<div class="card-body">
						<h5 class="card-title">Todo</h5>
						<c:forEach items="${taskList}" var="task">
							<div class="item">
								<small>${task.category}</small>
								<smail>${task.detail}</smail>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="card" onclick="location.href='/project/list.do'">
					<div class="card-body">
						<div class="card-title">진행 중인 프로젝트</div>
						<a href='/project/insert.do' class="btn btn-outline-dark">프로젝트
							추가</a>
					</div>

				</div>
				<div class="row">
					<div class="col-sm-4">
						<div class="card">
							<div class="card-body">
								<c:forEach items="${issueList}" var="issue">
									<a href="/issue/detail.do?issueId=${issue.issueId}"
										class="text-reset text-decoration-none">
										<div class="card-title">${issue.title}</div>
										<div class="">
											<small class="text-body-secondary">${issue.date}<br>${issue.name}</small>
										</div>
									</a>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">달력</h5>
								<%@include file="mainCalendar.jsp"%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>