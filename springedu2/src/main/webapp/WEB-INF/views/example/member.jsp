<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b>member page</b>
<hr/>
	<!-- 1) SPRING_SECURITY_CONTEXT 내장객체 -->
	<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>
	<b>사용자아이디: ${user.id }</b><br>
	<b>사용자별칭: ${user.nickName }</b><br>
	<b>사용자생일: ${user.birth }</b>
	<p>권한: ${user.authorities }</p>
	<c:forEach var="auth" items="${user.authorities }">
		<h3>${auth.roleId }</h3>
	</c:forEach>
	<hr>
	<!-- 2) 권한 authorize -->
	<sec:authorize access="hasRole('ROLE_USER')">
		ROLE_USER권한만 보임
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		ROLE_ADMIN권한만 보임
	</sec:authorize>
	<hr>
	<!-- 3) 인증 authorication -->
	<sec:authentication property="principal" />
<%-- 	<sec:authentication property="principal.pw" /> --%>
</body>
</html>