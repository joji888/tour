package com.itfxp.travel.dao.impl;

import com.itfxp.travel.dao.UserDao;
import com.itfxp.travel.domain.User;
import com.itfxp.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*
  用户的持久层（跟数据库进行交互）
 */
public class UserDaoImpl implements UserDao {
    // 创建jdbc工具类对象
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        try {
            // 定义sql语句 根据用户名查询用户表信息
            String sql = "select * from tab_user where username = ?";
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 用户信息添加
     * @param user
     */
    @Override
    public void addUser(User user) {
        // 编写sql语句
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }
}
