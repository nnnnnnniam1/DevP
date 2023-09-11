<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023/07/25
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>부트스트랩 사이드바 예시</title>
    <link rel="stylesheet" href="/resources/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<div id = "sidebar" class="d-flex flex-column flex-shrink-0 p-3 bg-light position-fixed" style="width: 20%; height: 100vh">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
      <svg class="bi me-2" width="40" height="36"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4">로고</span>
    </a>
    <hr>	
    <ul class="nav nav-pills flex-column mb-auto text-center ">
      <li class="nav-item">
        <a href="/project/detail.do?projectId=1" class="nav-link link-dark active">
                     프로젝트
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-dark">
          	업무
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-dark">
          	멤버
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-dark">
          	진행률
        </a>
      </li>
      <li>
        <a href="/issue/list.do?projectId=1" class="nav-link link-dark">
          	이슈
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-dark">
          	한 일
        </a>
      </li>
    </ul>
  </div>
  
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
