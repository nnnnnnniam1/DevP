document.querySelector('.logoutBtn button').addEventListener('click', function() {
    // 현재 페이지의 URL을 가져옴
    var currentURL = window.location.href;

    // "/project/leader.do"에서 "/logout.do"로 변경
    var logoutURL = currentURL.replace('/project/leader.do', '/logout.do');

    // 변경된 URL로 이동
    window.location.href = logoutURL;
 });