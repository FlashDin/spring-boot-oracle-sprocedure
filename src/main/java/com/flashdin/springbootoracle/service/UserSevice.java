package com.flashdin.springbootoracle.service;

import com.flashdin.springbootoracle.entity.User;

import java.util.List;

public interface UserSevice extends BaseService<User> {

    List<User> findByUsername(User param);

}
