package com.tdw.test;

import com.tdw.Dao.UserDao;
import com.tdw.Dao.impl.UserDaoImpl;
import com.tdw.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if (userDao.queryUserByUsername("aaa") == null){
            System.out.println("用户名可以用");
        }else {
//            System.out.println(userDao.queryUserByUsername("aaa"));
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("aaa","123456")==null){
            System.out.println("用户名或密码错误");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {

        System.out.println(userDao.saveUser(new User(null,"aa","123456","1234567",null,"")));



    }
}