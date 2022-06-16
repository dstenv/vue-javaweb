package com.tdw.Service;

import com.tdw.pojo.User_Order;

import java.math.BigDecimal;
import java.util.List;

public interface User_OrderService {

    /**
     * 生成订单
     * @param Userid
     * @param Price
     * @return
     */
    public int addOrder(Integer Userid, String Oaddress, BigDecimal Price, Integer Ostatus);

    /**
     * 删除一条订单
     * @param Orderid
     * @return
     */
    public int deleteOrder(Integer Orderid);

    /**
     * 查询订单id 降序输出 第一个订单id则为最新的订单id
     * @param Userid
     * @return
     */
    public List<User_Order> queryOrderid(Integer Userid);


    /**
     * 修改订单状态 0代表未发货 1代表已发货 2代表已收货
     * @param Ostatus
     * @param Orderid
     * @return
     */
    public int updateOrderstu(Integer Ostatus,Integer Orderid);


    /**
     * 查询用户的订单信息
     * @param Userid
     * @return
     */
    public List<User_Order> queryOrder(Integer Userid);

}
