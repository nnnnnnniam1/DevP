<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"  href="/resources/css/base.css">
<link rel="stylesheet"  href="/resources/css/login.css">
<head>
</head>
<body>
<div class="container">
    <div class="login-wrapper">
            <form class="row justify-content-center loginForm" id="login-form">
                <h2>프로젝트 삭제</h2>
                <div class="mb-3">
                    <div>
                        <label class="form-label">프로젝트명</label>
                        <input type="text" readonly class="form-control-plaintext" id="projectName" value="${projectName}">
                    </div>
                    <div>
                        <label class="form-label">삭제사유</label>
                        <div class="col-sm-10">
                            <textarea name="reason" class="form-control" id="reason" placeholder="프로젝트 삭제 사유를 적어주세요."></textarea>
                        </div>
                    </div>
                    <div>
                        <button class="form-control btn main m-t-2 m-b-2" type="button" onclick="deleteProject('${projectId}','${projectName}')">삭제</button>
                    </div>
                </div>
            </form>
        </div>
</div>
</body>
<script type="text/javascript">
function deleteProject(projectId, projectName){
    reason = document.getElementById('reason').value;
    console.log(reason);
        console.log(projectId);
    if(confirm("["+projectName+"]프로젝트를 삭제하시겠습니까?")){
        $.ajax({
            type: 'POST',
            url: '/project/deleteProject.do',
            data: {
                projectId: projectId,
                reason: reason
            },
            success: function(response){
                if(response === "success"){
                    alert("삭제되었습니다");
                    window.location.href="/project/list.do";
                } else {
                    alert("오류가 발생했습니다. 다시 시도해주세요");
                    window.location.href="/project/leader.do?projectId=${projectId}"
                }

            },
            error: function(error){
                console.log("에러: "+error);
                alert("오류가 발생했습니다. 다시 시도해주세요");
                window.location.href="/project/leader.do?projectId=${projectId}"
            }
        });
    }
}
</script>
</html>