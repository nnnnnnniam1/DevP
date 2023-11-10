<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet" href="/resources/css/manageTask.css">
<link rel="stylesheet" href="/resources/css/task.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="mv-100 container">
    <div>
        <h2 class="pTitle">
            ${projectName} － 업무추가
            <button class="btn btn-outline-success" type="button" onclick="location.href='/project/list/view.do'">완료</button>
        </h2>
    </div>
    <div class="contentsBox">
        <p class="labelWrapper">업무추가</p>
        <div class="row">
            <div id="box">
                <div class="addForm">
                    <table id="taskTbl" class="table" width=500px;>
                        <thead>
                            <tr>
                                <th class="" scope="col">Category</th>
                                <th class="" scope="col">Work Package</th>
                                <th class="" scope="col">depth</th>
                                <th class="" scope="col">detail</th>
                                <th class="" scope="col">담당자</th>
                                <th class="" scope="col">시작일</th>
                                 <th class="" scope="col">종료일</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <select class="form-select" name="category" id="categorySelect">
                                        <option value="" disabled selected> 선택</option>
                                        <c:forEach items="${categoryMap}" var="category">
                                            <option value="${category.value}">${category.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select class="form-select" name="workPackage" id="workPackageSelect">
                                        <option value="" disabled selected> 선택</option>
                                    </select>
                                </td>
                                <td><input class="form-control" type="text" name="depth" id="depth"></td>
                                <td><input class="form-control" type="text" name="detail" id="detail"></td>
                                <td><select class="form-select" name="responsibility" id="responsibilitySelect">
                                    <option value="" disabled selected> 선택</option>
                                    <c:forEach items="${memberMap}" var="member">
                                        <option value="${member.key}">${member.value}</option>
                                    </c:forEach>
                                </select></td>
                                <td><input class="form-control startDate" type="date"  name="startdate" id="startDate"/></td>
                                <td><input class="form-control endDate" type="date"  name="enddate" id="endDate" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <button class="btn main" onclick="insertTask()">추가</button>
        </div>
    </div>
    <div class="contentsBox">
        <p class="labelWrapper m-b-1">간트차트</p>
        <div class="wbsBox col-auto">
            <div id="chart_div"></div>
        </div>
        <c:if test="${not empty taskList}">
            <div class="wbsWrapper">
                <div class="wbsBox col-auto">
                    <div id="chart_div"></div>
                </div>
            </div>
        </c:if>
        <c:if test="${empty taskList}">
            <div style="height:10em;"></div>
        </c:if>
    </div>
    <div class="contentsBox">
        <p class="labelWrapper">업무수정</p>
        <div class="row">
            <form class="manageTask" modelAttribute="TaskVO" name="dataForm" method="post" action="/project/task/modify.do">
                <div>
                    <div class="manageForm">
                        <table class="table taskTbl">
                            <thead>
                                <tr>
                                    <th class="" scope="col">Category</th>
                                    <th class="" scope="col">Work Package</th>
                                    <th class="" scope="col">depth</th>
                                    <th class="" scope="col">detail</th>
                                    <th class="" scope="col">담당자</th>
                                    <th class="" scope="col">시작일</th>
                                    <th class="" scope="col">종료일</th>
                                    <th class="" scope="col">상태</th>
                                    <th class="" scope="col">진행률</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${taskList}" var="task" varStatus="loop">
                                    <input type="hidden" name="taskVOList[${loop.index}].taskId" value="${task.taskId}">
                                    <input type="hidden" name="taskVOList[${loop.index}].projectId" value="${task.projectId}">
                                    <tr>
                                        <td>
                                            <select class="form-select" name="taskVOList[${loop.index}].category" id="categorySelect${loop.index}">
                                                <c:forEach items="${categoryMap}" var="category">
                                                    <option value="${category.value}"
                                                        <c:if test="${category.value eq task.category}"> selected</c:if>
                                                    >${category.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="hidden" id="workPackageSelectValue" value="${task.workPackage}">
                                            <select class="form-select" name="taskVOList[${loop.index}].workPackage" id="workPackageSelect${loop.index}">
                                                <c:choose>
                                                    <c:when test="${task.category eq '기획'}">
                                                        <c:forEach items="${workPackage1}" var="workPackage">
                                                            <option value="${workPackage.value}" <c:if test="${workPackage.value eq task.workPackage}">selected</c:if>>${workPackage.value}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${task.category eq '분석'}">
                                                        <c:forEach items="${workPackage2}" var="workPackage">
                                                            <option value="${workPackage.value}" <c:if test="${workPackage.value eq task.workPackage}">selected</c:if>>${workPackage.value}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${task.category eq '디자인'}">
                                                        <c:forEach items="${workPackage3}" var="workPackage">
                                                            <option value="${workPackage.value}" <c:if test="${workPackage.value eq task.workPackage}">selected</c:if>>${workPackage.value}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${task.category eq '개발'}">
                                                        <c:forEach items="${workPackage4}" var="workPackage">
                                                            <option value="${workPackage.value}" <c:if test="${workPackage.value eq task.workPackage}">selected</c:if>>${workPackage.value}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${task.category eq '서버'}">
                                                        <c:forEach items="${workPackage5}" var="workPackage">
                                                            <option value="${workPackage.value}" <c:if test="${workPackage.value eq task.workPackage}">selected</c:if>>${workPackage.value}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${task.category eq '테스트'}">
                                                        <c:forEach items="${workPackage6}" var="workPackage">
                                                            <option value="${workPackage.value}" <c:if test="${workPackage.value eq task.workPackage}">selected</c:if>>${workPackage.value}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${task.category eq '완료'}">
                                                        <c:forEach items="${workPackage7}" var="workPackage">
                                                            <option value="${workPackage.value}" <c:if test="${workPackage.value eq task.workPackage}">selected</c:if>>${workPackage.value}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                        <td><input class="form-control" type="text" name="taskVOList[${loop.index}].depth" value="${task.depth}"></td>
                                        <td><input class="form-control" type="text" name="taskVOList[${loop.index}].detail" value="${task.detail}"></td>
                                        <td><select class="form-select" name="taskVOList[${loop.index}].userId">
                                            <c:forEach items="${memberMap}" var="member">
                                                <option value="${member.key}"
                                                    <c:if test="${member.key eq task.userId}"> selected</c:if>
                                                >${member.value}</option>
                                            </c:forEach>
                                        </select></td>
                                        <td><input class="form-control startDate" type="date"  name="taskVOList[${loop.index}].startdate" value="${task.startdate}"/></td>
                                        <td><input class="form-control endDate" type="date"  name="taskVOList[${loop.index}].enddate" value="${task.enddate}"/></td>
                                        <td>
                                            <select class="form-select" name="taskVOList[${loop.index}].status" id="statusSelect">
                                                <c:forEach items="${statusMap}" var="status">
                                                    <option value="${status.value}"
                                                        <c:if test="${status.value eq task.status}"> selected</c:if>
                                                    >${status.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td><input class="form-control" type="text" name="taskVOList[${loop.index}].progress" value="${task.progress}"></td>
                                        <td><input class="form-control" type="button" value="삭제" onclick="deleteTask('${task.taskId}')" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input class="form-control" type="submit" value="수정">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/resources/js/insertTask.js"></script>
<script type="text/javascript">

function getCurrentDate(){
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth()+1).padStart(2,'0');
        const day = String(now.getDate()).padStart(2,'0');

        return year+"-"+month+"-"+day;
}
$(document).ready(function(){
    const currentDate = getCurrentDate();
    document.getElementById('startDate').setAttribute('min', currentDate);
    document.getElementById('startDate').setAttribute('value', currentDate);
    document.getElementById('endDate').setAttribute('min', currentDate);
    document.getElementById('endDate').setAttribute('value', currentDate);
});
</script>
<script type="text/javascript">
var dataArray = [];

function dateDifference(a, b){
    return (b-a+1)/(1000 * 60 * 60 * 24);
}

<c:forEach items="${taskList}" var="task" varStatus="status">
    var percentage = 0;
    var startDate = new Date("${task.startdate}");
    var endDate = new Date("${task.enddate}");
    var today = new Date();
    var daysDifference = dateDifference(startDate,endDate);
    var days = dateDifference(startDate,today);
    if(days>=0){
        var dayPassed = days/daysDifference;
        percentage = Math.round(dayPassed * 100);
    } else {
        percentage = 0;
    }
    if (percentage > 100) percentage = 100;
    var datas = ["${task.taskId}","${task.detail}","${task.workPackage}",
                 startDate,endDate,null,percentage, null];

    dataArray.push(datas);
</c:forEach>


if(dataArray[0] !== null && dataArray.length>0){
    google.charts.load('current', {'packages':['gantt']});
    google.charts.setOnLoadCallback(drawChart);
}

function drawChart(){
    console.log(dataArray);

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Task ID');
    data.addColumn('string', 'Task Name');
    data.addColumn('string', 'workPackage');
    data.addColumn('date', 'Start Date');
    data.addColumn('date', 'End Date');
    data.addColumn('number', 'Duration');
    data.addColumn('number', 'Percent Complete');
    data.addColumn('string', 'Dependencies');

    data.addRows(dataArray);

    var options = {
        height: 400,
        gantt: {
            trackHeight: 50
        }
    };

    var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

    chart.draw(data, options);

}


function insertTask(){
    var category = document.getElementById("categorySelect").value;
    var workPackage = document.getElementById("workPackageSelect").value;
    var depth = document.getElementById("depth").value;
    var detail = document.getElementById("detail").value;
    var responsibility = document.getElementById("responsibilitySelect").value;
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;

    if(category === null || workPackage === null || detail === null || responsibility === null || startDate === null || endDate === null){
        alert("빈칸없이 채워주세요");
    } else {
        $.ajax({
            url: "/project/task/add.do",
            method: 'POST',
            data: {
                category: category,
                workPackage: workPackage,
                depth: depth,
                detail: depth,
                detail: detail,
                userId: responsibility,
                startdate: startDate,
                enddate: endDate
            },
            success: function(response){
                console.log("업무 추가!")
                window.location.href="/project/task/add/view.do";
            },
            error: function(error){
                console.log('에러: '+error);
                alert("업무를 생성하는데 오류가 발생하였습니다.");
                window.location.href="/project/task/add/view.do";
            }



        })
    }
}


</script>
<script>
var workPackage = {
    "기획": ["기획분석", "일정계획"],
    "분석": ["IA 정의", "사용자 화면 기획"],
    "디자인": ["디자인"],
    "개발": ["프로토타입", "퍼블리싱", "개발세팅", "프런트엔드", "백엔드"],
    "서버": ["서버"],
    "테스트": ["테스트"],
    "완료": ["모니터링 및 디버깅", "산출물 취합", "운영이관", "프로젝트 완료"]
}
// 카테고리 선택 시 작업 패키지 업데이트
function updateWorkPackages() {
    var categorySelect = document.getElementById("categorySelect");
    var workPackageSelect = document.getElementById("workPackageSelect");
    var selectedCategory = categorySelect.value;

    // 작업 패키지 셀렉트 박스 초기화
    workPackageSelect.innerHTML = '<option value="" disabled selected> 선택</option>';

    // 선택한 카테고리에 해당하는 작업 패키지 추가
    if (selectedCategory in workPackage) {
        workPackage[selectedCategory].forEach(function (workPackage) {
            var option = document.createElement("option");
            option.value = workPackage;
            option.text = workPackage;
            workPackageSelect.add(option);
        });
    }
}
document.getElementById("categorySelect").addEventListener("change", updateWorkPackages);
updateWorkPackages();

<c:forEach items="${taskList}" var="task" varStatus="loop">

function modifyWorkPackages${loop.index}() {
    var categorySelect = document.getElementById("categorySelect${loop.index}");
    var workPackageSelect = document.getElementById("workPackageSelect${loop.index}");
    var selectedCategory = categorySelect.value;

    console.log(selectedCategory);
    // 작업 패키지 셀렉트 박스 초기화
    workPackageSelect.innerHTML = '<option value="" disabled selected> 선택</option>';

    // 선택한 카테고리에 해당하는 작업 패키지 추가
    if (selectedCategory in workPackage) {
        workPackage[selectedCategory].forEach(function (workPackage) {
            var option = document.createElement("option");
            option.value = workPackage;
            option.text = workPackage;

            workPackageSelect.add(option);

        });
    }
}
document.getElementById("categorySelect${loop.index}").addEventListener("change", modifyWorkPackages${loop.index});
</c:forEach>


</script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>