package com.lakala.mini.server.core.service.impl;

import com.lakala.mini.server.core.dao.UserRepository;
import com.lakala.mini.server.core.domain.User;
import com.lakala.mini.server.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public List<User> test(String test) {
        return userRepository.test(test);
    }
}