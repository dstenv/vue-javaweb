package com.tdw.Service;

import com.tdw.pojo.Good;
import com.tdw.pojo.User;

import java.util.List;

public interface GoodService {

    /**
     * 添加商品
     * @param good
     * @return
     */
    public int insertGood(Good good);

//    /**
//     * 添加商品图1
//     * @param good
//     * @param path
//     * @return
//     */
//    public int insertGoodpic(Good good,String path);
//
//    /**
//     * 添加商品图2
//     * @param good
//     * @param path
//     * @return
//     */
//    public int insertGoodpic2(Good good,String path);
//
//    /**
//     * 添加商品图3
//     * @param good
//     * @param path
//     * @return
//     */
//    public int insertGoodpic3(Good good,String path);

    /**
     * 修改商品名称
     * @param good
     * @return
     */
    public int updateGoodname(Good good);


    /**
     * 修改商品封面图片
     * @param good
     * @return
     */
    public int updateGoodcoverpic(Good good);

    /**
     * 修改商品详细图片
     * @param good
     * @return
     */
    public int updateGoodpic(Good good);

    /**
     * 修改商品介绍
     * @param good
     * @return
     */
    public int updateGoodinformation(Good good);

    /**
     * 修改商品价格
     * @param good
     * @return
     */
    public int updateGoodprice(Good good);

    /**
     * 修改商品状态(当商品被购买时)
     * @param Status
     * @param PayUserid
     * @param Gid
     * @return
     */
    public int updateGoodstatus(Integer Status,Integer PayUserid,Integer Gid);

    /**
     * 删除商品
     * @param gid
     * @return
     */
    public int deleteGood(Integer gid);

    /**
     * 查找所有商品
     * @return
     */
    public List<Good> selectGoods();

    /**
     * 根据商品类型查找商品
     * @param type
     * @return
     */
    public List<Good> selectGoodsBytype(String type);

    /**
     * 查询商品热度
     * @param Gid
     * @return
     */
    public Good selectGoodHot(Integer Gid);

    /**
     * 修改商品热度
     * @param Hot
     * @param Gid
     * @return
     */
    public int updateGoodHot(Integer Hot,Integer Gid);

    /**
     * 查找用户发布的商品
     * @param uid
     * @return
     */
    public List<Good> selectGoodsByUserid(Integer uid);

    /**
     * 查询支付用户id 再通过id查询支付用户信息
     * @param Gid
     * @return
     */
    public Good selectGpayuserid(Integer Gid);


//    public String selectGoodcoverpic(Integer Userid,String targetPath);
//
//
//    public String slectGoodpic(Integer Userid,String targetPath);
//
//
//    public String slectGoodpic2(Integer Userid,String targetPath);
//
//
//    public String slectGoodpic3(Integer Userid,String targetPath);

    /**
     * 根据Gname查找 商品id
     * @param gname
     * @return
     */
    public Good selectGidByGname(String gname);

    /**
     * 根据Gid查找 展示物品详情
     * @param gid
     * @return
     */
    public Good selectGoodByGid(Integer gid);
}
