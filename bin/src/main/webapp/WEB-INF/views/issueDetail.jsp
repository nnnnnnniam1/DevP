<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>

<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<%
String username = (String) session.getAttribute("name");
String userId = (String) session.getAttribute("userId");
%>
<div class="container">
	<div class="issue-wrapper">
		<div class="mt-5">
	        <h1 class="mb-4">${project.projectName} 이슈</h1>
	        <p class="h3">${ issue.title }</p>
			<div class="d-flex">
				<div class="flex-grow-1">
					<small class="text-body-secondary">${ issue.category } | ${issue.date} | ${issue.name} | ${ issue.count }</small>
				</div>
				<c:if test = "${ issue.userId eq sessionScope.id}">
		        <div class="d-flex flex-row-reverse">
					<form id="solveForm" action="/issue/solve.do" method="post" class="my-0 mx-1">
		  			  <input type="hidden" name="projectId" value = "${ issue.projectId }">
		  			  <input type="hidden" name="issueId" value = "${ issue.issueId }">
				      <button type="submit" id = "solveButton" class="btn btn-primary">해결</button>
				    </form>
				    <button type="button" onclick="location.href='/issue/modify/view.do?issueId=${issue.issueId}'" class="btn btn-primary">수정</button>
			    </div>
			    </c:if>
		    </div>
	        <div class="card text-bg-light mb-3">
	        	<div class="card-body" style="min-height: 30vh">
	        		${ issue.content }
	        	</div>
	       	</div>
		</div>
		<div class = "card text-bg-light">
		  	<c:forEach items="${commentList}" var="comment">
		  		<div class="item m-2">
		  			<small class="text-body-secondary">${comment.date}</small>
					<p><strong>${comment.writer}</strong> ${comment.content}</p>
				</div>
			</c:forEach>
			<form method="post" action="../comment/write.do" id="commentForm" class = "p-2 my-0">
	  			  <input type="hidden" id="issueId" name="issueId" value = "${ issue.issueId }">
        		  <input type="hidden" id="writer" name= "writer" value="<%= username %>">
	       		<div class="form-floating">
				  <textarea class="form-control" name="content" placeholder="Leave a comment here" id="floatingcontent" style="height: 10vh"></textarea>
				  <label for="floatingcontent">댓글</label>  
				</div>
		        <button type="submit" class="btn btn-primary m-3" style="float: right">등록</button>
	    	</form>
		</div>
	</div>
</div>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>