<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>

<div class="container">
    "${ issue.title }"
  	"${ issue.category }" 
  	"${	issue.date }"
  	"${ issue.name }"
  	"${ issue.count }"
  	"${ issue.content }"
</div>
</body>
</html>