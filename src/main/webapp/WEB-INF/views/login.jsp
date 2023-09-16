<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet"  href="/resources/css/login.css">
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>
<div class="container">
    <div class="login-wrapper">
            <form class="row justify-content-center loginForm" method="post" action="login.do" id="login-form">
                <div class="mb-3">
                    <label class="form-label">아이디</label>
                    <input class="form-control" type="text" name="id" placeholder="id" value="${id}">
                    <label class="form-label">비밀번호</label>
                    <input class="form-control" type="password" name="password" placeholder="Password">
                    <div class="form-check">
                       <input class="form-check-input" type="checkbox" name="saveId" id="remember-check" ${checked} >
                       <label class="form-label saveIdLabel">아이디 저장</label>
                    </div>
                    <input class="form-control" type="submit" value="Login">
                    <br>
                    <div class="position-relative">
                    <div class="col">
                        <a class="position-absolute top-50 start-0 translate-middle-y" href="searchLogin.do">아이디/비밀번호 찾기</a>
                    </div>
                    <div class="col">
                        <a class="position-absolute top-50 end-0 translate-middle-y" href="#">회원가입</a>
                    </div>
                </div>
            </form>
        </div>
</div>
</body>
</html>