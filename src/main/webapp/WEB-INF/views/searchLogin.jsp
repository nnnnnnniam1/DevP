<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/searchLoginStyles.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
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

                        <label class="form-label" id="input">인증번호</label>
                        <input class="form-input" type="text" name="input-code">
                        <input id="checkCode" type="button" value="인정번호 확인">
                        <p id="checkingCode"><p>
                        <br>
                        <input type="submit" value="비밀번호 재설정">
                    </form>
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

            // Assuming you are using jQuery for AJAX form submission
            $.ajax({
                type: 'POST',
                url: 'searchPw.do',
                data: $(this).serialize(),
                success: function (response) {
                    // Update the activeTabIndex to the "비밀번호 찾기" tab
                    activeTabIndex = 1;
                    // Refresh the tab display
                    showActiveTab();
                },
                error: function (error) {
                    console.log('Error: ' + error);
                    // Handle error case if needed
                }
            });
        });

        // Function to show the active tab on page load or reload
        function showActiveTab() {
            for (let i = 0; i < tabList.length; i++) {
                if (i === activeTabIndex) {
                    tabList[i].classList.add('is_on');
                    document.getElementById('searchTitle').innerText = (i === 0) ? "아이디 찾기" : "비밀번호 찾기";
                } else {
                    tabList[i].classList.remove('is_on');
                }
            }
        }

        // Call the function on page load
        showActiveTab();



</script>
</body>
</html>