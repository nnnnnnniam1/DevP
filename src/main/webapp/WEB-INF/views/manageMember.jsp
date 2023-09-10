<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/manageMemberStyles.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@include file="sidebar.jsp"%>
<head>
    <title>멤버관리</title>
</head>
<body>
<div class="container">
    <p class="projectName">${projectName}</p>
    <p class="semiTitle">프로젝트관리-멤버 추가/삭제</p>
    <div class="manage-wrapper">
        <div class="addMember">
            <form class="manageMemberWrapper" action="/project/addMember.do" method="post">
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label"> 멤버추가</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="email" placeholder="devp@devp.com" name="email">
                    </div>
                    <div class="col-sm-2"><input class="btn btn-primary" type="submit" value="send"></div>
                </div>
            </form>
        </div>
    </div>
    <div  class="member-wrapper">
        <p class="manageMemberWrapper">멤버 관리</p>
        <form class="manageForm" method="post" action="/project/updateMember.do">
            <table class="table" width=500px;>
                <thread>
                    <tr>
                        <th class="col-md-2" scope="col" width="10%">이름</th>
                        <th class="col-md-4" scope="col">이메일</th>
                        <th class="col-md-2" scope="col">직책</th>
                        <th class="col-md-2" scope="col">역할</th>
                        <th class="col-md-2" scope="col">상황</th>
                        <th class="col-md-2" scope="col">삭제</th>
                    </tr>
                </thread>
                <tbody>
                    <c:forEach items="${memberList}" var="member">
                        <tr>
                            <th scope="row">${member.userName}</td>
                            <td>${member.email}</td>
                            <td>
                                <select name="role">
                                    <option value="" <c:if test="${empty member.role}">selected</c:if>>선택</option>
                                    <c:forEach items="${roleMap}" var="option">
                                        <option value="${option.value}"
                                            <c:if test="${option.value eq member.role}">
                                                    selected
                                            </c:if>
                                            >${option.key}
                                    </c:forEach>
                                </select>
                            </td>
                            <input type="hidden" value="${projectId}" name="projectId">
                            <input type="hidden" value="${member.userId}" name="userId">
                            <td>
                                <select name="position">
                                    <option value="" <c:if test="${empty member.position}">selected</c:if>>선택</option>
                                    <c:forEach items="${positionMap}" var="option">
                                        <option value="${option.value}"
                                            <c:if test="${option.value eq member.position}">
                                                    selected
                                            </c:if>
                                            >${option.key}
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <c:if test="${member.status != '1'}">
                                    대기
                                </c:if>
                            </td>
                            <td>
                                <input type="button" value="삭제" onclick="deleteMember('${member.userId}','${projectName}', ${projectId})" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="button" onclick="updateMembers()" value="수정">
        </form>
    </div>
</div>
</body>
</html>
