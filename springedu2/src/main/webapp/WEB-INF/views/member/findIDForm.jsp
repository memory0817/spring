<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/main_header.jsp" flush="false" />


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.errmsg, .pwErr {
	color: red;
}
</style>


<div class="container">
	<div class="row justify-content-center">
		<div class="col-4">
		<form:form modelAttribute="mdto" action="${pageContext.request.contextPath }/member/findid">
				<div class="text-center mb-4">
					<img class="mb-4" src="/resources/images/findid.PNG" alt=""
						width="300" height="300">
					<h1 class="h3 mb-3 font-weight-normal">아이디찾기</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>

				<div class="form-label-group">
						<form:label	path="tel">전화번호</form:label>
					<form:input type="text" path="tel" class="form-control"
						placeholder="전화번호를 입력하세요" required="true" autofocus="true" /> 
						<form:errors path="tel" cssClass="errmsg"></form:errors>
				</div>
				<div class="mb-3"></div>
				<div class="form-label-group">
						<form:label	path="birth">생년월일</form:label>
					<form:input type="date" path="birth" class="form-control"
						placeholder="생년월일을 입력하세요" required="true" /> 
						<form:errors path="birth" cssClass="errmsg"></form:errors>
				</div>
				<div class="mb-4"></div>
				<button class="btn btn-lg btn-dark btn-block" type="submit">아이디찾기</button>
				<p class="mt-5 mb-3 text-muted text-center">&nbsp;</p>
			</form:form>
			<div class="mb-3"></div>
			
		</div>
	</div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />
