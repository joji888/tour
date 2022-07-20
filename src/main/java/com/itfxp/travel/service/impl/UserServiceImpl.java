package com.itfxp.travel.service.impl;

import com.itfxp.travel.dao.UserDao;
import com.itfxp.travel.dao.impl.UserDaoImpl;
import com.itfxp.travel.domain.User;
import com.itfxp.travel.service.UserService;

/*
  处理用户的业务层
 */
public class UserServiceImpl implements UserService {
    // 创建UserDao实现类对象
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        // 根据用户的姓名，查有没有重名的用户名
        User isUser = userDao.findUserByUsername(user.getUsername());
        // 如果查询到用户的数据，封装到User对象，该对象如果不为null，则有数据查询到了用户信息了
        if (isUser != null) {
            return false; // 查询到了有重名的用户
        }

        // 如果没有，则执行添加操作。
        userDao.addUser(user);
        return true;
    }
}
