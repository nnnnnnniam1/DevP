<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet" href="/resources/css/issue.css">
<style>
    .tag-container {
        border: 1px solid #ccc;
        display: inline-block;
        padding: 5px;
        margin: 5px;
    }
    
    .tag {
        display: inline-block;
        padding: 5px;
        background-color: #007bff;
        color: #fff;
        border-radius: 5px;
        margin-right: 5px;
        cursor: pointer;
    }
    </style>
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="container">
    <div class="issue-wrapper">
        <div class="mt-5">
            <h2 class="pTitle">${project.projectName} - 이슈작성</h2>
            <form method="post" class="p-3" action="/issue/write.do"
                id="issue-form">
                <input type="hidden" name="projectId" value="${project.projectId}">
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label">${project.projectName}</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext"
                            name="projectTitle" value="${project.projectName}">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label">안건</label>
                    <div class="col-sm-10">
                        <select class="form-select" aria-label="Default select example"
                            name="category">
                            <option value="" selected>선택</option>
                            <option value="논의">논의</option>
                            <option value="개발">개발</option>
                            <option value="제안">제안</option>
                        </select>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label">작성자 </label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext"
                            name="writer" value="${ user.name }">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label">제목</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="title">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label">업무선택</label>
                    <div class="col-sm-10">
                        <select id="chooseTask" name="taskId" class="form-select col-sm-10">
                            <option disabled selected>선택</option>
                            <option value="">업무아님</option>
                            <c:forEach items="${taskList}" var="task">
                                <option value="${task.taskId}" ${task.taskId == taskId ? 'selected' : ''} >${task.detail}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label">알림</label>
                    <div class="col-sm-7">
                        <div id="sendingEmail-container"></div>
                        <input id="sendingEmail" type="hidden" class="form-control"
                            name="sendingEmail">
                    </div>
                    <div class="col-sm-3">
                        <select id="chooseMember" class="form-select col-sm-10">
                            <option>멤버 선택</option>
                            <c:forEach items="${memberList}" var="member">
                            	<c:if test="${ user.id != member.userId }">
                                <option value="${member.email}">${member.userId}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label">내용</label>
                    <div class="col-sm-10">
                        <textarea type="text" class="form-control" name="content"
                            style="height: 15vh"></textarea>
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary" style="float: right">등록</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
// 중복 태그 방지를 위한 Set 데이터 구조 사용
    $(document).ready(
            function() {
                $("#chooseMember").change(
                        function() {
                            // 선택된 옵션의 값을 <input> 요소의 현재 값에 추가합니다.
                            var selectedValue = $(this).val();
                            var currentInputValue = $("#sendingEmail")
                                    .val();
                            addTag(selectedValue);
                        });
                // 중복 태그 방지를 위한 Set 데이터 구조 사용
                var tagsSet = new Set();

                // 태그를 추가하는 함수
                function addTag(selectedValue) {
                    var tagText = selectedValue;

                    if (tagText !== "") {
                        if (!tagsSet.has(tagText)) {
                            var tagContainer = document.createElement("div");
                            tagContainer.className = "tag-container";
                            var tag = document.createElement("span");
                            tag.className = "tag";
                            tag.textContent = tagText;
                            tagContainer.appendChild(tag);
                        
                            var currentInputValue = $("#sendingEmail").val();
                            if (currentInputValue) {
                                // 이미 값이 있는 경우 공백을 추가한 후 선택된 값을 추가합니다.
                                $("#sendingEmail").val(currentInputValue + "," + selectedValue);
                            } else {
                                // 값이 없는 경우 선택된 값을 직접 추가합니다.
                                $("#sendingEmail").val(selectedValue);
                            }

                            // "X" 버튼을 추가하여 태그를 삭제
                            var closeButton = document.createElement("span");
                            closeButton.innerHTML = " &#10006;";
                            closeButton.style.cursor = "pointer";
                            closeButton.addEventListener("click", function() {
                                tagContainer.remove();
                                tagsSet.delete(tagText);
                            });
                            tag.appendChild(closeButton);

                            var tagContainerDiv = document.getElementById("sendingEmail-container");
                            tagContainerDiv.appendChild(tagContainer);
                            tagsSet.add(tagText);
                        } else {
                            alert("이미 존재하는 태그입니다.");
                        }
                    }
                }
            });
</script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>