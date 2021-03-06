<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />
<jsp:include page="/main_header.jsp" flush="false" />
<!DOCTYPE html>
<style>
.errmsg, .pwErr {
	color: red;
}
</style>

<script>
	$(function(){
		
		//유효성체크 맞을 시 처리
		
		
		
		
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

		// 회원정보 변경 버튼
		$("#modifyBtn").on("click",function(e){
			e.preventDefault();	
			location.href="/member/memberModifyForm/${user.id }";
		});
		
		// 비밀번호 변경버튼
		$("#passwordBtn").on("click",function(e){
			e.preventDefault();	
			location.href="/member/pwCheckForm";
		});
		
				
		// 탈퇴버튼
		$("#exit").on("click",function(e){
			e.preventDefault();	
			location.href="/member/memberDelete/${user.id }";
		}); 

	});
</script>


<div class="container">
<div class="row">
<div class="col offset-3 ">
	
	

	<form:form modelAttribute="mdto"
		action="${pageContext.request.contextPath }/member/memberModify">
		
		<div class="form-group row">
				<form:label path="id" class="col-sm-2 col-form-label col-form-label-sm">아이디</form:label>
				<form:input type="text" class="col-sm-3 form-control" path="id" readonly="true" />
				<form:errors path="id" cssClass="col-sm-2 errmsg"></form:errors>
		</div>

<%-- 		<div class="form-group row">
				<form:label path="pw" class="col-sm-2 col-form-label col-form-label-sm">비밀번호</form:label>
				<form:input type="password" class="col-sm-3 form-control" path="pw"
					placeholder="비밀번호를 입력하세요." />
				<form:errors path="pw" cssClass="col-sm-2 errmsg"></form:errors>
		</div>

		<div class="form-group row">
			<label for="pwchk" class="col-sm-2 col-form-label col-form-label-sm">비밀번호확인</label> 
			<input type="password" name="pwchk" id="pwchk" class="col-sm-3 form-control"	placeholder="비밀번호를 확인해주세요." />
			<span class="col-sm-2 pwErr"></span>
		</div> --%>


		<div class="form-group row">
				<form:label path="tel" class="col-sm-2 col-form-label col-form-label-sm">전화번호</form:label>
				<form:input type="text" class="col-sm-3 form-control" readonly="true" path="tel"
					placeholder="전화번호를 입력하세요. ex)010-1234-5678" />
				<form:errors path="tel" cssClass="col-sm-2 errmsg"></form:errors>
		</div>


		<div class="form-group row">
				<form:label path="nickName" class="col-sm-2 col-form-label col-form-label-sm">닉네임</form:label>
				<form:input type="text" class="col-sm-3 form-control"
					path="nickName" readonly="true" placeholder="사용하실 닉네임을 입력하세요." />
				<form:errors path="nickName" cssClass="col-sm-2 errmsg"></form:errors>
		</div>


		<div class="form-group row">
			<form:label path="gender" class="col-sm-2 col-form-label col-form-label-sm">성별</form:label>
			<form:input type="text" class="col-sm-3 form-control"
					path="gender" readonly="true" />
			<%-- <form:radiobuttons class="ml-2" items="${gender }" path="gender" itemLabel="label" readonly="true" itemValue="code"/> --%>
  	</div>
  
  
   
    <div class="form-group row">    
				<form:label path="region" class="col-sm-2 col-form-label col-form-label-sm">지역</form:label>
				<form:input type="text" class="col-sm-3 form-control"
					path="region" readonly="true" />
  </div>
  
  
  		<div class="form-group row">
				<form:label path="birth" class="col-sm-2 col-form-label col-form-label-sm">생년월일</form:label>
				<form:input type="date" class="col-sm-3 form-control" readonly="true"
					path="birth" />
				<form:errors path="birth" cssClass="col-sm-2 errmsg"></form:errors>
		</div>
		<div class="mb-6">&nbsp;&nbsp;</div>

		<div class="ml-5">
			<button class="btn btn-dark" id="modifyBtn">회원정보 변경</button>&nbsp;
			<button class="btn btn-dark" id="passwordBtn">비밀번호 변경</button>&nbsp;
			<button type="button" class="btn btn-dark" data-toggle="modal" data-target="#delete">
			  탈퇴
			</button>&nbsp;
			<!-- Button trigger modal -->

<!-- 			<button class="btn btn-dark" id="modifyCancelBtn">수정취소</button> -->
		</div>

	</form:form>
		<div class="mb-3"></div>
		<div class="mb-3"></div>
		<div class="mb-5"></div>

<!-- Modal -->
<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원탈퇴</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        정말 탈퇴 하시겠습니까? 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-dark" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-dark" id="exit">탈퇴</button>
      </div>
    </div>
  </div>
</div>
		
		
<!-- 	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
		})();
	</script> -->
		</div>
</div></div>

<jsp:include page="/main_footer.jsp" flush="false" />
