package com.tdw.Servlet;

import com.tdw.Service.*;
import com.tdw.Service.impl.*;
import com.tdw.pojo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/orderServlet")
public class OrderServlet extends BaseServlet{

    private User_OrderService userOrderService = new User_OrderServiceImpl();
    private Good_OrderService goodOrderService = new Good_OrderServiceImpl();
    private GoodService goodService = new GoodServiceImpl();
    private UserService userService = new UserServiceImpl();
    private CarService carService = new CarServiceImpl();

    /**
     * 生成订单和添加订单数据 完成一条订单添加一个商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String address = req.getParameter("address");
        String gid = req.getParameter("gid");


        ResultData resultData = new ResultData();
        Integer payuserid = Integer.valueOf(uid);
        Integer Gid = Integer.valueOf(gid);

        //根据商品id查找商品信息 获取商品价格
        Good findgood = goodService.selectGoodByGid(Gid);

        //生成订单 如果返回-1则购买商品失败，反之则成功 购买成功后需要修改该商品的状态为1
        if (userOrderService.addOrder(payuserid,address,findgood.getGprice(),0) == -1){
            System.out.println("生成订单失败，即购买商品失败");
            resultData.setCode(700);
            resultData.setMsg("购买商品失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            //修改商品状态
            goodOrderService.updateGoodstatus(1,payuserid,Gid);

            //添加订单数据 需要先找到最新的订单id
            List<User_Order> user_orderList = userOrderService.queryOrderid(payuserid);

            //遍历user_orderlist 取第一个数据的订单id 应该有更好的方法去拿第一条数据
            for (User_Order userOrder:user_orderList){
                if (goodOrderService.addDetil(Gid,userOrder.getOrderid()) != -1){
                    System.out.println("添加订单数据成功");
                    //查询用户的订单信息
                    List<User_Order> user_orders = userOrderService.queryOrder(payuserid);
                    carService.deleteGoodFromCar(Gid,payuserid);
                    System.out.println(user_orders);
                    resultData.setCode(701);
                    resultData.setMsg("购买商品成功");
                    resultData.setData(user_orders);
                    send(resp,resultData);
                    break;
                }
            }

        }

    }

    /**
     * 生成订单和添加订单数据 完成一条订单添加多个个商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //用字符串获取前端传递的商品id数组
        String id = req.getParameter("id");
        String uid = req.getParameter("uid");
        String address = req.getParameter("address");


        ResultData resultData = new ResultData();
        Integer payuserid = Integer.valueOf(uid);
        //对字符串切割 获得字符数组
        String[] gid = id.split(",");

        //定义int数组 对其进行赋值
        int[] Gid = new int[gid.length];
        int i;
        for (i = 0; i < gid.length; i++) {
            int number = Integer.parseInt(gid[i].trim());
            Gid[i] = number;
            System.out.println(Gid[i]);
        }

        //根据商品id查找商品信息 获取订单价格
        BigDecimal totalprice = new BigDecimal(0);
        for (i = 0 ;i< Gid.length;i++){
            Good findgood = goodService.selectGoodByGid(Gid[i]);
            totalprice = totalprice.add(findgood.getGprice());
        }

        //生成订单 如果返回-1则购买商品失败，反之则成功 购买成功后需要修改该商品的状态为1
        if (userOrderService.addOrder(payuserid,address,totalprice,0) == -1){
            System.out.println("生成订单失败，即购买商品失败");
            resultData.setCode(700);
            resultData.setMsg("购买商品失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            //修改商品状态
            for (i = 0;i< Gid.length;i++)
                goodOrderService.updateGoodstatus(1,payuserid,Gid[i]);

            //添加订单数据 需要先找到最新的订单id
            List<User_Order> user_orderList = userOrderService.queryOrderid(payuserid);
            //遍历user_orderlist 取第一个数据的订单id 应该有更好的方法去拿第一条数据
            for (User_Order userOrder:user_orderList){
                for (i = 0;i< Gid.length;i++){
                    //添加订单数据
                    goodOrderService.addDetil(Gid[i],userOrder.getOrderid());
                    if ( i == Gid.length-1 ){
                        System.out.println("添加订单数据成功");
                        //查询用户的订单信息
                        List<User_Order> user_orders = userOrderService.queryOrder(payuserid);
                        resultData.setCode(701);
                        resultData.setMsg("购买商品成功");
                        resultData.setData(user_orders);
                        send(resp,resultData);
                        break;
                    }
                }
                break;
            }

        }
    }

    /**
     * 查询用户所有订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");


        Integer userid = Integer.valueOf(uid);

        ResultData resultData = new ResultData();
        //查询用户的订单信息
        List<User_Order> user_orders = userOrderService.queryOrder(userid);

        //根据订单id找到商品id 再找到商品
        List<Good> goods = new ArrayList<>();
        //订单id和商品id对应的数据
        List<Good_Order> good_orders = new ArrayList<>();

        //定义返回前端的List
        List<Object> sendData = new ArrayList<>();
        good_orders = goodOrderService.queryGoodidandOrderid();
        for (Good_Order goodOrder : good_orders){
            goods.add(goodService.selectGoodByGid(goodOrder.getGoodid()));
        }
        sendData.add(user_orders);
        sendData.add(good_orders);
        sendData.add(goods);
        if (user_orders!=null&&user_orders.size()>0){
            System.out.println("查询用户订单成功");
            resultData.setCode(702);
            resultData.setMsg("查询用户订单成功");
            resultData.setData(sendData);
            send(resp,resultData);
        }else {
            System.out.println("用户暂无订单");
            resultData.setCode(703);
            resultData.setMsg("用户暂无订单");
            resultData.setData(null);
            send(resp,resultData);
        }

    }

    /**
     * 查询订单详细信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectOrderInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String oid = req.getParameter("oid");


        Integer orderid = Integer.valueOf(oid);

        ResultData resultData = new ResultData();
        //根据订单id查找订单包含的商品id
        List<Good_Order> goodids = goodOrderService.queryGoodid(orderid);

        //用于保存good信息的list集合
        List<Good> goodList = new ArrayList<>();
        if (goodids!=null&&goodids.size()>0){
            for (Good_Order good_orderid:goodids){
                //查找good信息 返回good对象
                Good findgood = goodService.selectGoodByGid(good_orderid.getGoodid());
                goodList.add(findgood);
            }
            //设置返回的对象信息
            System.out.println("查询用户订单详细信息成功");
            resultData.setCode(704);
            resultData.setMsg("查询用户订单详细信息成功");
            resultData.setData(goodList);
            send(resp,resultData);
        }else {
            //设置返回的对象信息
            System.out.println("查询用户订单详细信息失败");
            resultData.setCode(705);
            resultData.setMsg("查询用户订单详细信息失败");
            resultData.setData(null);
            send(resp,resultData);
        }
    }

    /**
     * 发货处理 修改订单状态为1 1代表已发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendgood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");
        String gid = req.getParameter("gid");
        String payid = req.getParameter("payid");

        Integer userid = Integer.valueOf(uid);
        Integer goodid = Integer.valueOf(gid);
        Integer payuserid = Integer.valueOf(payid);
        ResultData resultData = new ResultData();

        //需要先找到修改的订单id 则需要先找到支付人的id
        //Good findpayid = goodService.selectGpayuserid(goodid);
        Good_Order findoid = goodOrderService.queryOrderidByGid(goodid, payuserid);
        if (userOrderService.updateOrderstu(1, findoid.getOrderid())!= -1){
            System.out.println("修改订单状态为1成功（1代表发货）");
            //根据订单id查找订单包含的商品id
            List<Good_Order> goodids = goodOrderService.queryGoodid(findoid.getOrderid());
            //用于保存good信息的list集合
            List<Good> goodList = new ArrayList<>();
            for (Good_Order good_orderid:goodids){
                //查找good信息 返回good对象
                Good findgood = goodService.selectGoodByGid(good_orderid.getGoodid());
                goodList.add(findgood);
            }
            //修改商品状态为2
            goodOrderService.updateGoodstatus(2,payuserid,goodid);
            resultData.setCode(706);
            resultData.setMsg("修改订单状态为1成功（1代表发货）");
            resultData.setData(goodList);
            send(resp,resultData);
        }else {
            System.out.println("修改订单状态为1失败（1代表发货）");
            resultData.setCode(707);
            resultData.setMsg("修改订单状态为1失败（1代表发货）");
            resultData.setData(null);
            send(resp,resultData);
        }
    }

    /**
     * 收货处理 修改订单状态为2 2代表已收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void recievegood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String oid = req.getParameter("oid");
        String gid = req.getParameter("gid");
        String payid = req.getParameter("payid");

        Integer orderid = Integer.valueOf(oid);
        Integer goodid = Integer.valueOf(gid);
        Integer payuserid = Integer.valueOf(payid);
        ResultData resultData = new ResultData();

        //修改订单中状态为2
        if (userOrderService.updateOrderstu(2,orderid)!= -1){
            //修改商品状态为2
            goodOrderService.updateGoodstatus(3,payuserid,goodid);
            System.out.println("修改订单状态为2成功（2代表收货）");
            resultData.setCode(708);
            resultData.setMsg("修改订单状态为2成功（2代表收货）");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            System.out.println("修改订单状态为2失败（2代表收货）");
            resultData.setCode(709);
            resultData.setMsg("修改订单状态为2失败（2代表收货）");
            resultData.setData(null);
            send(resp,resultData);
        }
    }


}
