<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>

<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">경고!!</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          이슈를 삭제하시겠습니까?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
          <button type="button" onclick="sendDeleteRequest()" class="btn btn-primary">확인</button>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
      <div class="issue-wrapper">
          <div class="mt-5">
              <h2 class="pTitle">${project.projectName} - 이슈 수정</h1>
              <form method="post" class="p-3" action="/issue/modify.do" id="issue-form">
                   <input class = "issueInput" type = "hidden" id="issueId" name="issueId" value = "${issue.issueId}"><br>
                   <input class = "issueInput" type = "hidden" id="projectId" name="projectId" value = "${issue.projectId}"><br>
                   <%-- <input class = "issueInput" type = "text" name="taskId" value = "${ issue.taskId }"><br> --%>
                   <div class="mb-3 row">	
                      <label class="col-sm-2 col-form-label">프로젝트</label>
                      <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" name="projectTitle" value="프로젝트">
                      </div>
                    </div>
                    <div class="mb-3 row">
                      <label class="col-sm-2 col-form-label">안건</label>
                      <div class="col-sm-10">
                        <select class="form-select" aria-label="Default select example" name="category">
                            <option value="${ issue.category }" selected>${ issue.category }</option>
                            <option value="논의">논의</option>
                            <option value="개발">개발</option>
                            <option value="제안">제안</option>
                          </select>
                      </div>
                    </div>
                    <div class="mb-3 row">
                      <label class="col-sm-2 col-form-label">작성자	</label>
                      <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" name="writer" value="${ issue.name }">
                      </div>
                    </div>
                    <div class="mb-3 row">
                      <label class="col-sm-2 col-form-label">제목</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="title" value="${ issue.title }">
                      </div>
                    </div>
                    <div class="mb-3 row">
                      <label class="col-sm-2 col-form-label">알림</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="sendingEmail" value="${ issue.sendingEmail }">
                      </div>
                    </div>
                    <div class="mb-3 row">
                      <label class="col-sm-2 col-form-label">내용</label>
                      <div class="col-sm-10">
                        <textarea type="text" class="form-control" name="content" style="height : 15vh">${issue.content}</textarea>
                      </div>
                    </div>
                    <div class="d-flex flex-row-reverse">
                        <button type="button" onclick="location.href='/issue/detail.do?issueId=${issue.issueId}'" class="btn btn-primary">취소</button>  	
                      <button type="submit" class="btn btn-primary mx-1">수정</button>
                      <button type="button" id = "solveButton" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">삭제</button>
                    </div>
              </form>
                    
          </div>
      </div>
  </div>
  <script>
      function sendDeleteRequest() {
          var form = document.createElement('form');
  
          var issueId = document.getElementById('issueId').value;
          var projectId = document.getElementById('projectId').value;
          form.method = 'POST';
          form.action = '/issue/delete.do';
          
          var issueIdInput = document.createElement('input');
          var projectIdInput = document.createElement('input');
          issueIdInput.type = 'hidden';
          issueIdInput.name = 'issueId';
          issueIdInput.value = issueId;
          projectIdInput.type = 'hidden';
          projectIdInput.name = 'projectId';
          projectIdInput.value = projectId;
          form.appendChild(issueIdInput);
          form.appendChild(projectIdInput);
          
          document.body.appendChild(form);
  
          form.submit();
      }
  </script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>