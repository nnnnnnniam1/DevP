<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet" href="/resources/css/member.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="container">
<h2 class="pTitle">${project.projectName}</h2>

    
    <div class="member-wrapper">
        <p class="manageMemberWrapper">멤버관리</p>
        <c:forEach items="${memberList}" var="member">
            <div class="member-item">
                <div class="item-label member-name" style="width: 10%">${member.userName}</div>
                <c:choose>
                    <c:when test="${empty member.leader}">
                        <div class="tag type01" id="memberTag">Member</div>
                    </c:when>
                    <c:otherwise>
                        <div class="tag type01" id="leaderTag">Leader</div>
                        <!-- 빈 칼럼 -->
                    </c:otherwise>
                </c:choose>
                <div class="item-label tag type02" id="roleTag">${member.role}</div>
                <c:choose>
                    <c:when test="${member.position eq '팀장'}">
                        <div class="item-label tag type03" id="positionTag">팀장</div>
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
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>