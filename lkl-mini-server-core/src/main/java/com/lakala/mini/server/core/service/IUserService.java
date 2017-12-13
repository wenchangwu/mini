package com.lakala.mini.server.core.service;

import com.lakala.mini.server.core.domain.User;

import java.util.List;

public interface IUserService {

    User findByUserName(String userName);

    User findByEmail(String email);

    User saveUser(User user);

    List<User> test(String test);
}