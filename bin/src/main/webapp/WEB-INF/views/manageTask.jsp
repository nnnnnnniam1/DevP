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
    <h2 class="pTitle">${projectName} - 업무관리</h2>
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
                                <td><input class="form-control" type="text" name="workPackage" id="workPackage"></td>
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
                                <td><button class="btn main" onclick="insertTask()">추가</button></td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
    <div class="contentsBox">
        <p class="labelWrapper m-b-1">간트차트</p>
        <div class="wbsBox col-auto">
            <div id="chart_div"></div>
        </div>
        <c:if test="${empty taskList}">
            <div style="height:10em;"></div>
        </c:if>
    </div>
    <div class="contentsBox">
        <p class="labelWrapper">업무수정</p>
        <div class="row">
            <form class="manageTask" modelAttribute="TaskVO" name="dataForm" method="post" action="/leader/task/modify.do">
                <div>
                    <div class="manageForm">
                        <table class="table taskTbl">
                            <thead>
                                <tr>
                                    <th class="col-auto" scope="col">Category</th>
                                    <th class="col-auto" scope="col">Work Package</th>
                                    <th class="col-auto" scope="col">depth</th>
                                    <th class="col-auto" scope="col">detail</th>
                                    <th class="col-auto" scope="col">담당자</th>
                                    <th class="col-auto" scope="col">시작일</th>
                                    <th class="col-auto" scope="col">종료일</th>
                                    <th class="col-auto" scope="col">상태</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${taskList}" var="task" varStatus="loop">
                                    <input type="hidden" name="taskVOList[${loop.index}].taskId" value="${task.taskId}">
                                    <input type="hidden" name="taskVOList[${loop.index}].projectId" value="${task.projectId}">
                                    <tr>
                                        <td>
                                            <select class="form-select" name="taskVOList[${loop.index}].category">
                                                <c:forEach items="${categoryMap}" var="category">
                                                    <option value="${category.value}"
                                                        <c:if test="${category.value eq task.category}"> selected</c:if>
                                                    >${category.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td><input class="form-control" type="text" name="taskVOList[${loop.index}].workPackage" value="${task.workPackage}"></td>
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
                                        <td><input class="btn main" type="button" value="삭제" onclick="deleteTask('${task.taskId}')" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input class="btn main form-control" type="submit" value="수정">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/resources/js/manageTask.js"></script>
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

    var rowHeight = 50;
    var chartHeight = (dataArray.length * rowHeight) + 100;

    var options = {
        height: chartHeight,
        gantt: {
            trackHeight: rowHeight
        }
    };

    var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

    chart.draw(data, options);

}
function insertTask(){
    var category = document.getElementById("categorySelect").value;
    var workPackage = document.getElementById("workPackage").value;
    var depth = document.getElementById("depth").value;
    var detail = document.getElementById("detail").value;
    var responsibility = document.getElementById("responsibilitySelect").value;
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;

    var projectId = ${projectId}

    if(category === null || workPackage === null || detail === null || responsibility === null || startDate === null || endDate === null){
        alert("빈칸없이 채워주세요");
    } else {
        $.ajax({
            url: "/leader/task/add.do",
            method: 'POST',
            data: {
                projectId: projectId,
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
                window.location.href="/leader/task/view.do";
            },
            error: function(error){
                console.log('에러: '+error);
                alert("업무를 생성하는데 오류가 발생하였습니다.");
                window.location.href="/leader/task/view.do";
            }



        })
    }
}


</script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>