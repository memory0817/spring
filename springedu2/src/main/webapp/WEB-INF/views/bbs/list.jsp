<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/main_header.jsp" flush="false" />
<script>
 $(function(){

	 
	 $("#write").on("click",function(e){
		 e.preventDefault();

     
		 location.href="${pageContext.request.contextPath }/bbs/write"; 			 
		 

	 });	
	 
	 
	 
	 //검색버튼 클릭
	 $("#btn1").on("click",function(){
		 
		 if($("#keyword").val().trim().length == 0){
			 alert("검색어를 입력하세요!");
			 return;
			 
		 }
		 $("form").submit();
		 
		 
	 });
	 
	 
	 
	 
	 
	 
 });

 
 
</script>


<div class="container">
	<div class="table-responsive">
	<h3 class="text-center p-3 mb-3 bg-white font-weight-bolder"><a href="/bbs/list" class="text-decoration-none text-reset text-dark">게시글 목록</a></h3>
		<table class="table table-striped table-sm" summary="게시글 목록">
<!-- 			<caption>				<b>게시글 목록</b>			</caption> -->
			<colgroup>
				<col width="10%">
				<col width="55%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<thead>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일</th>
				<th scope="col">조회</th>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="bbsdto">
				 <c:if test="${bbsdto.isdel eq 'Y' }">
					<tr>
						<td>${bbsdto.bnum }</td>
						<td class="text-danger">삭제된 게시물입니다. </td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:if>
				<c:if test="${bbsdto.isdel ne 'Y' }">	
					<tr>
						<td>${bbsdto.bnum }</td>
						 <td>
							<c:forEach begin="1" end="${bbsdto.bindent }">&nbsp;&nbsp;</c:forEach>
	            <c:if test="${bbsdto.bindent > 0 }">
	            	<img src="${pageContext.request.contextPath }/resources/images/re.png" width="20" style="dispaly=inline" >
	            </c:if>				
						<a href="/bbs/view?bnum=${bbsdto.bnum }&${pc.makeSearchURL(pc.recordCriteria.reqPage)} " class="text-decoration-none text-reset text-danger" >${bbsdto.btitle }</a>
						</td>
						<td>${bbsdto.bnickname }</td>
						<td>${bbsdto.bcdate }</td>
						<td>${bbsdto.bhit }</td>
					</tr>
				</c:if>	
				</c:forEach>
				<tr style="background-color:white">
					<td colspan="5" align="right">
						<button id="write" type="button" class="btn btn-sm btn-outline-dark">글쓰기</button>					
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<nav aria-label="Page navigation example">
  <ul class="pagination pagination-sm justify-content-center">
  <c:if test="${pc.prev }">
  <li class="page-item">
      <a class="page-link" href="/bbs/list?${pc.makeSearchURL(1) }&searchType=${pc.searchType}&keyword=${pc.keyword}" tabindex="-1" aria-disabled="true">◀</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="/bbs/list?${pc.makeSearchURL(pc.startPage-1) }&searchType=${pc.searchType}&keyword=${pc.keyword}" tabindex="-1" aria-disabled="true">◁</a>
    </li>
    </c:if>

      
       
    <c:forEach begin="${pc.startPage }" end="${pc.endPage }" var="pageNum"> 
    <!-- 현재페이지와 요청페이지가 다르면   -->
	    <c:if test="${pc.recordCriteria.reqPage != pageNum}">  
	    	<li class="page-item"><a class="page-link" href="/bbs/list?${pc.makeSearchURL(pageNum)}&searchType=${pc.searchType }&keyword=${pc.keyword }">${pageNum }</a></li>
	    </c:if>
	   <!-- 요청페이지와 현재페이지가 같으면 강조표시 -->
	   <c:if test="${pc.recordCriteria.reqPage == pageNum}">  
	    	<li class="page-item active"><a class="page-link" href="/bbs/list?${pc.makeSearchURL(pageNum)}&searchType=${pc.searchType }&keyword=${pc.keyword }">${pageNum }</a></li>
	    </c:if>
    </c:forEach>
    

    
      
    
    <c:if test="${pc.next }">  
	    <li class="page-item">
	      <a class="page-link" href="/bbs/list?${pc.makeSearchURL(pc.endPage+1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">▷</a>
	    </li>
	    <li class="page-item">
	      <a class="page-link" href="/bbs/list?${pc.makeSearchURL(pc.finalEndPage) }&searchType=${pc.searchType}&keyword=${pc.keyword}">▶</a>
	    </li>
    </c:if>
    
    
  </ul>
</nav>
<!-- 검색조건 -->

      <div class="row justify-content-center mb-3">
        <form action="/bbs/list" method="post">
        	<div class="row">
          <label for= "key1" class="col-sm-2 col-form-label col-form-label-sm px-0 mx-0">검색어</label>
          <select name="searchType" id="key1" class="col-sm-3 custom-select custom-select-sm px-1 mr-1">
            <option value="TC" <c:out value="${pc.searchType == 'TC' ? 'selected' : ''}"/>>제목+내용</option>
            <option value="T" <c:out value="${pc.searchType == 'T' ? 'selected' : ''}"/>>제목</option>
            <option value="C" <c:out value="${pc.searchType == 'C' ? 'selected' : ''}"/>>내용</option>
            <option value="N" <c:out value="${pc.searchType == 'N' ? 'selected' : ''}"/>>작성자</option>
            <option value="I" <c:out value="${pc.searchType == 'I' ? 'selected' : ''}"/>>아이디</option>
          </select>
          <input class="col-sm-5 form-control form-control-sm px-1 mr-1" type="search" 
          id="keyword" name="keyword" value="${pc.keyword}" placeholder="검색어를 입력하세요">
          <button id="btn1" type="button" class="btn btn-sm btn-outline-dark px-2 mx-0">검색</button>
     			 </div>
        </form>
    </div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />