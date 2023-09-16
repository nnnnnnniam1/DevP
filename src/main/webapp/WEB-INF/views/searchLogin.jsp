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

    <div class="searchLogin-wrapper">
        <h2 id="searchTitle">아이디 찾기</h2>
        <p>회원가입 시 사용했던 정보를 입력해주세요.</p>
        <ul class="list">
            <li class="is_on" style="list-style: none;">
                <a href="#" class="btn">아이디</a>
                <div class="searchForm find-id">
                    <form class="findForm" method="post" action="searchId.do" id="login-form">
                        <div class="row mb-3">
                            <label class="col-sm-3 col-form-label" id="input">이름</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" name="name">
                            </div>
                            <label class="col-sm-3 col-form-label" id="input">이메일</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="email" placeholder="devp@devp.com" name="email">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <input class="form-control" type="submit" value="이메일로 아이디 전송">
                        </div>
                    </form>
                </div>
            </li>
            <li style="list-style: none;">
                <a href="#" class="btn">비밀번호</a>
                <div class="searchForm find-pw row mb-3">
                    <div>
                        <form class="findForm" method="post" action="searchPw.do" id="login-form">
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label" id="input">아이디</label>
                                <div class="col-sm-9">
                                    <input class="form-control" type="text" name="id"><br>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label" id="input">이메일</label>
                                <div class="col-sm-7">
                                    <input class="form-control" type="email" placeholder="devp@devp.com" name="email">
                                </div>
                                <div class="col-sm-2">
                                    <input class="form-control formBtn" type="submit" value="보내기"><br>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <form class="codeForm findForm" method="post" action="checkCode.do" id="login-form">
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label" id="input">인증번호</label>
                                <input type="hidden" id="authKey" value="${authKey}">
                                <div class="col-sm-7">
                                    <input class="form-control" type="text" name="input-code" id="input-code">
                                </div>
                                <div class="col-sm-2">
                                    <input class="form-control check-code formBtn" type="submit" value="인증"><br>
                                </div>
                            </div>
                            <input type="hidden" id="successed" value="${success}">
                            <p id="result"><p><br>
                        </form>
                        <div class="mb-3">
                            <input class="form-control" type="button" id="resetPwButton" value="비밀번호 재설정">
                        </div>
                    </div>
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