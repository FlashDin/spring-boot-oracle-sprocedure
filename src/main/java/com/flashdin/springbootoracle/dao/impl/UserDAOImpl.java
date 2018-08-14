package com.flashdin.springbootoracle.dao.impl;

import com.flashdin.springbootoracle.dao.UserDAO;
import com.flashdin.springbootoracle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User param) {
        String sql = "insert into table_user (username,password) values (?,?)";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getUsername());
            ps.setString(2, param.getPassword());
            return ps;
        });
        param.setId(rtn);
        return param;
    }

    @Override
    public User update(User param) {
        String sql = "update table_user set username=?,password=? where id=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getUsername());
            ps.setString(2, param.getPassword());
            ps.setInt(3, param.getId());
            return ps;
        });
        param.setId(rtn);
        return param;
    }

    @Override
    public int delete(User param) {
        String sql = "delete from table_user where id=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, param.getId());
            return ps;
        });
        return rtn;
    }

    @Override
    public User findById(int id) {
        String sql = "select * from table_user where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from table_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findByUsername(User param) {
        String sql = "select * from table_user where username like ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + param.getUsername() + "%"}, new BeanPropertyRowMapper<>(User.class));
    }
}
