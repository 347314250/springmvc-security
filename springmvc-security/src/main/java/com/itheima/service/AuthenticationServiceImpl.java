package com.itheima.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itheima.model.AuthenticationRequest;
import com.itheima.model.UserDto;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private HashMap<String, UserDto> map = new HashMap<String, UserDto>();

	{
		Set<String> s1 = new HashSet<String>();
		s1.add("p1");
		Set<String> s2 = new HashSet<String>();
		s2.add("p2");
		map.put("zhangsan", new UserDto(s1, "1010", "zhangsan", "123", "张三", "13445677"));
		map.put("lisi", new UserDto(s2, "1011", "lisi", "456", "李四", "66666666"));

	}

	@Override
	public UserDto authentication(AuthenticationRequest request) {
		// 校验是否为空
		if (request == null || StringUtils.isEmpty(request.getUsername())
				|| StringUtils.isEmpty(request.getPassword())) {
			throw new RuntimeException("用户名或者密码为空");
		}
		// 根据账号去数据库查询
		UserDto user = getUserDto(request.getUsername());
		// 判断用户是否为空
		if (user == null) {
			throw new RuntimeException("查询不到该用户");
		}
		// 校验密码
		if (!user.getPassword().equals(request.getPassword())) {
			throw new RuntimeException("密码错误");
		}
		// 认证通过，返回用户信息
		return user;
	}

	private UserDto getUserDto(String username) {
		return map.get(username);
	}

}
