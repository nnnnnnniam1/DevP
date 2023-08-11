<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/searchLoginStyles.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>
<div class="container">
    <div class="login-wrapper">
        <form method="post" action="changePw.do" class="change-form">
            <label id="inputLabel">새로운 비밀번호<br>
            <input type="password" name="password" id="password1"><br>
            <label id="inputLabel">비밀번호 확인<br>
            <input type="password" name="password2" id="password2">
            <br>
            <input type="button" id="changePwButton" value="비밀번호 재설정">
        </form>
    </div>
</div>
<script>
    document.getElementById('changePwButton').addEventListener('click', function () {
        var p1 = document.getElementById("password1").value;
        var p2 = document.getElementById("password2").value;

        if(p1.length<8){
            alert("비밀번호는 8자 이상이어야합니다.");
        }
        if (p1 != p2){
            alert("비밀번호가 일치하지 않습니다.");
        } else {
            $.ajax({
                type:'POST',
                url: 'changePw.do',
                data: {password: p1},
                success: function(response){
                    console.log(response);
                    window.location.href="/changePwSuccess.do"
                },
                error: function(error){
                    console.log("비밀번호 변경 중 오류 발생");
                    console.log(error);
                }
            });
        }
    });
</script>
</body>
</html>