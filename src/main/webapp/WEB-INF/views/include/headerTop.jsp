<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}" />

</head>
<body>
<div class="content-wrap">
<div id="header">
	<a href="/" class="logo" id="home">
   		<img src="/resources/image/dogfootprint.png" />
   		<span id="logoLabel">DevP</span>
	</a>
	<div>
	<ul class="menu">
		<li></li>
		<li><button class="btn black" type="button" onclick="location.href='/logout.do'">Logout</button></li>
	</ul>
	</div>
</div>

<c:if test="${path!='/' && path!='/project/list.do'}">
<div id = "sidebar" class="sidebar" style="width: 20%; height: 100vh">
    <%@ include file="/WEB-INF/views/include/headerNav.jsp"%>
</div>
</c:if>
