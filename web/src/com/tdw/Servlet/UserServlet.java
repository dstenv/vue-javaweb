package com.tdw.Servlet;

import com.tdw.Service.UserService;
import com.tdw.Service.impl.UserServiceImpl;
import com.tdw.pojo.ResultData;
import com.tdw.pojo.User;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/userservlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 利用session存储数据
        //HttpSession session = req.getSession();
        //session.setMaxInactiveInterval(10*60);
        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ResultData resultData = new ResultData();
        User userInfo = new User();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password,null,null,""));
        // 通过username找到userid 存放到session
        User findUser = userService.findUser(username);
        //session.setAttribute("User",findUser);
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
//            req.setAttribute("msg","用户或密码错误！");
//            req.setAttribute("username", username);
            // 把错误信息保存到resultData对象 并返回给前端
            System.out.println("登录失败");
            resultData.setCode(200);
            resultData.setMsg("用户或密码错误！");
            resultData.setData(null);
            send(resp,resultData);
        } else {
            //返回数据给前端
            System.out.println("登录成功");
            //System.out.println(findUser);
            resultData.setCode(201);
            resultData.setMsg("登录成功");
            resultData.setData(findUser);
            send(resp,resultData);
        }
    }

    /**
     * 注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        //String code = req.getParameter("code");

        ResultData resultData = new ResultData();
//        2、检查 用户名是否为空
        if (username == null || username == ""){
            resultData.setCode(300);
            resultData.setMsg("用户名为空!");
            resultData.setData(null);
            send(resp,resultData);
        }

//        3、检查 用户名是否可用
        if (userService.existsUsername(username)) {
            System.out.println("用户名[" + username + "]已存在!");

            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "用户名已存在！！！");
            req.setAttribute("username", username);
            req.setAttribute("phone", phone);
            resultData.setCode(202);
            resultData.setMsg("用户名已存在!");
            resultData.setData(null);
            send(resp,resultData);
        } else {
            //      可用
//                调用Sservice保存到数据库
            userService.registUser(new User(null, username, password, phone,null,""));
            resultData.setCode(203);
            resultData.setMsg("注册成功");
            User finduser = userService.findUser(username);

            resultData.setData(finduser);

            send(resp,resultData);
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


    /**
     * 修改用户名
     */
    protected void updateusername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultData resultData = new ResultData();
        String uid = req.getParameter("uid");
        String username = req.getParameter("username");

        Integer userid = Integer.valueOf(uid);
        //User findUser = userService.findUser(username);
        System.out.println("当前用户的userid为:"+userid);

        if (userService.existsUsername(username)) {
            System.out.println("用户名[" + username + "]已存在!");
            resultData.setCode(100);
            resultData.setMsg("用户名已存在");
            send(resp,resultData);
        } else {
            // 可用
            Integer ispassusername = userService.updateUsername(new User(userid,username,"","",null,""));

            User combackUser = userService.findUserByuserid(userid);
            /**
             * if返回-1则表示修改失败，反之，修改成功
             */
            //System.out.println(Userid);
            if (ispassusername == -1){
                System.out.println("修改失败");
                resultData.setCode(101);
                resultData.setMsg("修改失败");
                resultData.setData(null);
                send(resp,resultData);
            }else{
                System.out.println("修改成功");
                resultData.setCode(102);
                resultData.setMsg("修改成功");
                resultData.setData(combackUser);
                send(resp,resultData);
            }

        }

    }

    /**
     * 修改密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updatepassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");
        String password = req.getParameter("password");

        ResultData resultData = new ResultData();
        Integer userid = Integer.valueOf(uid);
        System.out.println("当前用户的userid为:"+userid);

        Integer ispasspwd = userService.updateUserpassword(new User(userid,"",password,"",null,""));

        User combackUser = userService.findUserByuserid(userid);
        /**
         * if返回-1则表示修改失败，反之，修改成功
         */
        //System.out.println(Userid);
        if (ispasspwd == -1){
            System.out.println("修改失败");
            resultData.setCode(103);
            resultData.setMsg("修改失败");
            resultData.setData(null);
            send(resp,resultData);
        }else{
            System.out.println("修改成功");
            send(resp,combackUser);
        }
    }

    /**
     * 查找用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectuser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String userid = req.getParameter("userid");

        ResultData resultData = new ResultData();
        Integer Userid = Integer.valueOf(userid);
        System.out.println(Userid);

        User finduser = userService.findUserByuserid(Userid);

        if (finduser == null){
            resultData.setCode(104);
            resultData.setMsg("无此用户");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            resultData.setCode(105);
            resultData.setMsg("查找成功");
            resultData.setData(finduser);
            send(resp,resultData);
        }
    }
}

    

