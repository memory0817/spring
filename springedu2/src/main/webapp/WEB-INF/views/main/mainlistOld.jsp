<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>



</script>


<div class="container">
<div class="table-responsive">


<table class="table table-hover table-borderless table-sm" summary="게시글 목록">
			<colgroup>
				<col width="80%">
				<col width="20%">
			</colgroup>
				<thead>
				    <tr>
				      <th scope="col" class="table-info" colspan="2">&nbsp;공지사항 최신글</th>
				    </tr>
				  </thead>
				  <tbody>
				  <tr>
				      <td>공지사항 최신글입니다6</td>
				      <td>관리자</td>
				    </tr>
				    <tr>
				      <td>공지사항 최신글입니다5</td>
				      <td>관리자12</td>
				    </tr>
				    <tr>
				      <td>공지사항 최신글입니다4</td>
				      <td>관리자</td>
				    </tr>
				    <tr>
				      <td>공지사항 최신글입니다3</td>
				      <td>관리자</td>
				    </tr>
				    <tr>
				      <td>공지사항 최신글입니다2</td>
				      <td>관리자</td>
				    </tr>
				    <tr>
				      <td>공지사항 최신글입니다1</td>
				      <td>관리자</td>
				    </tr>
				  
				  
<%-- 				  <tr>
				  	<c:forEach items="${list }" var="noticeDTO">
				    
				      <td>
							<c:forEach begin="1" end="${noticeDTO.nindent }">&nbsp;&nbsp;</c:forEach>
	            <c:if test="${noticeDTO.nindent > 0 }">
	            	<img src="${pageContext.request.contextPath }/resources/images/re.png" width="20" style="dispaly=inline" >
	            </c:if>				
						<a href="/notice/view?nnum=${noticeDTO.nnum }&${pc.makeSearchURL(pc.recordCriteria.reqPage)} " class="text-decoration-none text-reset text-danger" >${noticeDTO.ntitle }</a>
						</td>
						<td>관리자</td>
				    
					</c:forEach>
					</tr> --%>
				  </tbody>
		</table>


</div>
</div>