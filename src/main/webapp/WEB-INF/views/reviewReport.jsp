<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet" href="/resources/css/base.css">
<link rel="stylesheet"  href="/resources/css/leaderDetail.css">
<link rel="stylesheet" href="/resources/css/member.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!-- 컨텐츠 시작 -->
<div class="mv-100 container">
    <h2 class="pTitle">${projectName}</h2>
        <div class="contentsBox">
            <div class="projectProgress">
                <span class="progressLabel">프로젝트</span>
                <div class="">${projectData.projectName}</div>
                <span class="progressLabel">script</span>
                <div class="">${projectData.script}</div>
                <div class="projectProgress">
                    <span class="progressLabel">기간</span>
                    <div>계획기간 :${projectData.startDate} ~ ${projectData.endDate}</div>
                    <div>실제기간 :${projectData.startDate} ~ ${projectData.realEnd}</div>
                </div>
                <div class="projectProgress">
                    <div class="member-wrapper">
                        <p class="progressLabel">멤버리뷰</p>

                        <div class="member-item"><br>
                            <textarea  rows="5" cols="120" >

                            </textarea> <br>
                        </div>

                        <c:forEach items="${memberData}" var="member">
                            <div class="member-item">
                                <p class="item-label member-name">${member.userName}</p>
                                <c:choose>
                                    <c:when test="${empty member.leader}">
                                        <div class="tag type01" id="memberTag">Member</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="tag type01" id="leaderTag">Leader</div>
                                        <!-- 빈 칼럼 -->
                                    </c:otherwise>
                                </c:choose>
                                <div class="item-label tag type02" id="roleTag">${member.role}</div>
                                <c:choose>
                                    <c:when test="${member.position eq '팀장'}">
                                        <div class="item-label tag type03" id="positionTag">팀장</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="item-label"></div>
                                        <!-- 빈 칼럼 -->
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="projectProgress">
                    <span class="progressLabel">업무 진행 내역</span>
                        <div class="wbsBox">
                            <div id="chart_div"></div>
                        </div>
                </div>
                <div class="projectProgress">
                    <span class="progressLabel">업무 기여도</span><br>
                    <c:forEach items="${reportData}" var="report" varStatus="loop">
                        <h2 class="pTitle">${report.userName}</h2>
                        <div class="contentsBox">
                            <div>${report.userName}님, '${project.projectName}' 프로젝트에서 ${report.taskPercentage}업무 기여도를 보입니다.</div>
                            <div> 그 중 '늦춰진 업무 ${report.lateTaskCount}건(${report.lateTaskPercentage})', '이슈 등록된 업무 4건(80%)','리더가 일정을 조정한 업무 ${report.modifiedTaskCount}건(${report.modifiedTaskPercentage})' 입니다.</div>
                            <div> 일정이 늦춰지는 것을 알리지 않은 업무 1건(스와이퍼 구조 해결)이 있습니다.</div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var dataArray = [];

    function dateDifference(a, b){
        return (b-a+1)/(1000 * 60 * 60 * 24);
    }
    <c:forEach items="${taskList}" var="task" varStatus="status">
        var percentage = 0;
        var startDate = new Date("${task.startdate}");
        var endDate = new Date("${task.realEnd}");
        percentage = ${task.progress}
        var datas = ["${task.taskId}","${task.detail}","${task.userName}",
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
