function deleteMember(userId, projectName, projectId){
    if(confirm("\""+userId+"\"멤버를 ["+projectName+"]프로젝트에서 삭제하시겠습니까?")){
        $.ajax({
            type: 'POST',
            url: '/project/deleteMember.do',
            data: {
                userId: userId,
                projectId: projectId
            },
            success: function(response){
                alert("삭제되었습니다");
                window.location.href="/project/manageMember.do";
            },
            error: function(error){
                console.log("에러: "+error);
            }
        });
    }
}