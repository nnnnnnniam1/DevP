<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/loginStyles.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<%@include file="sidebar.jsp"%>
<head>
</head>
<body>
<div class="container">
    <div class="container">
        <h2 id="searchTitle">아이디 찾기</h2>
        <p>회원가입 시 사용했던 정보를 입력해주세요.</p>
        <div class="searchLogin-wrapper">
            <ul class="list">
                <li class="is_on">
                    <a href="#" class="btn">아이디</a>
                    <div class="searchForm">
                        <form method="post" action="searchId.do" id="login-form">
                            <label id="inputLabel">이름
                            <input type="text" name="name"><br>
                            <label id="inputLabel">이메일
                            <input type="text" name="email-id">@<input type="text" name="email"><br>
                            <input type="submit" value="이메일로 아이디 전송">
                        </form>
                    </div>
                </li>
                <li>
                    <a href="#" class="btn">비밀번호</a>
                    <div class="searchForm">
                        <form method="post" action="searchPw.do" id="login-form">
                            <label id="inputLabel">아이디<br>
                            <input type="text" name="userid"><br>
                            <label id="inputLabel">이메일<br>
                            <input type="text" name="email-id">@<input type="text" name="email">
                        </form>
                            <input type="submit" value="이메일 인증"><br>
                            <label id="inputLabel">인증번호 입력<br>
                            <input type="text" name="code">
                            <input type="button" value="인정번호 확인"><br>
                            <input type="submit" value="비밀번호 재설정">
                    </div>
                </li>
            </ul>
        <div>
    </div>
</div>
<script>
    const tabList = document.querySelectorAll('.searchLogin-wrapper .list li');

    for(var i = 0; i < tabList.length; i++){
        tabList[i].querySelector('.btn').addEventListener('click', function(e){
            if(i==0) {document.getElementById('searchTitle').innerText = "아이디 변경";}
            else {document.getElementById('searchTitle').innerText = "비밀번호 변경";}
            e.preventDefault();
            for(var j = 0; j < tabList.length; j++){
                tabList[j].classList.remove('is_on');
            }
            this.parentNode.classList.add('is_on');
        });
    }
</script>
</body>
</html>