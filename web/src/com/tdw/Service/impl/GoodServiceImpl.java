package com.tdw.Service.impl;

import com.tdw.Dao.GoodDao;
import com.tdw.Dao.impl.GoodDaoImpl;
import com.tdw.Service.GoodService;
import com.tdw.pojo.Good;
import com.tdw.pojo.User;

import java.util.List;

public class GoodServiceImpl implements GoodService {

    private GoodDao goodDao = new GoodDaoImpl();

    @Override
    public int insertGood(Good good) {
        return goodDao.saveGood(good);
    }

//    @Override
//    public int insertGoodpic(Good good, String path) {
//        return goodDao.saveGoodpic(good, path);
//    }
//
//    @Override
//    public int insertGoodpic2(Good good, String path) {
//        return goodDao.saveGoodpic2(good, path);
//    }
//
//    @Override
//    public int insertGoodpic3(Good good, String path) {
//        return goodDao.saveGoodpic3(good, path);
//    }

    @Override
    public int updateGoodname(Good good) {
        return goodDao.updateGoodname(good);
    }

    @Override
    public int updateGoodcoverpic(Good good) {
        return goodDao.updateGoodcoverpic(good);
    }

    @Override
    public int updateGoodpic(Good good) {
        return goodDao.updateGoodpic(good);
    }

    @Override
    public int updateGoodinformation(Good good) {
        return goodDao.updateGoodinformation(good);
    }

    @Override
    public int updateGoodprice(Good good) {
        return goodDao.updateGoodprice(good);
    }

    @Override
    public int updateGoodstatus(Integer Status, Integer PayUserid, Integer Gid) {
        return goodDao.updayeGoodstatus(Status,PayUserid,Gid);
    }

    @Override
    public int deleteGood(Integer gid) {
        return goodDao.deleteGood(gid);
    }

    @Override
    public List<Good> selectGoods() {
        return goodDao.queryGoods();
    }

    @Override
    public List<Good> selectGoodsByUserid(Integer uid) {
        return goodDao.queryGoodByUserid(uid);
    }

    @Override
    public Good selectGpayuserid(Integer Gid) {
        return goodDao.queryPayuserid(Gid);
    }

    @Override
    public List<Good> selectGoodsBytype(String type) {
        return goodDao.queryGoodsBytype(type);
    }

    @Override
    public Good selectGoodHot(Integer Gid) {
        return goodDao.queryGoodHot(Gid);
    }

    @Override
    public int updateGoodHot(Integer Hot, Integer Gid) {
        return goodDao.updateGoodHot(Hot,Gid);
    }

    //    @Override
//    public String selectGoodcoverpic(Integer Userid, String targetPath) {
//        return goodDao.queryGoodcoverpic(Userid,targetPath);
//    }
//
//    @Override
//    public String slectGoodpic(Integer Userid, String targetPath) {
//        return goodDao.queryGoodpic(Userid,targetPath);
//    }
//
//    @Override
//    public String slectGoodpic2(Integer Userid, String targetPath) {
//        return goodDao.queryGoodpic2(Userid,targetPath);
//    }
//
//    @Override
//    public String slectGoodpic3(Integer Userid, String targetPath) {
//        return goodDao.queryGoodpic3(Userid,targetPath);
//    }

    @Override
    public Good selectGidByGname(String gname) {
        return goodDao.queryGoodByGname(gname);
    }

    @Override
    public Good selectGoodByGid(Integer gid) {
        return goodDao.queryGoodByGid(gid);
    }
}
