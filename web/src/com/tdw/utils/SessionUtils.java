package com.tdw.utils;

import com.tdw.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static User getSession(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        return user;
    }

}
