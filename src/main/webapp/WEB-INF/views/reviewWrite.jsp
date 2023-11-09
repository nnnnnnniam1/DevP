<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet" href="/resources/css/base.css">
<link rel="stylesheet" href="/resources/css/projectDetail.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="container">
    <h2 class="pTitle">${projectName}</h2>

    <form method="post" class="p-3" action="/project/review/report.do" id="issue-form">
        <c:forEach items="${memberData}" var="member" varStatus="loop">
            <div class="member-item">
                <input type="hidden" name="evaMemberId" value="${member.userId}" />
                <div class="memberProgress">${member.userName}</div>
                <textarea type="text" class="form-control" name="content" style="height: 15vh"></textarea>
            </div>
        </c:forEach>
        <div>
            <button type="submit" class="btn btn-primary" style="float: right">등록</button>
        </div>
    </form>
</div>
</body>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
