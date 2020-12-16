package com.itheima.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.model.AuthenticationRequest;
import com.itheima.model.UserDto;
import com.itheima.service.AuthenticationService;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationService authService;

	@RequestMapping(value = "/login", produces = "text/plain;charset=utf-8")
	public String login(AuthenticationRequest request, HttpSession session, HttpServletResponse response) {
		UserDto userDto = authService.authentication(request);
		session.setAttribute(UserDto.SESSION_KEY, userDto);
		response.addCookie(new Cookie("_mycookie", userDto.getUsername() + ":" + userDto.getPassword()));
		return request.getUsername() + "：登陆成功";
	}

	@RequestMapping(value = "/r/r1", produces = "text/plain;charset=utf-8")
	public String r1(HttpSession session, HttpServletRequest request) {
		String fullname = null;
		Object obj = session.getAttribute(UserDto.SESSION_KEY);
		if (obj == null) {
			fullname = "xxxx";
		} else {
			fullname = ((UserDto) obj).getFullname();
		}
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("_mycookie")) {
				System.out.println(cookie.getValue());
			}
		}
		return fullname + "访问了页面";
	}

	@RequestMapping(value = "/r/r2", produces = "text/plain;charset=utf-8")
	public String r2(HttpSession session, HttpServletRequest request) {
		String fullname = null;
		Object obj = session.getAttribute(UserDto.SESSION_KEY);
		if (obj == null) {
			fullname = "xxxx";
		} else {
			fullname = ((UserDto) obj).getFullname();
		}
		return "访问了页面  r2";
	}

	@RequestMapping(value = "/logout", produces = "text/plain;charset=utf-8")
	public String logout(HttpSession session) {
		session.invalidate();// 清空该会话的session
		return "退出成功";
	}
}
