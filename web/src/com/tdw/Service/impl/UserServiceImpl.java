package com.tdw.Service.impl;

import com.tdw.Dao.UserDao;
import com.tdw.Dao.impl.UserDaoImpl;
import com.tdw.pojo.User;
import com.tdw.Service.UserService;

public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {

        if (userDao.queryUserByUsername(username) == null) {
            // 等于null,说明没查到，没查到表示可用
            return false;
        }

        return true;

    }

    @Override
    public int updateUsername(User user) {
        return userDao.updateUsername(user);
    }

    @Override
    public int updateUserpassword(User user) {
        return userDao.updateUserpassword(user);
    }

    @Override
    public User findUserid(String username) {
        return userDao.queryUseridByUsername(username);
    }

    @Override
    public User findUser(String username) {
        return userDao.queryUserByUsername(username);
    }

    @Override
    public User findUserByuserid(Integer userid) {
        return userDao.queryUserByUserid(userid);
    }
}
