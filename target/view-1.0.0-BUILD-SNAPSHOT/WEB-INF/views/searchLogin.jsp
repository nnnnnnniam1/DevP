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
<div>
ddd
</div>
<div class="container">
    <h2 id="searchTitle">아이디 찾기</h2>
    <p>회원가입 시 사용했던 정보를 입력해주세요.</p>
    <div class="searchLogin-wrapper">
        <ul class="list">
            <li class="is_on">
                <a href="#" class="btn">아이디</a>
                <div class="searchForm find-id">
                    <form class="findForm" method="post" action="searchId.do" id="login-form">
                        <label class="form-label" id="input">이름</label>
                        <input class="form-input" type="text" name="name"><br>
                        <label class="form-label" id="input">이메일</label>
                        <input class="form-input" type="text" name="email-id">@<input class="form-input" type="text" name="email"><br>
                        <input type="submit" value="이메일로 아이디 전송">
                    </form>
                </div>
            </li>
            <li>
                <a href="#" class="btn">비밀번호</a>
                <div class="searchForm find-pw">
                    <form class="findForm" method="post" action="searchPw.do" id="login-form">
                        <label class="form-label" id="input">아이디</label>
                        <input class="form-input" type="text" name="id"><br>
                        <label class="form-label" id="input">이메일</label>
                        <input class="form-input" type="text" name="email-id">@<input class="form-input" type="text" name="email">
                        <input type="submit" value="이메일 인증"><br>
                    </form>
                    <form class="codeForm" method="post" action="checkCode.do" id="login-form">
                        <label class="form-label" id="input">인증번호</label>
                        <input type="hidden" id="authKey" value="${authKey}">
                        <input class="form-input" type="text" name="input-code" id="input-code">
                        <input type="hidden" id="successed" value="${success}">
                        <input class="check-code" type="submit" value="인증번호 확인">
                        <p id="result"><p><br>
                    </form>
                        <input type="button" id="resetPwButton" value="비밀번호 재설정">
                </div>
            </li>
        </ul>
    <div>
</div>
<script>
    const tabList = document.querySelectorAll('.searchLogin-wrapper .list li');
    var findIdForm = document.querySelector('.searchForm.find-id form');
    var findPwForm = document.querySelector('.searchForm.find-pw form');
    var findIdInputs = document.querySelectorAll('.searchForm.find-id .form-input');
    var findPwInputs = document.querySelectorAll('.searchForm.find-pw .form-input');
    var success = false;

    for (var i = 0; i < tabList.length; i++) {
        tabList[i].querySelector('.btn').addEventListener('click', function (e) {
            if (i == 0) {
                document.getElementById('searchTitle').innerText = "아이디 찾기";
                for (var k = 0; k < findIdInputs.length; k++) {
                    findIdInputs[k].value = ""; // 아이디 탭 클릭 시 해당 input 요소들의 값을 초기화
                }
            } else {
                document.getElementById('searchTitle').innerText = "비밀번호 찾기";
                for (var k = 0; k < findPwInputs.length; k++) {
                    findPwInputs[k].value = ""; // 비밀번호 탭 클릭 시 해당 input 요소들의 값을 초기화
                }
            }
            e.preventDefault();
            for (var j = 0; j < tabList.length; j++) {
                tabList[j].classList.remove('is_on');
            }
            this.parentNode.classList.add('is_on');
        });
    }

    document.querySelector('.searchForm.find-pw form').addEventListener('submit', function (e) {
        // Prevent the default form submission behavior
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'searchPw.do',
            data: $(this).serialize(),
            success: function (response) {
                // Update the activeTabIndex to the "비밀번호 찾기" tab
                activeTabIndex = 1;

                document.getElementById("authKey").innerText = response.authKey;
                alert("인증번호를 보냈습니다.");

                // Refresh the tab display
                showActiveTab();
            },
            error: function (error) {
                console.log('Error: ' + error);
                // Handle error case if needed
            }
        });
    });


    for (let i = 0; i < tabList.length; i++) {
        tabList[i].querySelector('.btn').addEventListener('click', function (e) {
            if (i === 0) {
                document.getElementById('searchTitle').innerText = "아이디 찾기";
                for (var k = 0; k < findIdInputs.length; k++) {
                    findIdInputs[k].value = ""; // 아이디 탭 클릭 시 해당 input 요소들의 값을 초기화
                }
            } else {
                document.getElementById('searchTitle').innerText = "비밀번호 찾기";
                for (var k = 0; k < findPwInputs.length; k++) {
                    findPwInputs[k].value = ""; // 비밀번호 탭 클릭 시 해당 input 요소들의 값을 초기화
                }
            }
            e.preventDefault();
            for (var j = 0; j < tabList.length; j++) {
                tabList[j].classList.remove('is_on');
            }
            this.parentNode.classList.add('is_on');
        });
    }
        
    document.querySelector('.searchForm.find-pw .codeForm').addEventListener('submit', function (e) {
        // Prevent the default form submission behavior
        e.preventDefault();
        if(document.getElementById("authKey") === ""){
            alert("인증번호를 입력하세요.");
        } else {
            $.ajax({
                type: 'POST',
                url: 'checkCode.do',
                data: $(this).serialize(),
                success: function (response) {
                    if (response === "success") {
                        $('#result').text("인증되었습니다.");
                        success = true;
                    } else {
                        $('#result').text("다시 입력해주세요");
                        success = false
                    }
                    showActiveTab();
                },
                error: function (error) {
                    console.log('Error: ' + error);
                    // Handle error case if needed
                }
            });
        }
    });

    document.getElementById("resetPwButton").addEventListener('click', function() {
        // successed 값이 "success"인 경우에만 비밀번호 재설정 페이지로 이동
        if (success) {
            location.href = 'changePw.do';
        } else {
            // successed 값이 "success"가 아닌 경우, 즉 인증이 안 된 경우에는 알림창을 띄움
            alert("비밀번호 재설정을 위해서는 먼저 인증을 완료해주세요.");
        }
    });

    showActiveTab();



</script>
</body>
</html>