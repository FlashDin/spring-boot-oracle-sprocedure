package com.flashdin.springbootoraclesprocedure.dao.impl;

import com.flashdin.springbootoraclesprocedure.dao.ProfileDAO;
import com.flashdin.springbootoraclesprocedure.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProfileDAOImpl implements ProfileDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Profile save(Profile param) {
        String sql = "insert into table_profile (namaLengkap,alamat,umur,jk) values (?,?,?,?)";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getNamaLengkap());
            ps.setString(2, param.getAlamat());
            ps.setInt(3, param.getUmur());
            ps.setString(4, param.getJk());
            return ps;
        });
        param.setId(rtn);
        return param;
    }

    @Override
    public Profile update(Profile param) {
        String sql = "update table_profile set namaLengkap=?,alamat=?,umur=?,jk=? where id=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getNamaLengkap());
            ps.setString(2, param.getAlamat());
            ps.setInt(3, param.getUmur());
            ps.setString(4, param.getJk());
            ps.setInt(5, param.getId());
            return ps;
        });
        param.setId(rtn);
        return param;
    }

    @Override
    public int delete(Profile param) {
        String sql = "delete from table_profile where id=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, param.getId());
            return ps;
        });
        return rtn;
    }

    @Override
    public Profile findById(int id) {
        String sql = "select * from table_profile where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<>(Profile.class));
    }

    @Override
    public List<Profile> findAll() {
        String sql = "select * from table_profile";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));
    }

    @Override
    public List<Profile> findByName(Profile param) {
        String sql = "select * from table_profile where namaLengkap like ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + param.getNamaLengkap() + "%"}, new BeanPropertyRowMapper<>(Profile.class));
    }
}
