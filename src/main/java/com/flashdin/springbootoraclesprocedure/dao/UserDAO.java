package com.flashdin.springbootoraclesprocedure.dao;

import com.flashdin.springbootoraclesprocedure.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {

    List<User> findByUsername(User param);

}
