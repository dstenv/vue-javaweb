package com.tdw.Dao.impl;

import com.tdw.Dao.CarDao;
import com.tdw.pojo.Car;
import com.tdw.pojo.Good;

import java.util.List;

public class CarDaoImpl extends BaseDao implements CarDao {

    @Override
    public int addCar(Integer Userid, Integer Goodid) {
        String sql = "insert into car(`Userid`,`Goodid`) values(?,?)";
        return update(sql,Userid,Goodid);
    }

    @Override
    public int delete(Integer Goodid,Integer Userid) {
        String sql = "delete from car where Goodid=? and Userid=?";
        return update(sql,Goodid,Userid);
    }

    @Override
    public int deleteall(Integer Userid) {
        String sql = "delete from car where Userid=?";
        return update(sql,Userid);
    }

    @Override
    public List<Good> querycarinfo(Integer Userid) {
        String sql = "select * from goods,car where car.Userid=? and car.Goodid=goods.Gid";
        return queryForList(Good.class,sql,Userid);
    }

//    @Override
//    public Good queryCarone(Integer Gid) {
//        String sql = "select * from goods where car.Userid=?";
//        return null;
//    }


    @Override
    public List<Car> queryUser() {
        String sql = "select `Userid` from car";
        return queryForList(Car.class,sql);
    }
}
