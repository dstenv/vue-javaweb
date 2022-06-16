package com.tdw.Service.impl;

import com.tdw.Dao.User_OrderDao;
import com.tdw.Dao.impl.User_OrderDaoImpl;
import com.tdw.Service.User_OrderService;
import com.tdw.pojo.User_Order;

import java.math.BigDecimal;
import java.util.List;

public class User_OrderServiceImpl implements User_OrderService {

    private User_OrderDao user_orderDao = new User_OrderDaoImpl();

    @Override
    public int addOrder(Integer Userid, String Oaddress, BigDecimal Price, Integer Ostatus) {
        return user_orderDao.addOrder(Userid,Oaddress,Price,Ostatus);
    }

    @Override
    public int deleteOrder(Integer Orderid) {
        return user_orderDao.deleteOrder(Orderid);
    }

    @Override
    public List<User_Order> queryOrderid(Integer Userid) {
        return user_orderDao.queryOrderid(Userid);
    }

    @Override
    public int updateOrderstu(Integer Ostatus, Integer Orderid) {
        return user_orderDao.updateOrderstu(Ostatus,Orderid);
    }

    @Override
    public List<User_Order> queryOrder(Integer Userid) {
        return user_orderDao.queryOrder(Userid);
    }
}
