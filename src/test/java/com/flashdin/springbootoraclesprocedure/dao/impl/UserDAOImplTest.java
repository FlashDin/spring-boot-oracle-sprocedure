package com.flashdin.springbootoraclesprocedure.dao.impl;

import com.flashdin.springbootoraclesprocedure.dao.UserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDAOImplTest {

    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAOImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
        userDAO.findAll();
    }

    @Test
    public void findByUsername() {
    }
}