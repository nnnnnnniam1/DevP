<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/member.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@include file="sidebar.jsp"%>
<head>
<title>멤버관리</title>
</head>
<body>
	<div class="container">
		<p class="projectName">${project.projectName}</p>
		<div class="member-wrapper">
			<p class="manageMemberWrapper">멤버관리</p>
			<c:forEach items="${memberList}" var="member">
				<div class="member-item">
					<div class="item-label" style="width: 10%">${member.userName}</div>
					<c:choose>
						<c:when test="${empty member.leader}">
							<div class="item-label tag" id="memberTag">Member</div>
						</c:when>
						<c:otherwise>
							<div class="item-label tag" id="leaderTag">Leader</div>
							<!-- 빈 칼럼 -->
						</c:otherwise>
					</c:choose>
					<div class="item-label tag" id="roleTag">${member.role}</div>
					<c:choose>
						<c:when test="${member.position eq '팀장'}">
							<div class="item-label tag" id="positionTag">팀장</div>
						</c:when>
						<c:otherwise>
							<div class="item-label"></div>
							<!-- 빈 칼럼 -->
						</c:otherwise>
					</c:choose>
					<div class="chatting">
						<button class="image-button" onclick="location.href='/chat/getChatView.do?userId=${member.userId}'">
							<img src="/resources/image/chatIcon.png" alt="버튼 이미지">
						</button>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>