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
all page

	<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>
	<b>사용자아이디${user.id }</b>
	<sec:authorize access="hasRole('ROLE_USER')">
		ROLE_USER권한만 보임
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		ROLE_ADMIN권한만 보임
	</sec:authorize>
	<sec:authentication property="principal" />
</body>
</html>