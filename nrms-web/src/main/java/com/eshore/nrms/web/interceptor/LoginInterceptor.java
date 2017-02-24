package com.eshore.nrms.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eshore.nrms.vo.Conts;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String basePath = request.getContextPath();
		//校验用户是否登录
		HttpSession session = request.getSession();
//		if(session.getAttribute(Conts.USER_SESSION_KEY) == null) {
//			response.sendRedirect(basePath);
//			return false;
//		}
		return true;
	}


}
