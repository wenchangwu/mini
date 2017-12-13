package com.lakala.mini.server.core.dao.impl;

import com.lakala.mini.server.core.dao.UserRepositoryCustom;
import com.lakala.mini.server.core.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager emf;

    @Override
    public List<User> test(String userName) {
        String jpql=" from User a where a.userName=:userName";
        return emf.createQuery(jpql,User.class).setParameter("userName",userName).getResultList();
    }

}
