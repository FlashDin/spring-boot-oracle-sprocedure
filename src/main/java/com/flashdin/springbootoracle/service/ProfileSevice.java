package com.flashdin.springbootoracle.service;

import com.flashdin.springbootoracle.entity.Profile;

import java.util.List;

public interface ProfileSevice extends BaseService<Profile> {

    List<Profile> findByName(Profile param);
    
}
