package com.itheima.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.itheima.model.UserDto;

@Component
public class MyInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object obj = request.getSession().getAttribute(UserDto.SESSION_KEY);
		if (obj == null) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("请登录");
			response.getWriter().flush();
			response.getWriter().close();
		}
		UserDto userDto = (UserDto) obj;
		String uri = request.getRequestURI();
		if (userDto.getAuthors().contains("p1") && uri.contains("/r/r1")) {
			return true;
		}
		if (userDto.getAuthors().contains("p2") && uri.contains("/r/r2")) {
			return true;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("没有权限，拒绝访问");
		response.getWriter().flush();
		response.getWriter().close();
		return false;
	}
}
