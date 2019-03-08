<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
<script type="text/javascript">
$(function(){
	//수정버튼 클릭시
	$(".modify, .delete, .insert").on("click",function(){
		$(location).attr("href",$(this).attr("data-memurl"));
	});
});
</script>

<div class="container">
<div class="row justify-content-center">
<table class="table table-sm table-dark">
	<colgroup>
					<col width="12%">
					<col width="12%">
					<col width="10%">
					<col width="5%">
					<col width="8%">
					<col width="10%">
					<col width="10%">
					<col width="5%">
					<col width="5%">
	</colgroup>
  <thead>
    <tr>
      <th scope="col">아이디</th>
      <th scope="col">전화번호</th>
      <th scope="col">별칭</th>
      <th scope="col">성별</th>
      <th scope="col">지역</th>
      <th scope="col">생년월일</th>
      <th scope="col">가입일시</th>
      <th scope="col">수정</th>
      <th scope="col">삭제</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${memberList }" var="mdto">	
    <tr>
      <th>${mdto.id }</th>
      <td>${mdto.tel }</td>
      <td>${mdto.nickName }</td>
      <td>${mdto.gender }</td>
      <td>${mdto.region }</td>
      <td>${mdto.birth }</td>
      <td>${mdto.cdate }</td>
      <td><button class="btn btn-outline-light modify" data-memurl="/member/memberModifyForm/${mdto.id }">수정</button></td>
      <td><button class="btn btn-outline-light delete" data-memurl="/member/memberDelete/${mdto.id }">삭제</button></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div> 

</div>
<jsp:include page="/main_footer.jsp" flush="false" />
