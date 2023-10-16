<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>개발자국</title>
	<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
</head>
<body>
<div id = "sidebar" class="sidebar fixed-top p-3" style="width: 20%; height: 100vh">
	<div class="align-items-center">
		<a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none" id="home">
    		<img src="/resources/image/dogfootprint.png" width="15%" height="100%">
    		<span id="logoLabel">DevP</span>
		</a>
	</div>
	<hr>
    <%@ include file="/WEB-INF/views/include/headerNav.jsp"%>
</div>
