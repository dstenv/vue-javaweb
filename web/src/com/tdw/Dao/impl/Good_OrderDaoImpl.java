package com.tdw.Dao.impl;

import com.tdw.Dao.Good_OrderDao;
import com.tdw.pojo.Good_Order;

import java.util.List;

public class Good_OrderDaoImpl extends BaseDao implements Good_OrderDao {

    @Override
    public int addDetil(Integer Goodid, Integer Orderid) {
        String sql = "insert into good_order(`Goodid`,`Orderid`) values(?,?)";
        return update(sql,Goodid,Orderid);
    }

    @Override
    public int updayeGoodstatus(Integer Status, Integer PayUserid, Integer Gid) {
        String sql = "update goods " + "set GStatus=?,Payuserid=? where Gid=?";
        return update(sql,Status,PayUserid,Gid);
    }

    @Override
    public Good_Order queryOrderidByGid(Integer Gid,Integer Userid) {
        String sql = "select user_order.Orderid from good_order,user_order where Goodid=? and Userid=? and good_order.Orderid=user_order.Orderid";
        return queryForOne(Good_Order.class,sql,Gid,Userid);
    }

    @Override
    public List<Good_Order> queryGoodid(Integer Orderid) {
        String sql = "select `Goodid`,`Orderid` from good_order where Orderid=?";
        return queryForList(Good_Order.class,sql,Orderid);
    }

    @Override
    public List<Good_Order> queryGoodidandOrderid() {
        String sql = "select `Goodid`,`Orderid` from good_order";
        return queryForList(Good_Order.class,sql);
    }
}
