<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />
<jsp:include page="/main_header.jsp" flush="false" />

<head>
 <meta charset="UTF-8">
 <meta http-equiv="refresh" content="3; url=/member/memberModifyFormView/${user.id}">
 <title>비밀번호 변경</title>
</head>

<div class="container">
	<div class="row justify-content-center">
		<div class="col-4">
		
				<div class="text-center mb-4">
					<img class="mb-4" src="/resources/images/pwchange.jpg" alt=""
						width="300" height="300">
					<h1 class="h3 mb-3 font-weight-normal">비밀번호변경</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>

				<div>
						<label></label>
					<input type="text" class="form-control" value="비밀번호가 변경되었습니다"	style="text-align:center;" readonly="readonly" required="ture" autofocus="ture" />
					<div class="mb-2"></div>
				</div>
			<div class="mb-10"></div>
			<p class="mt-5 mb-3 text-muted text-center">&nbsp;</p>
			
		</div>
	</div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />
