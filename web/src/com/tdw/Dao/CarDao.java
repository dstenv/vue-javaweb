package com.tdw.Dao;

import com.tdw.pojo.Car;
import com.tdw.pojo.Good;

import java.util.List;

public interface CarDao {

    /**
     * 添加商品到购物车
     * @param Userid
     * @param Goodid
     * @return
     */
    public int addCar(Integer Userid,Integer Goodid);

    /**
     * 购物车删除商品
     * @param Goodid
     * @return
     */
    public int delete(Integer Goodid,Integer Userid);

    /**
     * 查询购物车信息
     * @param Userid
     * @return
     */
    public List<Good> querycarinfo(Integer Userid);

    /**
     * 清空购物车
     * @param Userid
     * @return
     */
    public int deleteall(Integer Userid);

    /**
     * 查找购物车中的用户
     * @return
     */
    public List<Car> queryUser();

//    /**
//     * 查询购物车中的一条数据
//     * @param Gid
//     * @return
//     */
//    public Good queryCarone(Integer Gid);

    //从购物车购物商品 对order表操作


}
