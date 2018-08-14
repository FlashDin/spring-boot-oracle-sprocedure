package com.flashdin.springbootoraclesprocedure.service;

import com.flashdin.springbootoraclesprocedure.entity.Profile;

import java.util.List;

public interface ProfileSevice extends BaseService<Profile> {

    List<Profile> findByName(Profile param);
    
}
