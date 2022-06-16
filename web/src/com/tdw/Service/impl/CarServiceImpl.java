package com.tdw.Service.impl;

import com.tdw.Dao.CarDao;
import com.tdw.Dao.impl.CarDaoImpl;
import com.tdw.Service.CarService;
import com.tdw.pojo.Car;
import com.tdw.pojo.Good;

import java.util.List;

public class CarServiceImpl implements CarService {
    private CarDao carDao = new CarDaoImpl();

    @Override
    public int addGoodToCar(Integer Userid, Integer Goodid) {
        return carDao.addCar(Userid,Goodid);
    }

    @Override
    public int deleteGoodFromCar(Integer Goodid,Integer Userid) {
        return carDao.delete(Goodid,Userid);
    }

    @Override
    public List<Good> selectGoods(Integer Userid) {
        return carDao.querycarinfo(Userid);
    }

    @Override
    public int emptyCar(Integer Userid) {
        return carDao.deleteall(Userid);
    }

    @Override
    public List<Car> selectUsers() {
        return carDao.queryUser();
    }
}
