<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet" href="/resources/css/manageMemberStyles.css">
<link rel="Stylesheet" href="/resources/css/manageTask.css">
<link rel="Stylesheet" href="/resources/css/task.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="container">
<h2 class="pTitle">${project.projectName} - 멤버관리</h2>
<div class="contentsBox">
    <p class="labelWrapper m-b-1"> 멤버추가</p>
    <form class="" action="/leader/member/add.do" method="post">
        <div class="addMemberLine row align-items-center">
            <div class="col-sm-10">
                <input type="hidden" value="${project.projectName}" name="projectName" />
                <input class="formInput form-control" type="email" placeholder="devp@devp.com" name="user">
            </div>
            <input class="btn main col-sm-2" type="submit" value="send">
        </div>
    </form>
</div>
<div  class="contentsBox">
    <p class="labelWrapper">멤버 관리</p>
    <form class="manageForm" modelAttribute="MemberVO" name="dataForm" id="dataForm" method="post" action="/leader/member/modify.do" >
        <table class="table" width=500px;>
            <thead>
                <tr>
                    <th class="col-md-2" scope="col" width="10%">이름</th>
                    <th class="col-md-4" scope="col">이메일</th>
                    <th class="col-md-2" scope="col">직책</th>
                    <th class="col-md-2" scope="col">역할</th>
                    <th class="col-md-2" scope="col">상황</th>
                    <th class="col-md-2" scope="col">삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${memberList}" var="member" varStatus="loop">
                    <tr>
                        <input type="hidden" value="${project.projectId}" name="memberVOList[${loop.index}].projectId">
                        <input type="hidden" value="${member.userId}" name="memberVOList[${loop.index}].userId">
                        <td>${member.userName}</td>
                        <td>${member.email}</td>
                        <td>
                        <select class="form-select" name="memberVOList[${loop.index}].position" id="roleSelect">
                            <option value="" <c:if test="${empty member.position}"> selected</c:if>>
                                선택
                            </option>
                            <c:forEach items="${positionMap}" var="option">
                            <option value="${option.value}"
                                <c:if test="${option.value eq member.position}">
                                        selected
                                </c:if>
                                >${option.key}
                            </option>
                            </c:forEach>
                        </select>
                        </td>

                        <td>
                        <select class="form-select" name="memberVOList[${loop.index}].role" id="roleSelect">
                            <option value="" <c:if test="${empty member.role}"> selected</c:if>>
                                선택
                            </option>
                            <c:forEach items="${roleMap}" var="option">
                            <option value="${option.value}"
                                <c:if test="${option.value eq member.role}">
                                        selected
                                 </c:if>
                                >${option.key}
                            </option>
                            </c:forEach>
                        </select>
                        </td>
                        <td>
                            <c:if test="${member.status != '1'}">
                                대기
                            </c:if>
                        </td>
                        <td>
                            <input class="form-control" type="button" value="삭제" onclick="deleteMember('${member.userId}','${project.projectName}', ${project.projectId})" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input class="btn main col-sm-12" type="submit" value="수정">
    </form>
</div>
</div>
<script src="/resources/js/manageMemberScript.js"></script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>