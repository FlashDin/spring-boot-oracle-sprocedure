package com.flashdin.springbootoracle.dao;

import com.flashdin.springbootoracle.entity.Profile;

import java.util.List;

public interface ProfileDAO extends BaseDAO<Profile> {

    List<Profile> findByName(Profile param);

}
