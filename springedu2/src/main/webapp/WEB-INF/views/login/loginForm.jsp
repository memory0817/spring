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
		<form:form modelAttribute="login" action="/login">
				<div class="text-center mb-4">
					<img class="mb-4" src="/resources/images/login.PNG" alt=""
						width="300" height="300">
					<h1 class="h3 mb-3 font-weight-normal">Login</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>

				<div class="form-label-group">
						<form:label	path="id">아이디</form:label>
					<form:input type="text" path="id" class="form-control"
						placeholder="아이디를 입력하세요" required="true" autofocus="true" /> 
						<form:errors path="id" cssClass="errmsg"></form:errors>
				</div>

				<div class="form-label-group">
						<form:label	path="pw">비밀번호</form:label>
					<form:input type="password" path="pw" class="form-control"
						placeholder="비밀번호를 입력하세요" required="true" /> 
						<form:errors path="pw" cssClass="errmsg"></form:errors>
				</div>
				<div class="mb-3"></div>
				<div class="checkbox mb-3">
				<label>
					<input type="checkbox" name="remember-me" /> 자동로그인
				</label>				
				</div>
				<div class="mb-3"></div>
				<button class="btn btn-lg btn-dark btn-block" type="submit">로그인</button>
				<p class="mt-5 mb-3 text-muted text-center">&nbsp;</p>
				
				
			</form:form>
		</div>
	</div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />
