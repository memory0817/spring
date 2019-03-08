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
		
				<div class="text-center mb-4">
					<img class="mb-4" src="/resources/images/findpw.jpg" alt=""
						width="300" height="300">
					<h1 class="h3 mb-3 font-weight-normal">비밀번호 찾기</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>

				<div>
						<label></label>
					<input type="text" style="text-align:center;" class="form-control" value="회원정보가 일치하지 않습니다."	readonly="readonly" required="ture" autofocus="ture" />
					<div class="mb-2"></div>
				</div>
			<div class="mb-10"></div>
			<p class="mt-5 mb-3 text-muted text-center">&nbsp;</p>
			
		</div>
	</div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />