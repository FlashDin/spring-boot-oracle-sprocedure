package com.flashdin.springbootoraclesprocedure.dao.impl;

import com.flashdin.springbootoraclesprocedure.dao.UserDAO;
import com.flashdin.springbootoraclesprocedure.entity.User;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User param) {
//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("procInsUser");
//        jdbcCall.addDeclaredParameter(new SqlParameter("p_username", OracleTypes.VARCHAR));
//        jdbcCall.addDeclaredParameter(new SqlOutParameter("p_password", OracleTypes.VARCHAR));
//        Map<String, String> callParams = new HashMap<>();
//        callParams.put("p_username", param.getUsername());
//        callParams.put("p_password", param.getPassword());
//        Map<String, Object> outputMap = jdbcCall.execute(callParams);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("procInsUser")
                .declareParameters(new SqlParameter("p_username", OracleTypes.VARCHAR),
                        new SqlParameter("p_password", OracleTypes.VARCHAR));
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_username", param.getUsername())
                .addValue("p_password", param.getPassword());
        Map out = jdbcCall.execute(in);
        int res = ((BigDecimal) out.get("p_res")).intValue();
        param.setId(res);
        param.setUsername((String) out.get("p_username"));
        param.setPassword((String) out.get("p_password"));
        return param;
    }

    @Override
    public User update(User param) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("procUptUser");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id", param.getId())
                .addValue("p_username", param.getUsername())
                .addValue("p_password", param.getPassword());
        Map out = jdbcCall.execute(in);
        int res = ((BigDecimal) out.get("p_res")).intValue();
        param.setId(res);
        return param;
    }

    @Override
    public int delete(User param) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("procDelUser");
        SqlParameterSource in = new MapSqlParameterSource().addValue("p_id", param.getId());
        Map out = jdbcCall.execute(in);
        int res = ((BigDecimal) out.get("p_res")).intValue();
        return res;
    }

    @Override
    public User findById(int id) {
        String sql = "select * from table_user where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll() {
//        String sql = "select * from table_user";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("procFindAllUser");
        Map<String, Object> out = jdbcCall.execute();
        return (List<User>) out.get("dataSets");
    }

    @Override
    public List<User> findByUsername(User param) {
        String sql = "select * from table_user where username like ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + param.getUsername() + "%"}, new BeanPropertyRowMapper<>(User.class));
    }

}
