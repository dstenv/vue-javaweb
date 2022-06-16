package com.tdw.Service;

import com.tdw.pojo.Good_Order;

import java.util.List;

public interface Good_OrderService {

    /**
     * 添加订单数据
     * @param Goodid
     * @param Orderid
     * @return
     */
    public int addDetil(Integer Goodid,Integer Orderid);

    /**
     * 修改商品状态(当商品被购买时)
     * @param Status
     * @param PayUserid
     * @param Gid
     * @return
     */
    public int updateGoodstatus(Integer Status,Integer PayUserid,Integer Gid);

    /**
     * 查找需要修改的订单号，修改订单状态时，需要先找到是哪个订单 这里根据Gid和支付人的用户id唯一确定订单号 当发布人点击发货时，传发货的商品的id，通过调用
     * GoodService服务的方法先查到支付人用户的id，在将支付人的用户id传进来
     * @param Gid
     * @return
     */
    public Good_Order queryOrderidByGid(Integer Gid, Integer Userid);

    /**
     * 查询订单详细信息，先找到商品id，再调用goodservice的方法查找商品信息
     * @param Orderid
     * @return
     */
    public List<Good_Order> queryGoodid(Integer Orderid);

    /**
     * 查找good_order表的数据
     * @return
     */
    public List<Good_Order> queryGoodidandOrderid();

}
