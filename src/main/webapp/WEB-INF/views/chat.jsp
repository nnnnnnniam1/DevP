<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <style>
        .my-message {
            color: blue; /* 내 메시지 스타일 */
        }
        
        .other-message {
            color: green; /* 상대방 메시지 스타일 */
        }
        
        .chat-container {
            max-height: 300px;
            overflow-y: auto;
        }
    </style>
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="container">
    <div class="issue-wrapper">
        <div class="mt-5">
            <h1 class="mb-4">${project.projectName}</h1>
            <p class="h3">${receiver.name}</p>
            <div class="d-flex"></div>
            <div class="card text-bg-light mb-3">
                <div id="chatcontainer" class="card-body chat-container"
                    style="min-height: 30vh">
                    <!-- 채팅 메시지 목록을 나타내는 부분 -->
                    <c:forEach items="${messageHistoryList}" var="message">
                        <div
                            class="item d-flex ${message.sender eq user.id ? 'justify-content-end' : 'justify-content-start'}">
                            <div class="py-1 d-inline-flex ">
                                <div class="card text-bg-light">
                                    <div class="card-body">
                                        <small class="text-body-secondary">${message.sender}
                                            : ${message.content}</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <%-- <c:forEach items="${messageHistoryList}" var="message">
                            <li
                                class="list-group-item message-item ${message.sender eq id ? 'my-message' : 'other-message'}">
                                <div class="card">
                                    <strong>${message.sender}:</strong> ${message.content}
                                </div>
                            </li>
                        </c:forEach> --%>

                </div>
            </div>
            <div class="card text-bg-light p-2">
                <div class="form-floating">
                    <textarea class="form-control" placeholder="Leave a comment here"
                        id="msg" style="height: 10vh"></textarea>
                    <label for="floatingcontent">메세지</label>
                </div>
                <div>
                    <button type="submit" id="chatbutton" class="btn btn-primary m-3"
                        style="float: right">보내기</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script th:inline="javascript">
    var $chatListContainer = $(".chat-container");
    var socket = null;
    var isStomp = false;
    const urlParams = new URL(location.href).searchParams;
    const chatRoomId = "${ chatRoomId }"
    const receiveId = urlParams.get('userId');
    const senderId = "${ user.id }"
    $(document).ready(function() {
        connectStomp()
        $('#chatbutton').on('click', function(evt) {
            evt.preventDefault();
            if (!isStomp && socket.readyState !== 1)
                return;

            let msg = $('textarea#msg').val();

            addChatMessage(msg, senderId);
            if (isStomp)
                socket.send('/sendChatMessage', {}, JSON.stringify({
                    chatId : chatRoomId,
                    sender : senderId,
                    receiver : receiveId,
                    content : msg
                }));
            $('textarea#msg').val('');
        });
    });

    function connectStomp() {
        sock = new SockJS("/stomp"); // endpoint
        client = Stomp.over(sock);
        isStomp = true;
        socket = client;
        client.connect({}, function() {
            console.log("stomp 연결 성공");
            // 컨트롤러 메세지 매핑, header, message(자유 형식)

            //토픽 구독
            client.subscribe('/topic/' + senderId, function(event) {
                var chatmessageData = JSON.parse(event.body);
                addChatMessage(chatmessageData.content, chatmessageData.sender);
            });
        });
    }
    function addChatMessage(message, id) {
        var senderId = "${ user.id }";
        if (senderId === id) {
            console.log(senderId, id + "아이디 같아유");
            temp_html = `<div class="item d-flex justify-content-end">
                <div class="py-1 d-inline-flex ">
                <div class="card text-bg-light">
                    <div class="card-body">
                        <small class="text-body-secondary"> ` + id + ` : ` + message + `</small>
                    </div>
                </div>
            </div>`;
        } else {
            console.log(senderId+ "senderId", id + "아이디 달라유");
            temp_html=`<div class="item d-flex justify-content-start">
                <div class="py-1 d-inline-flex ">
                <div class="card text-bg-light">
                    <div class="card-body">
                        <small class="text-body-secondary"> ` + id + ` : ` + message + `</small>
                    </div>
                </div>
            </div>`;
        }
            $('#chatcontainer').append(temp_html);
        console.log(message)
        /* 
        messageItem.className = id === "${ id }" ? 'my-message'
                : 'other-message';
        messageItem.innerHTML = '<strong>' + id + ':</strong> ' + message;
        messageList.appendChild(messageItem);  */
        $chatListContainer.scrollTop($chatListContainer[0].scrollHeight);
    }

    // 스크롤을 가장 아래로 이동
    $chatListContainer.scrollTop($chatListContainer[0].scrollHeight);
</script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>