package com.tdw.Dao.impl;

import com.tdw.Dao.User_OrderDao;
import com.tdw.pojo.User_Order;

import java.math.BigDecimal;
import java.util.List;

public class User_OrderDaoImpl extends BaseDao implements User_OrderDao {
    @Override
    public int addOrder(Integer Userid, String Oaddress, BigDecimal Price, Integer Ostatus) {
        String sql = "insert into user_order(`Userid`,`Oaddress`,`Price`,`Ostatus`) values(?,?,?,?)";
        return update(sql,Userid,Oaddress,Price,Ostatus);
    }


    @Override
    public int updateOrderstu(Integer Ostatus, Integer Orderid) {
        String sql = "update user_order " + "set Ostatus=? where Orderid=?";
        return update(sql,Ostatus,Orderid);
    }

    @Override
    public int deleteOrder(Integer Orderid) {
        String sql = "delete from user_order where Orderid=?";
        return update(sql,Orderid);
    }

    @Override
    public List<User_Order> queryOrderid(Integer Userid) {
        String sql = "select `Orderid` from user_order where Userid=? order by Orderid desc";
        return queryForList(User_Order.class,sql,Userid);
    }

    @Override
    public List<User_Order> queryOrder(Integer Userid) {
        String sql = "select * from user_order where Userid=?";
        return queryForList(User_Order.class,sql,Userid);
    }
}
