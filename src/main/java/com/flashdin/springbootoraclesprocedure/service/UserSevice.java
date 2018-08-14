package com.flashdin.springbootoraclesprocedure.service;

import com.flashdin.springbootoraclesprocedure.entity.User;

import java.util.List;

public interface UserSevice extends BaseService<User> {

    List<User> findByUsername(User param);

}
