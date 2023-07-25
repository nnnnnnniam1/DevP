<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/loginStyles.css">
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>
<div class="container">
    <div class="login-wrapper">
            <form method="post" action="login.do" id="login-form">
                <label id="inputLabel">아이디<br>
                <input type="text" name="id" placeholder="id" value="${id}"><br>
                <label id="inputLabel">비밀번호<br>
                <input type="password" name="password" placeholder="Password"><br>
                <label for="remember-check">
                    <input type="checkbox" name="saveId" id="remember-check" ${chekced}} >아이디 저장
                </label><br>
                <input type="submit" value="Login">
            </form>
            <a href="#">아이디/비밀번호 찾기</a>
            <a href="#">회원가입</a>
        </div>
</div>
</body>
</html>
