<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <c:choose>
        <c:when test="${projectId!=null}">
            <ul class="navbar-nav flex-column mt-4">
                <li class="nav-item">
                    <a href="/project/detail.do?projectId=${ projectId }" + class="nav-link p-3 mb-2 sidebar-link link-dark" id="projectMenu">
                        <i class="bi bi-receipt-cutoff mx-3"></i>프로젝트
                    </a>
                </li>
                <li>
                    <a href="/project/myTask.do" class="nav-link p-3 mb-2 sidebar-link link-dark" id="taskMenu">
                        <i class="bi bi-list-task mx-3"></i></i>업무
                    </a>
                </li>
                <li>
                    <a href="/project/member.do" class="nav-link p-3 mb-2 sidebar-link link-dark" id="memberMenu">
                        <i class="bi bi-people-fill mx-3"></i>멤버
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link p-3 mb-2 sidebar-link link-dark" id="menu4">
                        <i class="bi bi-bar-chart-fill mx-3"></i>진행률
                    </a>
                </li>
                <li>
                    <a href="/issue/list.do?projectId=${ projectId }" class="nav-link p-3 mb-2 sidebar-link link-dark" id="issueMenu">
                        <i class="bi bi-newspaper mx-3"></i>이슈
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link p-3 mb-2 sidebar-link link-dark">
                        <i class="bi bi-check-circle-fill mx-3"></i>한 일
                    </a>
                </li>
                  <c:if test="${myData.leader==myData.userId}">
                    <li>
                        <a href="/project/leader.do?projectId=${project.projectId}" class="nav-link p-3 mb-2 sidebar-link link-dark">
                            <i class="bi bi-key mx-3"></i>리더
                        </a>
                    </li>
                  </c:if>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="navbar-nav flex-column mt-4">
                <li class="nav-item">
                    <a href="/project/list.do" class="nav-link p-3 mb-2 sidebar-link link-dark">
                        <i class="bi bi-receipt-cutoff mx-3"></i>프로젝트
                    </a>
                </li>
                <li>
                    <a href="/project/list.do" class="nav-link p-3 mb-2 sidebar-link link-dark">
                        <i class="bi bi-list-task mx-3"></i></i>업무
                    </a>
                </li>
                <li>
                    <a href="/project/list.do" class="nav-link p-3 mb-2 sidebar-link link-dark">
                        <i class="bi bi-people-fill mx-3"></i>멤버
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link p-3 mb-2 sidebar-link link-dark">
                        <i class="bi bi-bar-chart-fill mx-3"></i>진행률
                    </a>
                </li>
                <li>
                    <a href="/project/list.do" class="nav-link p-3 mb-2 sidebar-link link-dark">
                        <i class="bi bi-newspaper mx-3"></i>이슈
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link p-3 mb-2 sidebar-link link-dark">
                        <i class="bi bi-check-circle-fill mx-3"></i>한 일
                    </a>
                </li>
            </ul>
        </c:otherwise>
    </c:choose>

    
