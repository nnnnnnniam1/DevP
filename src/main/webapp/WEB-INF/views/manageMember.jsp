<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/manageMemberStyles.css">
<script src="/resources/js/manageMemberScript.js"></script>

<%@include file="sidebar.jsp"%>
<head>
    <title>멤버관리</title>
</head>
<body>
<div class="container">
    <h1>${projectName}</h1>
    <h2>프로젝트관리-멤버 추가/삭제</h2>
    <div class="manage-wrapper">
        <div class="addMember">
            <form class="addMember-wrapper" action="/project/addMember.do" method="post">
                <label class="form-label"> 멤버추가</label>
                <input type="email" placeholder="devp@devp.com" name="email">
                <input type="submit" value="send">
            </form>
        </div>
    </div>
    <div  class="member-wrapper">
        <h3>멤버 관리</h3>
        <table>
            <tr>
                <th width="50">선택</th>
                <th>이름</th>
                <th>이메일</th>
                <th>직책</th>
                <th>역할</th>
            </tr>
            <c:forEach items="${memberList}" var="member">
                <tr>
            	    <td><input type="checkbox"></td>
            		<td>${member.userName}</td>
            		<td>${member.email}</td>
            		<td>${member.position}</td>
            		<td>${member.role}</td>
            		<td>${member.progress}</td>
            		<td>
            		    <c:if test="${member.status != '0'}">
            		    대기
            		    </c:if>
            		</td>
            		<td>
            		    <input type="button" value="삭제" onclick="deleteMember('${member.userId}','${projectName}', ${projectId})" />
            		</td>
            	</tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
