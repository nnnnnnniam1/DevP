<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp" %>
<%@ include file="/WEB-INF/views/include/headerLink.jsp" %>
<link rel="stylesheet" href="/resources/css/leaderDetail.css">
<link rel="stylesheet" href="/resources/css/progress.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp" %>
<!-- 컨텐츠 시작 -->
<%--<div class="topBar">--%>
<%--    <%@include file="leaderTopBar.jsp" %>--%>
<%--</div>--%>
<div class="container leader">
    <h2 class="pTitle">${project.projectName}</h2>
    <ul class="menu">
        <li>
            <button class="btn black" type="button" onclick="location.href='/leader/task/view.do'">일정 및 업무 수정</button>
        </li>
        <li>
            <button class="btn black" type="button" onclick="location.href='/leader/member/view.do'">멤버 추가 및 삭제</button>
        </li>
        <li>
            <button class="btn black" type="button"
                    onclick="completeProject('${project.projectId}','${project.projectName}')">프로젝트 완료
            </button>
        </li>
        <li>
            <button class="btn black" type="button"
                    onclick="location.href='/leader/project/delete/view.do?projectId=${project.projectId}'">프로젝트 삭제
            </button>
        </li>
    </ul>

    <div>
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
</div>
</div>
</body>
<script
= "text/javascript">
function completeProject(projectId, projectName){
if(confirm("["+projectName+"]프로젝트를 완료하시겠습니까?")){
$.ajax({
type: 'GET',
url: '/leader/project/complete.do',
data: {
projectId: projectId
},
success: function(response){
if(response === "success"){
alert("완료되었습니다");
window.location.href="/project/list/view.do";
} else {
alert("오류가 발생했습니다. 다시 시도해주세요");
window.location.href="/leader/detail.do?projectId=${project.projectId}"
}

},
error: function(error){
console.log("에러: "+error);
alert("오류가 발생했습니다. 다시 시도해주세요");
window.location.href="/leader/detail.do?projectId=${project.projectId}"
}
});
}
}
</script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>