<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="sidebar.jsp"%>
<head>
    <link rel="stylesheet" href="/resources/css/issue.css?A">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
    <div class="issue-wrapper">
		<div class="mt-5">
	        <h1 class="mb-4">Moment 이슈 등록 </h1>
	        <form method="post" class="p-3" action="/issue/write.do" id="issue-form">
            	 <input type = "hidden" name="projectId" value = "${projectId}"><br>
            	 <%-- <input type = "hidden" name="taskId" value = "${ taskId }"><br> --%>
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
						  <option value="" selected>선택</option>
						  <option value="논의">논의</option>
						  <option value="개발">개발</option>
						  <option value="제안">제안</option>
						</select>
				    </div>
				  </div>
				  <div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">작성자	</label>
				    <div class="col-sm-10">
				      <input type="text" readonly class="form-control-plaintext" name="writer" value="${ sessionScope.name }">
				    </div>
				  </div>
				  <div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">제목</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="title">
				    </div>
				  </div>
				  <div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">알림</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="sendingEmail">
				    </div>
				  </div>
				  <div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">내용</label>
				    <div class="col-sm-10">
				      <textarea type="text" class="form-control" name="content" style="height : 15vh"></textarea>
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
</script>
</body>
</html>