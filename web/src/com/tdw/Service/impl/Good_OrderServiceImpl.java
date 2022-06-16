package com.tdw.Service.impl;

import com.tdw.Dao.Good_OrderDao;
import com.tdw.Dao.impl.Good_OrderDaoImpl;
import com.tdw.Service.Good_OrderService;
import com.tdw.pojo.Good_Order;

import java.util.List;

public class Good_OrderServiceImpl implements Good_OrderService {

    private Good_OrderDao good_orderDao = new Good_OrderDaoImpl();

    @Override
    public int updateGoodstatus(Integer Status, Integer PayUserid, Integer Gid) {
        return good_orderDao.updayeGoodstatus(Status,PayUserid,Gid);
    }

    @Override
    public int addDetil(Integer Goodid, Integer Orderid) {
        return good_orderDao.addDetil(Goodid,Orderid);
    }

    @Override
    public Good_Order queryOrderidByGid(Integer Gid, Integer Userid) {
        return good_orderDao.queryOrderidByGid(Gid,Userid);
    }

    @Override
    public List<Good_Order> queryGoodid(Integer Orderid) {
        return good_orderDao.queryGoodid(Orderid);
    }

    @Override
    public List<Good_Order> queryGoodidandOrderid() {
        return good_orderDao.queryGoodidandOrderid();
    }
}
