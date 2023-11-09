<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/task.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="mv-100 container">

<h2 class="pTitle">${project.projectName} - 업무</h2>
    <div class="contentsBox">
        <p class="labelWrapper">진행률</p>
        <div class="projectProgress">
            <span class="progressLabel">전체진행률</span>
            <div class="progress">
                <div style="width: ${project.progress}%;" class="progress-bar type01 text-dark">${project.progress}%</div>
            </div>
        </div>
        <div class="projectProgress">
            <span class="progressLabel">${user.name}</span>
            <div class="progress">
                <div style="width: ${member.progress}%;" class="progress-bar type02 text-dark">${member.progress}%</div>
            </div>
        </div>
    </div>
    <div class="contentsBox">
        <p class="labelWrapper m-b-1">WBS</p>
        <div class="wbsWrapper">
            <div class="wbsBox">
                <div id="chart_div"></div>
            </div>
        </div>
        <c:if test="${empty taskList}">
            <div style="height:10em;"></div>
        </c:if>
    </div>
    <div class="contentsBox">
        <p class="labelWrapper">일정</p>
        <div class="taskWrapper">
            <div class="taskBox">
                <div id="">
                    <form modelAttribute="TaskVO" name="dataForm" method="post" action="/task/modify.do">
                        <table class="table" width=500px;>
                        	<colgroup>
                                <col style="width: 5%">
                                <col style="width: 10%">
                                <col style="width: 10%">
                                <col style="width: 25%">
                                <col style="width: 30%">
                                <col style="width: 10%">
                                <col style="width: 10%">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">No</th>
                                    <th scope="col">시작일</th>
                                    <th scope="col">종료일</th>
                                    <th scope="col">depth</th>
                                    <th scope="col">디테일</th>
                                    <th scope="col">진행률</th>
                                    <th scope="col">이슈</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${taskList}" var="task" varStatus="loop">
                                    <input type="hidden" name="taskVOList[${loop.index}].taskId" value="${task.taskId}">
                                    <input type="hidden" name="projectId" value="${projectId}">
                                    <tr>
                                        <td>${loop.count}</td>
                                        <td >${task.startdate}</td>
                                        <td>${task.enddate}</td>
                                        <td>${task.depth}</td>
                                        <td>${task.detail}</td>
                                        <td>
                                            <select class="form-select" name="taskVOList[${loop.index}].status" id="statusSelect">
                                                <c:forEach items="${statusMap}" var="status">
                                                    <option value="${status.value}"
                                                        <c:if test="${status.value eq task.status}"> selected</c:if>
                                                    >${status.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input class="form-control" type="button" value="이슈" onclick="location.href='/issue/view.do?projectId=${project.projectId}'" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input class="btn main form-control" type="submit" value="수정">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
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
        var dayPassed = dateDifference(startDate,today);
        if(dayPassed>=0){
            var dayPassed = (today-startDate+1)/(endDate-startDate+1);
            percentage = Math.round(dayPassed * 100);
        } else {
            percentage = 0;
        }
        if (percentage > 100) percentage = 100;
        var datas = ["${task.taskId}","${task.detail}","${task.workPackage}",
                     startDate,endDate,null,percentage, null];
    
        dataArray.push(datas);
    </c:forEach>
    
    google.charts.load('current', {'packages':['gantt']});
    google.charts.setOnLoadCallback(drawChart);
    
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
    </script>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>