package com.kh.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;


// 로긴체크하는 인터셉터
public class LoginCheckInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean wasLogin = false;
		
		
		wasLogin = request.getSession().getAttribute("user") != null;
		
		
		if(!wasLogin) {
			response.sendRedirect("http://localhost:8080/login/loginForm");
		}
		
		
		
		
		
		
		return wasLogin;
	}
	
	
	

}
