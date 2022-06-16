package com.tdw.Servlet;

import com.tdw.Service.UserService;
import com.tdw.Service.impl.UserServiceImpl;
import com.tdw.pojo.User;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//
//    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        //String code = req.getParameter("code");


//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde

//        3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");

                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "用户名已存在！！！");
                req.setAttribute("username", username);
                req.setAttribute("phone", phone);


            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(new User(null, username, password, phone,null,""));
//
            }
//         else {
//            // 把回显信息，保存到Request域中
//            req.setAttribute("msg", "验证码错误！！");
//            req.setAttribute("username", username);
//            req.setAttribute("phone", phone);
//
//            System.out.println("验证码[" + code + "]错误");
//            //req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//        }
    }
}
