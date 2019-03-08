<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />
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
<script>
		//유효성체크 오류시 에러메세지 처리
		$(".errmsg").each(function(idx){
			if($(this).text().length > 0) {
				$(this).prev().addClass("is-invalid");
			}
		});	 
		
		$("#pw,#pwchk").on("keyup", function() {
			if ($("#pw").val() != $("#pwchk").val()) {
				console.log($(this).val());
				$(".pwErr").text('비밀번호가 틀립니다!');
				$(this).focus();
			} else {
				$(".pwErr").text('');
			}
		})

</script>

<div class="container">
	<div class="row justify-content-center">
		<div class="col-4">
		<form:form modelAttribute="mdto" action="${pageContext.request.contextPath }/member/checkOK">
				<div class="text-center mb-4">
					<h1 class="h3 mb-3 font-weight-normal">비밀번호 변경</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>

				<div class="form-label-group">
						<form:label	path="id">아이디</form:label>
					<form:input type="text" path="id" class="form-control"
						required="true" autofocus="true" readonly="true" value="${user.id }" /> 
						<form:errors path="id" cssClass="errmsg"></form:errors>
				</div>

				<div class="mb-4"></div>
				<div class="form-label-group">
					<form:label	path="pw">새 비밀번호</form:label>
					<form:input type="password" path="pw" class="form-control"
						placeholder="새 비밀번호" required="true" /> 
						<form:errors path="pw" cssClass="errmsg"></form:errors>
				</div>
				<div class="form-label-group">
					<label for="pwchk">비밀번호확인</label> 
					<input type="password" name="pwchk" id="pwchk" class="form-control"	placeholder="새비밀번호 확인" />
					<span class="col-sm-2 pwErr"></span>
				</div>
				<div class="mb-3"></div>
				<div class="mb-3"></div>
				<div class="mb-5"></div>
				<button class="btn btn-lg btn-dark btn-block" type="submit">비밀번호 변경</button>
				<p class="mt-5 mb-3 text-muted text-center">&nbsp;</p>
			</form:form>
		</div>
	</div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />
