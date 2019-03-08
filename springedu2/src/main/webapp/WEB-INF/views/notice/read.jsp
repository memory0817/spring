<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/main_header.jsp" flush="false" />
<style>
.errmsg, {
	color: red;
}
</style>
<script>
  $(function(){
	  // 읽기모드
	  $("#rmode").css({"display":""});
	  
	  // 수정모드
	  $("#umode").css({"display":"none"});
	  
	  
	  //수정버튼 클릭시 편집모드로 변환
	  $("#btn2").on("click",function(e){
		  
		  
		  //1) 버튼
		  $("#umode").css({"display":""});	
		  $("#rmode").css({"display":"none"});
		  
			//2) 제목,내용 readonly 제거
		  $("#btitle,#bcontent").removeAttr("readonly");	
  	
  		//3) 수정버튼 눌리면 title바뀜
  		$("#title").text("게시글 수정");
	  });

	  //삭제버튼
	  $("#btn3").on("click",function(e){		  	  
		  location.href="/notice/delete?nnum=${notice.nnum}&reqPage=${rc.reqPage}";		  
	  }); 
	  
	  //답글버튼 : 답글작성됨
	  $("#btn1").on("click",function(e){		 	  
		  location.href="/notice/replyForm/${notice.nnum}/${rc.reqPage}";		  
	  });
	  
	  //목록버튼 : 목록으로 이동
	  $("#btn7,#btn4").on("click",function(e){
		 	  
		  location.href="/notice/list?reqPage=${rc.reqPage}";		  
	  }); 
	  
		//수정취소버튼 클릭시 읽기모드로 변환
	  $("#btn6").on("click",function(e){		    
		  location.href="/notice/view?nnum=${notice.nnum}&reqPage=${rc.reqPage}";		  
	  }); 

 		//수정완료버튼 클릭시 읽기모드로 변환
	  $("#btn5").on("click",function(e){
		 	
		  if(valChk()){
		  	$("form").submit();	  
		  }
	  });  
	  
		//유효성체크 오류시 에러메세지 처리
 		$("span[id$='.errors']").each(function(idx){
			if($(this).text().length > 0) {
				$(this).prev().removeClass("is-valid").addClass("is-invalid");
				$(this).removeClass("valid-feedback").addClass("invalid-feedback");
			}
		});	  

	  
  });

  function valChk(){

	    //제목입력값이 없을경우
	   if($("#ntitle").val().length == 0){
	      alert('제목을 입력하세요!');
	      $("#ntitle").focus();
	      return false;
	    }

	    //제목입력길이 체크
	    if($("#ntitle").val().length > 30){
	      alert('30자 이상 입력불가!');
	      $("#ntitle").focus();
	      return false;
	    }


	    //내용입력값이 없을경우
	    if($("#ncontent").val.length == 0){
	      alert('내용을 입력하세요!');
	      $("#ncontent").focus();
	      return false;
	    }

	    //내용입력길이 체크
	    if($("#ncontent").val.length > 100){
	      alert('100자 이상 입력불가!');
	      $("#ncontent").focus();
	      return false;
	    }


	    return true;  
	  
  };

</script>
<div class="container">
	<div class="table-responsive">
		<h3 id="title" class="text-center p-3 mb-3 bg-white font-weight-bolder">게시글
			보기</h3>
		<form:form modelAttribute="noticeDTO" action="/notice/modifyOK" method="post">
		<form:hidden path="nnum" value="${noticeDTO.nnum }"/>
		<input type="hidden" name="reqPage" value="${rc.reqPage }"/>
			<table class="table table-sm" summary="게시글 보기">
				<colgroup>
					<col width="20%">
					<col width="">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td>
							<form:input class="form-control" path="ntitle" type="text" placeholder="제목을 입력하세요" readonly="true"
							value="${noticeDTO.ntitle }" />
							<form:errors class="valid-feedback" path="ntitle"></form:errors>
						</td>						
					</tr>
					<tr>
						<th>작성자</th>
						<td>&nbsp;관리자
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>								
							<form:textarea class="form-control" path="ncontent" rows="15" cols="30" 
							value="${noticeDTO.ncontent }" readonly="true" />
							<form:errors class="valid-feedback" path="ncontent"></form:errors>	
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
						<div id="rmode">			
							<button type="button" id="btn1"	class="btn btn-sm btn-outline-dark">답글</button>
							
							<!-- 작성자만 수정,삭제 기능 시작 -->
							<button type="button" id="btn2"	class="btn btn-sm btn-outline-dark">수정하기</button>
							<button type="button" id="btn3"	class="btn btn-sm btn-outline-dark">삭제</button>

							<!-- 작성자만 수정,삭제 기능 시작 -->
							
							<button type="button" id="btn4"	class="btn btn-sm btn-outline-dark">목록</button>
						</div>			
						<div id="umode">			
							<button type="button" id="btn5"	class="btn btn-sm btn-outline-dark">수정완료</button>
							<button type="button" id="btn6"	class="btn btn-sm btn-outline-dark">수정취소</button>
							<button type="button" id="btn7"	class="btn btn-sm btn-outline-dark">목록</button>
						</div>				
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/bbs/reReply.jsp" flush="false" />
<jsp:include page="/main_footer.jsp" flush="false" />