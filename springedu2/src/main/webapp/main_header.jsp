<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
<html lang="ko">
		<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
<title>spring framwork</title>
<!-- Bootstrap 시작 -->
<link rel="stylesheet" href="/resources/bootstrap-4.2.1/dist/css/bootstrap.css" />
<!-- google font -->
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Patua+One" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&amp;subset=korean" rel="stylesheet">

<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/dist/js/bootstrap.js"></script>
<!-- Bootstrap 끝 -->
	
<!-- font awesome -->	
<link rel="stylesheet" href="/webjars/font-awesome/5.6.3/css/all.css" />


    <style>

    	
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      
    </style>
 <!-- Custom styles for this template -->
 <link href="/resources/css/blog.css" rel="stylesheet">
    
   	<style>
   	
   	.display-4 {
   		font-size: 2rem;
   		font-weight: 400;
   		line-height:1.2;
   	}
   	body, html, samp, .tooltip, .popover, text-monospace , .container {
   		font-family: 'Noto Sans KR', 'Open Sans',sans-serif;
   	}
   	p, textarea{
      font-family: 'Noto Sans KR', sans-serif;
    }
   	ul,li{
   		list-style:none;
   	}
   	</style>
  </head>
  <body>
    <c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>
    <div class="container">
  <header class="blog-header py-3">
 		
    <div class="row flex-nowrap justify-content-between align-items-center">
    <a class="text-muted" href="#" style="width: 2px">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mx-3" focusable="false" role="img"><title>Search</title><circle cx="10.5" cy="10.5" r="7.5"></circle><line x1="21" y1="21" x2="15.8" y2="15.8"></line></svg>
    </a>
    	<form action="/bbs/list" method="post" class="form-inline mt-1 mt-md-0">
        <input class="col-sm-5 form-control form-control-sm px-1 mr-1" type="search" id="keyword" name="keyword" value="${pc.keyword}" placeholder="검색어">
        <button class="btn btn-sm btn-outline-secondary" type="submit">검색</button>
      </form>
<!--       <div class="col-4 pt-1">
        <a class="text-muted" href="#">고객센터</a>
      </div> -->
      <div class="col-4 text-center">
        <a class="blog-header-logo text-dark" href="/">&nbsp;&nbsp;&nbsp;PANTONE</a>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
            
        <c:choose>
        <c:when test="${user eq null }">
	        <a class="p-1 text-muted" href="/member/findidForm">아이디</a>/<a class="p-1 text-muted" href="/member/findpwForm">비밀번호찾기</a>
	        <a class="btn btn-sm btn-outline-secondary" href="/login/loginForm">로그인</a>
	        <a class="btn btn-sm btn-outline-secondary" href="/member/memberJoinForm">회원가입</a>
        </c:when>
        <c:otherwise>
        	<b class="mr-2"><a href="/member/memberModifyFormView/${user.id}" class="text-decoration-none text-reset text-dark">${user.nickName}님</a></b>
        	<a class="btn btn-sm btn-outline-secondary" href="/login/logout">로그아웃</a>
        </c:otherwise>
        </c:choose>
        
        
      </div>
    </div>
  </header>

  <div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-end">
   	 <a class="p-2 text-muted" href="/notice/list">공지사항</a>
      <a class="p-2 text-muted" href="/bbs/list">게시판</a>
    </nav>
  </div>
</div>
