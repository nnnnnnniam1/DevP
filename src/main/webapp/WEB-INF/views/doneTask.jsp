<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/task.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="container">
	<h2 class="pTitle">${project.projectName}-한 일</h2>
	<div class="contentsBox">
		<p class="labelWrapper">한 일 목록</p>
		<div class="taskWrapper">
			<div class="taskBox">
				<div id="">
					<table class="table" width=500px;>
						<colgroup>
							<col style="width: 5%">
							<col style="width: 20%">
							<col style="width: 15%">
							<col style="width: 20%">
							<col style="width: 30%">
							<col style="width: 10%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">No</th>
								<th scope="col">일정</th>
								<th scope="col">업무 완료일</th>
								<th scope="col">depth</th>
								<th scope="col">디테일</th>
								<th scope="col">이슈</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${donetasklist}" var="task" varStatus="loop">
								<input type="hidden" name="taskVOList[${loop.index}].taskId"
									value="${task.taskId}">
								<input type="hidden" name="projectId" value="${projectId}">
								<tr>
									<td>${loop.count}</td>
									<td>${task.startdate} ~ ${task.enddate}</td>
									<td>${task.realEnd }</td>
									<td>${task.depth}</td>
									<td>${task.detail}</td>
									<td><input class="form-control" type="button" value="이슈"
										onclick="location.href='/issue/view.do?projectId=${project.projectId}&taskId=${task.taskId}'" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>