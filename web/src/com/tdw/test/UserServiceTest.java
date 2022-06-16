package com.tdw.test;

import com.tdw.Service.UserService;
import com.tdw.Service.impl.UserServiceImpl;
import com.tdw.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"ab","123456","123456",null,""));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"ab","123456","123456",null,"")));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("ab")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}