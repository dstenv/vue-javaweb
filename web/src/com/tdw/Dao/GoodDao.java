package com.tdw.Dao;

import com.tdw.pojo.Good;
import com.tdw.pojo.User;

import java.util.List;

public interface GoodDao {

    /**
     * 查找所有商品
     */
    public List<Good> queryGoods();

    /**
     * 根据商品类型查询商品
     * @param type
     * @return
     */
    public List<Good> queryGoodsBytype(String type);

    /**
     * 查询商品热度
     * @param Gid
     * @return
     */
    public Good queryGoodHot(Integer Gid);

    /**
     * 修改商品热度
     * @param Hot
     * @return
     */
    public int updateGoodHot(Integer Hot,Integer Gid);

    /**
     * 根据商品id查询商品信息
     * @param Gid
     * @return 如果返回null，则说明没有这个商品
     */
    public Good queryGoodByGid(Integer Gid);

    /**
     * 根据商品名查找商品id
     * @param gname
     * @return
     */
    public Good queryGoodByGname(String gname);


    /**
     * 根据用户id查找发布的物品
     * @param Userid
     * @return
     */
    public List<Good> queryGoodByUserid(Integer Userid);

    /**
     * 查询付款用户id
     * @param Gid
     * @return
     */
    public Good queryPayuserid(Integer Gid);

    /**
     * 根据付款用户id 查询付款用户信息
     * @return
     */
    public User queryPayuser(Integer Payuserid);

//    /**
//     * 根据用户id查到商品封面图片
//     * @param Userid
//     * @return
//     */
//    public String queryGoodcoverpic(Integer Userid,String targetPath);
//
//    /**
//     * 根据用户id查到商品图1
//     * @param Userid
//     * @return
//     */
//    public String queryGoodpic(Integer Userid,String targetPath);
//
//    /**
//     * 根据用户id查到商品图2
//     * @param Userid
//     * @return
//     */
//    public String queryGoodpic2(Integer Userid,String targetPath);
//
//    /**
//     * 根据用户id查到商品图3
//     * @param Userid
//     * @return
//     */
//    public String queryGoodpic3(Integer Userid,String targetPath);


    /**
     * 保存商品信息、封面图和商品图（3张）
     * @param good
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveGood(Good good);

//    /**
//     * 保存商品图1
//     * @param good
//     * @param path
//     * @return
//     */
//    public int saveGoodpic(Good good,String path);
//
//    /**
//     * 保存商品图2
//     * @param good
//     * @param path
//     * @return
//     */
//    public int saveGoodpic2(Good good,String path);
//
//    /**
//     * 保存商品图3
//     * @param good
//     * @param path
//     * @return
//     */
//    public int saveGoodpic3(Good good,String path);

    /**
     * 修改商品名称
     * @param good
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
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
     * 修改商品状态 1代表被购买 2代表已经发货 3代表已经收货
     * @param Status
     * @param PayUserid
     * @return
     */
    public int updayeGoodstatus(Integer Status,Integer PayUserid,Integer Gid);

    /**
     *删除商品
     * @param gid
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int deleteGood(Integer gid);
}
