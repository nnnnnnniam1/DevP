<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/WEB-INF/views/include/headerBase.jsp"%>
<%@ include file="/WEB-INF/views/include/headerLink.jsp"%>
<link rel="stylesheet" href="/resources/css/base.css">
<link rel="stylesheet" href="/resources/css/projectDetail.css">
<%@ include file="/WEB-INF/views/include/headerTop.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="container">
	<h2 class="pTitle">${project.projectName}-프로젝트리뷰</h2>
	<%-- <form method="post" class="p-3" action="/project/review/report.do" id="issue-form">
        <c:forEach items="${memberData}" var="member" varStatus="loop">
            <div class="member-item">
                <input type="hidden" name="evaMemberId" value="${member.userId}" />
                <div class="memberProgress">${member.userName}</div>
                <textarea type="text" class="form-control" name="content" style="height: 15vh"></textarea>
            </div>
        </c:forEach>
        <div>
            <button type="submit" class="btn btn-primary" style="float: right">등록</button>
        </div>
    </form> --%>
	<form method="post" class="p-3" action="/project/review/report.do"
		id="issue-form">
		<div id="member-reviews">
			<c:forEach items="${memberData}" var="member" varStatus="loop">
				<c:if test="${ member.userId != user.id }">
					<div class="member-item">
						<input type="hidden" name="evaMemberId" value="${member.userId}" />
						<div class="form-floating">
							<textarea type="text" class="form-control" name="content"
								placeholder="Leave a comment here" id="floatingcontent"
								style="height: 50vh"></textarea>
							<label for="floatingcontent">${member.userName} 멤버에게 리뷰를
								남겨주세요!</label>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="d-flex justify-content-center">
			<div class="p-2">
				<button type="button" class="btn" id="prev-review"><</button>
				<button type="button" class="btn" id="next-review">></button>
			</div>
		</div>

		<button type="submit" class="btn btn-primary" style="float:right">등록</button>
	</form>
</div>
<script>
	$(document).ready(function() {
		let currentReviewIndex = 0;
		const reviews = $(".member-item");

		// 처음에는 첫 번째 리뷰만 보이도록 설정
		$(reviews).hide();
		$(reviews[currentReviewIndex]).show();

		const changeBtnColor = () => {
			if (currentReviewIndex === 0) {
	            $("#prev-review").addClass("btn-secondary disabled");
	            $("#next-review").removeClass("btn-secondary disabled");
	            $("#next-review").addClass("btn-primary");
	        } else if (currentReviewIndex === reviews.length - 1) {
	            $("#prev-review").removeClass("btn-secondary disabled");
	            $("#prev-review").addClass("btn-primary");
	            $("#next-review").addClass("btn-secondary disabled");
	        } else {
	            $("#prev-review").removeClass("btn-secondary");
	            $("#next-review").removeClass("btn-secondary");
	            $("#prev-review").addClass("btn-primary");
	            $("#next-review").addClass("btn-primary");
	        }
		};
		
		$("#next-review").on("click", function() {
			if (currentReviewIndex < reviews.length - 1) {
				$(reviews[currentReviewIndex]).hide();
				currentReviewIndex++;
				$(reviews[currentReviewIndex]).show();
				changeBtnColor();
			}
		});

		$("#prev-review").on("click", function() {
			if (currentReviewIndex > 0) {
				$(reviews[currentReviewIndex]).hide();
				currentReviewIndex--;
				$(reviews[currentReviewIndex]).show();
				changeBtnColor();
			}
		});
		changeBtnColor();
	});
</script>
</body>
<!-- 컨텐츠 종료 -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
