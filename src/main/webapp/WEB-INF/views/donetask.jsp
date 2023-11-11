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
	<h2 class="pTitle">${project.projectName}- 한 일</h2>
	<c:forEach items="${donetasklist}" var="task" varStatus="loop">
		<input type="hidden" name="taskVOList[${loop.index}].taskId"
			value="${task.taskId}">
		<input type="hidden" name="projectId" value="${projectId}">
		<tr>
			<td>${loop.count}</td>
			<td>${task.startdate}</td>
			<td>${task.enddate}</td>
			<td>${task.depth}</td>
			<td>${task.detail}</td>
			<td><select class="form-select"
				name="taskVOList[${loop.index}].status" id="statusSelect">
					<c:forEach items="${statusMap}" var="status">
						<option value="${status.value}"
							<c:if test="${status.value eq task.status}"> selected</c:if>>${status.value}</option>
					</c:forEach>
			</select></td>
			<td><input class="form-control" type="button" value="이슈"
				onclick="location.href='/issue/view.do?projectId=${project.projectId}&taskId=${task.taskId}'" />
			</td>
		</tr>
	</c:forEach>
</div>

<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>