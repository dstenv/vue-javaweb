package com.tdw.Servlet;

import com.mysql.cj.xdevapi.JsonArray;
import com.tdw.Service.CarService;
import com.tdw.Service.GoodService;
import com.tdw.Service.impl.CarServiceImpl;
import com.tdw.Service.impl.GoodServiceImpl;
import com.tdw.pojo.Car;
import com.tdw.pojo.Good;
import com.tdw.pojo.ResultData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

@WebServlet(urlPatterns = "/carServlet")
public class CarServlet extends BaseServlet {

    private CarService carService = new CarServiceImpl();
    private GoodService goodService = new GoodServiceImpl();

    /**
     * 添加商品到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void savegoodTocar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String gid = req.getParameter("gid");

        Integer Userid = Integer.valueOf(uid);
        Integer Goodid = Integer.valueOf(gid);

        ResultData resultData = new ResultData();
        if ( carService.addGoodToCar(Userid,Goodid) == -1){
            System.out.println("添加到购物车失败");
            resultData.setCode(600);
            resultData.setMsg("添加到购物车失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            System.out.println("添加到购物车成功");
            List<Good> findgoods = carService.selectGoods(Userid);
            resultData.setCode(601);
            resultData.setMsg("添加到购物车成功");
            resultData.setData(findgoods);
            send(resp,resultData);
        }
    }

    /**
     * 从购物车删除单条数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gid = req.getParameter("gid");
        String uid = req.getParameter("uid");

        Integer Userid = Integer.valueOf(uid);
        Integer Goodid = Integer.valueOf(gid);


        ResultData resultData = new ResultData();
        if ( carService.deleteGoodFromCar(Goodid,Userid)== -1){
            System.out.println("删除失败");
            resultData.setCode(602);
            resultData.setMsg("删除失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            List<Good> findgoods = carService.selectGoods(Userid);
            resultData.setCode(603);
            resultData.setMsg("删除成功");
            resultData.setData(findgoods);
            send(resp,resultData);
        }
    }

    /**
     * 查询用户的购物车数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");

        Integer Userid = Integer.valueOf(uid);


//        List<Car> findUsers = carService.selectUsers();
//        for (Car list_car:findUsers){
//            if (list_car.getUserid() == Userid){
//                System.out.println("该用户的购物车中有商品");
//            }else {
//
//            }
//        }
        List<Good> findGoods = carService.selectGoods(Userid);
        ResultData resultData = new ResultData();
        if (findGoods!=null&&findGoods.size()>0){
            System.out.println("查询成功");
            resultData.setCode(603);
            resultData.setMsg("查询成功");
            resultData.setData(findGoods);
            send(resp,resultData);
        }else {
            System.out.println("购物车中没有商品");
            resultData.setCode(604);
            resultData.setMsg("购物车中没有商品");
            resultData.setData(null);
            send(resp,resultData);
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");


        Integer Userid = Integer.valueOf(uid);

        ResultData resultData = new ResultData();
        if (carService.emptyCar(Userid) == -1){
            System.out.println("清空购物车失败");
            resultData.setCode(605);
            resultData.setMsg("清空购物车失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            System.out.println("清空购物车成功");
            resultData.setCode(606);
            resultData.setMsg("清空购物车成功");
            List<Good> findGoods = carService.selectGoods(Userid);
            if (findGoods!=null&&findGoods.size()>0){
                System.out.println("清空购物车失败");
                resultData.setCode(605);
                resultData.setMsg("清空购物车失败");
                resultData.setData(findGoods);
                send(resp,resultData);
            }else {
                System.out.println("购物车中没有商品");
                resultData.setData(null);
                send(resp,resultData);
            }

        }
    }


}
