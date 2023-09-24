<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/searchLogin.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>
<div class="container">
    <div class=".changePw-wrapper">
        <h2>비밀변호 변경</h2><br>
        <form class="row justify-content-center" method="post" action="changePw.do" class="change-form">
            <div class="mb-3">
                <label id="form-label inputLabel">새로운 비밀번호</label>
                <input class="form-control" type="password" name="password" id="password1"><br>
            </div>
            <div class="mb-3">
                <label class="form-label" id="inputLabel">비밀번호 확인</label>
                <input class="form-control" type="password" name="password2" id="password2">
            </div>
            <div class="mb-3">
                <input class="form-control" type="button" id="changePwButton" value="비밀번호 재설정">
            </div>
        </form>
    </div>
</div>
<script>
    document.getElementById('changePwButton').addEventListener('click', function () {
        var p1 = document.getElementById("password1").value;
        var p2 = document.getElementById("password2").value;

        if(p1.length < 8 || p2.length < 8){
            alert("비밀번호는 8자 이상이어야합니다.");
        } else if (p1 != p2){
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