package com.flashdin.springbootoracle.dao;

import com.flashdin.springbootoracle.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {

    List<User> findByUsername(User param);

}
