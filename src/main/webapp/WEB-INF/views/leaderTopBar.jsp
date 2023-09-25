<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <link rel="stylesheet" href="/resources/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/path/to/custom.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid tobBar">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#">프로젝트</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">업무</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">멤버</a>
                <li class="nav-item">
                    <a class="nav-link" href="#">지난일</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">이슈</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto"> <!-- ml-auto를 추가하여 오른쪽으로 정렬 -->
                <li class="nav-item logoutBtn">
                    <button class="btn btn-outline-success" type="button" onclick="location.href='/logout.do'">Logout</button>
                </li>
            </ul>
        </div>
    </nav>

</body>
</html>
