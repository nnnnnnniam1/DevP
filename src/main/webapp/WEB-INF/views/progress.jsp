<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp" %>
<%@ include file="/WEB-INF/views/include/headerLink.jsp" %>
<%@ include file="/WEB-INF/views/include/headerTop.jsp" %>
<link rel="stylesheet" href="/resources/css/leaderDetail.css">
<link rel="stylesheet" href="/resources/css/progress.css">
<!-- 컨텐츠 시작 -->
<div class="topBar">
    <%@include file="leaderTopBar.jsp" %>
</div>
<div class="container leader">
    <h2 class="pTitle">${project.projectName} - 진행률</h2>
    <div class="">
        <span class="progressWrapper">전체 진행률</span>
        <div class="progress" style="margin: 20px">
            <div style="width: ${project.progress}%;" class="progress-bar"/>
        </div>
    </div>
    <div class="">
        <span class="progressWrapper">멤버별 진행률</span>
        <c:forEach items="${memberList}" var="member">
                <div class="progress-item">
                    <div style="width: 100%;">
                        <div class="progress-align">
                            <span class="member-name">${member.userName}</span>
                            <div class="chatting">
                                <button class="image-button"
                                        onclick="location.href='/chat/getChatView.do?userId=${member.userId}'">
                                    <img src="/resources/image/chatIcon.png" alt="버튼 이미지">
                                </button>
                            </div>
                        </div>
                        <div class="progress" style="margin: 20px">
                            <div style="width:${member.progress}%;" class="progress-bar" style="width: 100%;"/>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>