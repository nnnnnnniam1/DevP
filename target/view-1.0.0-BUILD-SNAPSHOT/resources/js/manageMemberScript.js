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

function updateMembers(){
    var memberDataList = [];
    var projectId = '<%= session.getAttribute("projectId") %>';
    console.log(projectId);

    $("table tr").each(function(){
        var member = {
            projectId: (this).find("[name='projectId']").val(),
            userId: $(this).find("[name='userId']").val(),
            role: $(this).find("[name='role']").val(),
            position: $(this).find("[name='position']").val(),
        };
        console.log(member.projectId)
        memberDataList.push(member);
    })

    $.ajax({
        type: 'POST',
        url: '/project/updateMember.do',
        data: {
            memberDataList: JSON.stringify(memberDataList)  //json형태
        }, success: function(response){
            alert("수정되었습니다.");
        }, error: function(error){
            alert("수정 실패!");
            console.log("에러: ", error);
        }
    });
}

function updateMember(){
    var memberDatas = document.querySelectorAll('.manageForm table tr');
    var updates = [];

    memberDatas.forEach(function(data){
        var userId = row.querySelector('input[name="')
    })
}

//var roleSelect = document.getElementById('roleSelect');
//if (!roleSelect.value) {
//    roleSelect.selectedIndex = 0; // 첫 번째 옵션을 선택
//}