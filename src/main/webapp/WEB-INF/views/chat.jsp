<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="sidebar.jsp"%>
<head>
    <title>1대1 채팅</title>
</head>
<body>
    <h1>1대1 채팅</h1>
    <div id="chat-messages"></div>
    <input type="text" id="message-input" placeholder="메시지 입력">
    <button onclick="sendMessage()">전송</button>

    <script>
        // WebSocket 연결 생성
        const socket = new WebSocket("ws://localhost:8080/chat"); // WebSocket 서버 URL

        socket.onopen = function(event) {
            console.log("WebSocket 연결이 열렸습니다.");
        };

        socket.onmessage = function(event) {
            const message = event.data;
            console.log("메시지 수신: " + message);
            // 메시지를 화면에 표시하는 로직
            const chatMessages = document.getElementById("chat-messages");
            chatMessages.innerHTML += "<p>" + message + "</p>";
        };

        socket.onclose = function(event) {
            if (event.wasClean) {
                console.log("WebSocket 연결이 정상적으로 종료되었습니다.");
            } else {
                console.error("WebSocket 연결이 비정상적으로 종료되었습니다.");
            }
        };

        function sendMessage() {
            const messageInput = document.getElementById("message-input");
            const message = messageInput.value;
            socket.send(message); // 메시지 전송
            messageInput.value = ""; // 입력 필드 초기화
        }
    </script>
</body>
</html>
