package com.lakala.mini.server.core.dao;

import com.lakala.mini.server.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;

public interface UserRepository extends JpaRepository<User, Long>,UserRepositoryCustom {

    User findByUserName(String userName);


}