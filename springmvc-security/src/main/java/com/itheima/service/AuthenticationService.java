package com.itheima.service;

import com.itheima.model.AuthenticationRequest;
import com.itheima.model.UserDto;

public interface AuthenticationService {

	UserDto authentication(AuthenticationRequest request);

}
