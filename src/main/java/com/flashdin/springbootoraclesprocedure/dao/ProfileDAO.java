package com.flashdin.springbootoraclesprocedure.dao;

import com.flashdin.springbootoraclesprocedure.entity.Profile;

import java.util.List;

public interface ProfileDAO extends BaseDAO<Profile> {

    List<Profile> findByName(Profile param);

}
