package com.tdw.Service;

import com.tdw.pojo.Car;
import com.tdw.pojo.Good;

import java.util.List;

public interface CarService {

    /**
     * 添加商品到购物车
     * @param Userid
     * @param Goodid
     * @return
     */
    public int addGoodToCar(Integer Userid,Integer Goodid);

    /**
     * 从购物车删除商品
     * @param Goodid
     * @return
     */
    public int deleteGoodFromCar(Integer Goodid,Integer Userid);

    /**
     * 查询用户的购物车信息
     * @param Userid
     * @return
     */
    public List<Good> selectGoods(Integer Userid);

    /**
     * 清空购物车
     * @param Userid
     * @return
     */
    public int emptyCar(Integer Userid);

    /**
     * 查询购物车中的用户
     * @return
     */
    public List<Car> selectUsers();
}
