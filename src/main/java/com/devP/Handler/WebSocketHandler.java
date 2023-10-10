package com.devP.Handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> userSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트와 연결이 성립되었을 때 호출됨
        // 연결된 세션을 사용자 식별자와 함께 저장
        String userId = extractUserIdFromSession(session); // 사용자 ID 추출
        System.out.printf("userId : " + userId);
        userSessions.put(userId, session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 메시지를 수신했을 때 호출됨
        // 메시지를 처리하고 필요한 작업을 수행함
        String content = message.getPayload();
        String userId = extractUserIdFromSession(session);

        // 특정 사용자에게 메시지 보내기
        WebSocketSession targetSession = userSessions.get(userId);
        if (targetSession != null) {
            targetSession.sendMessage(new TextMessage("From " + userId + ": " + content));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 클라이언트와 연결이 종료되었을 때 호출됨
        // 연결 종료 후 정리 작업을 수행하고 세션에서 사용자 제거
        String userId = extractUserIdFromSession(session);
        userSessions.remove(userId);
    }

    private String extractUserIdFromSession(WebSocketSession session) {
        // 세션에서 사용자 식별자를 추출하는 로직
        // 예를 들어, HTTP 세션에서 사용자 ID를 추출하거나,
        // WebSocket 연결 URL에서 사용자 ID를 추출할 수 있음
        // 이 예제에서는 간략하게 사용자 ID를 추출한다고 가정
        // 실제 환경에서는 더 안전한 방법을 사용해야 함
        return "user123";
    }
}