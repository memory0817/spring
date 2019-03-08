<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>


</script>


<div class="container">
	<div class="table-responsive">
<%-- 		<table class="table table-hover table-sm" summary="게시글 목록">
<!-- 			<caption>				<b>게시글 목록</b>			</caption> -->
			<colgroup>
				<col width="80%">
				<col width="20%">
			</colgroup>
			<thead>
				<th scope="col">공지사항 최신글</th>
				<th scope="col"> </th>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="noticedto">
					<tr>
						 <td>
							<c:forEach begin="1" end="${noticedto.nindent }">&nbsp;&nbsp;</c:forEach>
	            <c:if test="${noticedto.nindent > 0 }">
	            	<img src="${pageContext.request.contextPath }/resources/images/re.png" width="20" style="dispaly=inline" >
	            </c:if>				
						<a href="/list/view?nnum=${noticedto.nnum }&${pc.makeSearchURL(pc.recordCriteria.reqPage)} " class="text-decoration-none text-reset text-danger" >${noticedto.ntitle }</a>
						</td>
						<td>관리자</td>
					</tr>
				</c:forEach>
			</tbody>
		</table> --%>
			<table class="table table-hover table-borderless table-sm" summary="게시글 목록">
			<colgroup>
				<col width="80%">
				<col width="20%">
			</colgroup>
				<thead>
				    <tr>
				      <th scope="col" class="table-info" colspan="2">&nbsp;게시판 최신글</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>서비스레이어테스트190115</td>
				      <td>테스터12</td>
				    </tr>
				    <tr>
				      <td>&nbsp;&nbsp;&nbsp;[답글]입니다.</td>
				      <td>테스터12</td>
				    </tr>
				    <tr>
				      <td>[제목수정2]서비스레이어테스트190130</td>
				      <td>테스터10</td>
				    </tr>
				    <tr>
				      <td>제목-100</td>
				      <td>테스터10</td>
				    </tr>
				    <tr>
				      <td>&nbsp;&nbsp;&nbsp;[답글]제목-100</td>
				      <td>테스터10</td>
				    </tr>
				    <tr>
				      <td>&nbsp;&nbsp;&nbsp;[답글2]입니다.</td>
				      <td>테스터12</td>
				    </tr>
				  </tbody>
		</table>
	</div>
</div>