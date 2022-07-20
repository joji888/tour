package com.itfxp.travel.dao;

import com.itfxp.travel.domain.User;

public interface UserDao {

    // 根据用户名查询用户
    public User findUserByUsername(String username);

    // 添加用户
    public void addUser(User user);
}
