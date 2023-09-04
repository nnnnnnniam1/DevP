<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/loginStyles.css">
<%@include file="sidebar.jsp"%>
<head>
	<style>
        table {
            border-collapse: collapse;
        }

        th, td {
            width: 40px;
            height: 40px;
            text-align: center;
            vertical-align: middle;
            border: 1px solid #ccc;
        }

        th {
            background-color: #eee;
        }

        td {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
	
	<h1>${year}년 ${month}월</h1>
	
	<!-- 이전 달로 이동하는 버튼 -->
	<a href="/view/project/detail.do?year=${year}&month=${month - 1}">이전 달</a>
	<!-- 다음 달로 이동하는 버튼 -->
	<a href="/view/project/detail.do?year=${year}&month=${month + 1}">다음 달</a>
	
	<table>
    <table>
        <tr>
            <th>일</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
            <th>토</th>
        </tr>
        <c:forEach var="week" items="${calendar}">
            <tr>
                <c:forEach var="day" items="${week}">
                    <td>${day}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
	</table>
	
</div>
</body>
</html>