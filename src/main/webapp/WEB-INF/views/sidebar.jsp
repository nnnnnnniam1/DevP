<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023/07/25
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="/resources/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/js-cookie/3.0.1/js.cookie.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style type="text/css">
		.sidebar{
			box-shadow: 2px 2px 5px #D3D3D3;
		}
		.sidebar-link {
			transition: all .4s;
		}
		.sidebar-link:hover {
			background-color: #444;
			border-radius: 5px;
		}
		.current{
			background-color: #9932CC;
			border-radius: 7px;
			box-shadow: 2px 5px 10px #D3D3D3;
		}
		.current:hover{
			background-color: #9370DB;
			border-radius: 7px;
			box-shadow: 2px 5px 20px #D3D3D3;
			transform: translateY(-1px);
		}
		#logoLabel {
            font-style: bold;
            font-size: 24px;
            padding-left: 5px;
        }
	</style>
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
	<ul class="navbar-nav flex-column mt-4">
      <li class="nav-item">
        <a href="/project/detail.do?projectId=1" class="nav-link p-3 mb-2 sidebar-link link-dark" id="menu1">
	          <i class="bi bi-receipt-cutoff mx-3"></i>프로젝트
        </a>
      </li>
      <li>
        <a href="/project/myTask.do" class="nav-link p-3 mb-2 sidebar-link link-dark" id="menu2">
          <i class="bi bi-list-task mx-3"></i></i>업무
        </a>
      </li>
      <li>
        <a href="/project/member.do" class="nav-link p-3 mb-2 sidebar-link link-dark" id="menu3">
          <i class="bi bi-people-fill mx-3"></i>멤버
        </a>
      </li>
      <li>
        <a href="#" class="nav-link p-3 mb-2 sidebar-link link-dark" id="menu4">
          <i class="bi bi-bar-chart-fill mx-3"></i>진행률
        </a>
      </li>
      <li>
        <a href="/issue/list.do?projectId=1" class="nav-link p-3 mb-2 sidebar-link link-dark" id="menu5">
          <i class="bi bi-newspaper mx-3"></i>이슈
        </a>
      </li>
      <li>
        <a href="#" class="nav-link p-3 mb-2 sidebar-link link-dark">
          <i class="bi bi-check-circle-fill mx-3"></i>한 일
        </a>
      </li>
      <!--
      <c:if test="${!empty myData.leader}">
        <li>
            <a href="/project/leader.do?projectId=${project.projectId}" class="nav-link p-3 mb-2 sidebar-link link-dark">
                <i class="bi bi-check-circle-fill mx-3"></i>리더
            </a>
        </li>
      </c:if>
      -->
    </ul>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script>
	//메뉴를 클릭할 때 호출되는 함수
	function handleMenuClick(event) {
	    const clickedMenuID = event.target.id; // 클릭된 메뉴의 ID
	    Cookies.set('currentMenuID', clickedMenuID); // 클릭된 메뉴의 ID를 쿠키에 저장
	
	    // 모든 메뉴 항목에서 current 클래스 제거
	    document.querySelectorAll('.nav-link').forEach(link => {
	        link.classList.remove('current');
	    });
	
	    // 클릭한 메뉴에 current 클래스 추가
	    event.target.classList.add('current');
	}
	
	// 페이지가 로드될 때 호출
	window.addEventListener('load', function () {
	    // 저장된 메뉴 ID를 쿠키에서 읽어옴
	    const savedMenuID = Cookies.get('currentMenuID');
		
	    // 저장된 메뉴 ID가 있을 경우 해당 메뉴에 current 클래스 추가
	    if (savedMenuID) {
	        const savedMenu = document.getElementById(savedMenuID);
	        if (savedMenu) {
	            savedMenu.classList.add('current');
	        }
	    }
	
	    // 메뉴 항목에 클릭 이벤트 리스너 추가
	    document.querySelectorAll('.nav-link').forEach(link => {
	        link.addEventListener('click', handleMenuClick);
	    });
	});
    </script>
</body>
</html>
