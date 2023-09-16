<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>
<%
String username = (String) session.getAttribute("name");
%>
<div class="container">
    "${ issue.title }"
  	"${ issue.category }" 
  	"${	issue.date }"
  	"${ issue.name }"
  	"${ issue.count }"
  	"${ issue.content }"
  	<form method="post" action="../comment/write.do" id="commentForm">
  		<input type="hidden" id="issueId" name="issueId" value = "${ issue.issueId }">
        <input type="text" id="writer" name= "writer" value="<%= username %>" readonly="readonly"><br>
        <textarea id="content" name="content" placeholder="댓글 내용"></textarea><br>
        <input type="submit" value="댓글 작성">
    </form>
  	<c:forEach items="${commentList}" var="comment">
  		<div class="item">
			<p>${comment.writer}</p>
		    <p>${comment.date}</p>
		    <p>${comment.content}</p>
		</div>
	</c:forEach>
</div>
</body>
</html>