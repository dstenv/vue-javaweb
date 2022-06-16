package com.tdw.Dao.impl;

import com.tdw.Dao.UserDao;
import com.tdw.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao{

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`phone` from user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUserid(Integer userid) {
        String sql = "select `id`,`username`,`password`,`phone` from user where id = ?";
        return queryForOne(User.class,sql,userid);
    }

    @Override
    public User queryUseridByUsername(String username) {
        String sql = "select `id` from user where username = ?";
        return queryForOne(User.class,sql, username);
    }


    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`phone` from user where username = ? and password = ?";
        return queryForOne(User.class, sql, username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(`username`,`password`,`phone`) values(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getPhone());
    }

    public int updateUsername(User user){
        String sql = "update user " + "set username =? where id =?";
        return update(sql,user.getUsername(),user.getId());
    }

    @Override
    public int updateUserpassword(User user) {
        String sql = "update user " + "set password =? where id =?";
        return update(sql,user.getPassword(),user.getId());
    }
}
