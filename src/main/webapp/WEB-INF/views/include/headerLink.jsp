<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <link rel="stylesheet" href="/resources/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/js-cookie/3.0.1/js.cookie.min.js"></script>
	<link rel="stylesheet" href="/resources/css/manageMemberStyles.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="/resources/css/base.css">
	<style type="text/css">
		.sidebar{
			box-shadow: 2px 2px 5px #D3D3D3;
		}
		.sidebar-link {
			transition: all .4s;
		}
		.sidebar-link:hover {
			background-color: #530be9;
			color:#fff !important;
			border-radius: 0px;
		}
		.sidebar-link.current {
			background-color: #530be9;
			color:#fff !important;
			border-radius: 0px;
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
        .logoutBtn {
            position: fixed;
            bottom: 0;
            left: 0; /* Optional: 위치를 왼쪽 끝에 고정하려면 추가 */
            //width: 100%; /* Optional: 너비를 가득 채우려면 추가 */
            //background-color: #333; /* 버튼 배경색 설정 */
            //color: #fff; /* 버튼 텍스트 색 설정 */
            padding: 10px 0; /* 버튼 패딩 설정 */
            text-align: center; /* 버튼 텍스트 가운데 정렬 설정 */
        }
	</style>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script>

	console.log("${menuId}");
	// 페이지가 로드될 때 호출
	window.addEventListener('load', function () {
	    // 저장된 메뉴 ID가 있을 경우 해당 메뉴에 current 클래스 추가
	    if ("${menuId}") {
	        const savedMenu = document.getElementById("${menuId}");
	        if (savedMenu) {
	            savedMenu.classList.add('current');
	        }
	    }
	});
	  var projectId = ${project.projectId}; // 서버에서 생성된 값으로 설정
	  var link = "/project/detail.do?projectId=" + projectId;
	  document.getElementById("projectMenu").href = link; // 링크 엘리먼트의 href 속성 설정
	  var link = "/issue/list.do?projectId=" + projectId;
	  document.getElementById("issueMenu").href = link; // 링크 엘리먼트의 href 속성 설정
    </script>
	