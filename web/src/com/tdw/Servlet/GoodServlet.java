package com.tdw.Servlet;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonParser;
import com.tdw.pojo.User;
import com.tdw.pojo.sendGood;
import com.tdw.pojo.ResultData;
import com.tdw.utils.Base64Util;
import com.tdw.Service.GoodService;
import com.tdw.Service.UserService;
import com.tdw.Service.impl.GoodServiceImpl;
import com.tdw.Service.impl.UserServiceImpl;
import com.tdw.pojo.Good;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/goodServlet")
public class GoodServlet extends BaseServlet{
//    //图片序号
//    Integer gpno = 1;
//    //图片名
//    String gpicname = "good" + gpno;
    private GoodService goodService = new GoodServiceImpl();
    private UserService userService = new UserServiceImpl();
    private Base64Util base64to = new Base64Util();

    /**
     * 报存商品信息、封面图片和商品图片（3张） --目前是存放的图片路径，不太完善
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void savegood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");
        String gname = req.getParameter("gname");
        String ginformation = req.getParameter("ginformation");
        String gprice = req.getParameter("gprice");
        String base64 = req.getParameter("gcoverpic");
        String gpic = req.getParameter("gpic");
        String gpic2 = req.getParameter("gpic2");
        String gpic3 = req.getParameter("gpic3");
        String gtype = req.getParameter("gtype");
        System.out.println("base64:" + base64);
        System.out.println("gpic:" + gpic);
        Integer userid = Integer.valueOf(uid);

        ResultData resultData = new ResultData();
        // 将string类型的价格转换为BigDecimal
        BigDecimal Gprice = new BigDecimal(gprice);
        //System.out.println("1111");

//        //字符串切割 去掉base64头部
//        String[] gcoverpic=base64.split(",");
//        System.out.println(gcoverpic);

        //获取项目的跟路径
        String realPath = "E:\\vue+javaweb\\vue\\second_hand\\public\\dataImg";
        System.out.println("realPath = "+realPath);

        //获取封面图片base64编码的文件类型
        //String imgTypes = gcoverpic[0].substring(11, gcoverpic[0].toString().length()-7);
        String imgTypes = base64.substring(base64.indexOf("/") + 1, base64.indexOf(";"));
        System.out.println("图片类型为:" + imgTypes);

        //获取pic base64编码的文件类型
        String imgTypes1 = imgTypes;
        //获取pic2 base64编码的文件类型
        String imgTypes2 = imgTypes;
        //获取pic2 base64编码的文件类型
        String imgTypes3 = imgTypes;


        //调用方法生成文件并获取文件名
        String goodcoverpicname = base64to.base64ToFile(base64,realPath,imgTypes);
        System.out.println(goodcoverpicname);
        String goodpicname = base64to.base64ToFile(gpic,realPath,imgTypes1);
        String goodpic2name = base64to.base64ToFile(gpic2,realPath,imgTypes2);
        String goodpic3name = base64to.base64ToFile(gpic3,realPath,imgTypes3);


        //获取文件存放路径(包括文件名)
        String goodcoverpicpath = realPath + "\\" + goodcoverpicname;
        System.out.println(goodcoverpicpath);
        String goodpicpath = realPath + "\\" + goodpicname;
        System.out.println(goodpicpath);
        String goodpic2path = realPath + "\\" + goodpic2name;
        String goodpic3path = realPath + "\\" + goodpic3name;

        if (goodService.insertGood(new Good(null,userid,gname,gtype,ginformation,Gprice,0,0,goodcoverpicname,goodpicname,goodpic2name,goodpic3name,0)) == -1){
            System.out.println("发布失败");
            resultData.setCode(500);
            resultData.setMsg("发布失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            Good findgoodid = goodService.selectGidByGname(gname);
            Good findgood = goodService.selectGoodByGid(findgoodid.getGid());

//            if (findgood.getGprice() == null){
//                System.out.println("price为空");
//            }else {
//                System.out.println("price不为空");
//                System.out.println(findgood.getGprice());
//            }
            //System.out.println(findgood);
            resultData.setCode(501);
            resultData.setMsg("发布成功");
            resultData.setData(findgood);
            send(resp,resultData);
        }


    }

    /**
     * 通过物品id删除物品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deletegood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");
        String gid = req.getParameter("gid");

        Integer userid = Integer.valueOf(uid);
        Integer Gid = Integer.valueOf(gid);
        System.out.println(Gid);
        ResultData resultData = new ResultData();

        Good findgood = goodService.selectGoodByGid(Gid);
        System.out.println(findgood);
        if (findgood.getGStatus() != 0 ){
            System.out.println("删除失败,物品已被购买");
            List<Good> goodList = goodService.selectGoodsByUserid(userid);
            resultData.setCode(502);
            resultData.setMsg("删除失败,物品已被购买");
            resultData.setData(goodList);
            send(resp,resultData);
        }else {
            if(goodService.deleteGood(Gid) == -1){
                System.out.println("删除失败");
                resultData.setCode(502);
                resultData.setMsg("删除失败");
                send(resp,resultData);
            }else {
                List<Good> goodList = goodService.selectGoodsByUserid(userid);
                System.out.println("删除成功");
                resultData.setCode(503);
                resultData.setMsg("删除成功");
                resultData.setData(goodList);
                send(resp,resultData);
            }

        }


    }


    /**
     * 通过用户id查找用户发布的物品()
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectgoodsByuserid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("uid");
        ResultData resultData = new ResultData();

        Integer userid = Integer.valueOf(uid);


        List<Good> goodList = goodService.selectGoodsByUserid(userid);
//        //循环遍历goodlist 判断商品是否被购买，如果是 则在list中添加一个存放购买人信息的对象
//        for (Good list_good : goodList){
//            if (list_good.getGStatus() == 1){
//                Good findGpayuid = goodService.selectGpayuserid(list_good.getGid());
//                User findpayuser = userService.findUserByuserid(findGpayuid.getPayuserid());
//                goodList.add(findGpayuid);
//            }
//        }
        if (goodList!=null&&goodList.size()>0){
            System.out.println(goodList);
            resultData.setCode(503);
            resultData.setMsg("查找成功");
            resultData.setData(goodList);
            send(resp,resultData);
        }else {
            System.out.println("您未发布商品！");
            resultData.setCode(504);
            resultData.setMsg("您未发布商品！");
            send(resp,resultData);
        }

    }

    /**
     * 修改商品名称
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updategoodname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        String oldgname = req.getParameter("oldgname");
        String gname = req.getParameter("gname");
        String gid = req.getParameter("gid");
        Integer goodid= Integer.valueOf(gid);
        ResultData resultData = new ResultData();
        //Good goodid = goodService.selectGidByGname(oldgname);
        if (goodService.updateGoodname(new Good(goodid,null,gname,"","",null,null,null,"","","","",null)) ==-1){
            System.out.println("修改商品名称失败");
            resultData.setCode(505);
            resultData.setMsg("修改商品名称失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            Good newgood = goodService.selectGoodByGid(goodid);
            resultData.setCode(506);
            resultData.setMsg("修改商品名称成功");
            resultData.setData(newgood);
            send(resp,resultData);
        }
    }

    /**
     * 修改商品介绍
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updategInfomation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gid = req.getParameter("gid");
        String ginformation = req.getParameter("ginformation");
        Integer goodid= Integer.valueOf(gid);

        ResultData resultData = new ResultData();
        if (goodService.updateGoodinformation(new Good(goodid,null,"","",ginformation,null,null,null,"","","","",null)) ==-1){
            System.out.println("修改商品介绍失败");
            resultData.setCode(507);
            resultData.setMsg("修改商品介绍失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            Good newgood = goodService.selectGoodByGid(goodid);
            resultData.setCode(508);
            resultData.setMsg("修改商品介绍成功");
            resultData.setData(newgood);
            send(resp,resultData);
        }

    }

    /**
     * 修改商品价格
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updategprice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gid = req.getParameter("gid");
        String gprice = req.getParameter("gprice");
        Integer goodid= Integer.valueOf(gid);

        BigDecimal Gprice = new BigDecimal(gprice);
        ResultData resultData = new ResultData();
        if (goodService.updateGoodprice(new Good(goodid,null,"","","",Gprice,null,null,"","","","",null)) ==-1){
            System.out.println("修改商品价格失败");
            resultData.setCode(509);
            resultData.setMsg("修改商品价格失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            Good newgood = goodService.selectGoodByGid(goodid);
            resultData.setCode(510);
            resultData.setMsg("修改商品价格成功");
            resultData.setData(newgood);
            send(resp,resultData);
        }
    }

    /**
     * 修改商品封面图片
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updategcoverpic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gname = req.getParameter("gname");
        String gid = req.getParameter("gid");
        String base64 = req.getParameter("gcoverpic");


        ResultData resultData = new ResultData();

        Integer Gid = Integer.valueOf(gid);

        //获取项目的跟路径
        String realPath = "E:\\vue+javaweb\\vue\\second_hand\\public\\dataImg";
        System.out.println("realPath = "+realPath);

        //获取封面图片base64编码的文件类型
        //String imgTypes = gcoverpic[0].substring(11, gcoverpic[0].toString().length()-7);
        String imgTypes = base64.substring(base64.indexOf("/") + 1, base64.indexOf(";"));
        System.out.println("图片类型为:" + imgTypes);
        //调用方法生成文件并获取文件名
        String goodcoverpicname = base64to.base64ToFile(base64,realPath,imgTypes);
        //获取文件存放路径(包括文件名)
        String goodcoverpicpath = realPath + "\\" + goodcoverpicname;

        if (goodService.updateGoodcoverpic(new Good(Gid,null,"","","",null,null,null,goodcoverpicname,"","","",null)) == -1){
            System.out.println("修改商品封面图片失败");
            resultData.setCode(511);
            resultData.setMsg("修改商品封面图片失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            System.out.println("修改商品封面图片成功");
            Good sendGood = goodService.selectGoodByGid(Gid);
            resultData.setCode(512);
            resultData.setMsg("修改商品封面图片成功");
            resultData.setData(sendGood);
            send(resp,resultData);
        }

    }

    /**
     * 修改商品图片(3张)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updatepic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gname = req.getParameter("gname");
        String gid = req.getParameter("gid");
        String gpicbase64 = req.getParameter("gpic");
        String gpic2base64 = req.getParameter("gpic2");
        String gpic3base64 = req.getParameter("gpic3");

        ResultData resultData = new ResultData();

        Integer Gid = Integer.valueOf(gid);

        //获取项目的跟路径
        String realPath = "E:\\vue+javaweb\\vue\\second_hand\\public\\dataImg";
        System.out.println("realPath = "+realPath);

        //获取封面图片base64编码的文件类型
        //String imgTypes = gcoverpic[0].substring(11, gcoverpic[0].toString().length()-7);
        String imgTypes = gpicbase64.substring(gpicbase64.indexOf("/") + 1, gpicbase64.indexOf(";"));
        System.out.println("图片类型为:" + imgTypes);
        //调用方法生成文件并获取文件名
        String goodpicname = base64to.base64ToFile(gpicbase64,realPath,imgTypes);
        String goodpic2name = base64to.base64ToFile(gpic2base64,realPath,imgTypes);
        String goodpic3name = base64to.base64ToFile(gpic3base64,realPath,imgTypes);

        //获取文件存放路径(包括文件名)
        String goodpicpath = realPath + "\\" + goodpicname;
        String goodpic2path = realPath + "\\" + goodpic2name;
        String goodpic3path = realPath + "\\" + goodpic3name;

        //存储文件名 存储地址为vue的public文件夹 前端利用字符串拼接地址 绝对定位取出图片
        if (goodService.updateGoodpic(new Good(Gid,null,"","","",null,null,null,"",goodpicname,goodpic2name,goodpic3name,null)) == -1){
            System.out.println("修改商品图片失败");
            resultData.setCode(513);
            resultData.setMsg("修改商品图片失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            System.out.println("修改商品图片成功");
            Good sendGood = goodService.selectGoodByGid(Gid);
            resultData.setCode(514);
            resultData.setMsg("修改商品图片成功");
            resultData.setData(sendGood);

            send(resp,resultData);
        }

    }

//    /**
//     * 修改商品状态，用户购买商品的时候处理
//     * @param req
//     * @param resp
//     * @throws ServletException
//     * @throws IOException
//     */
//    protected void resolveorder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        String gname = req.getParameter("gname");
//        String gid = req.getParameter("gid");
//        String payuserid = req.getParameter("userid");
//
//        ResultData resultData = new ResultData();
//        Integer Payuserid = Integer.valueOf(payuserid);
//        Integer Gid = Integer.valueOf(gid);
//
//        if (goodService.updateGoodstatus(1,Payuserid,Gid) == -1){
//            System.out.println("购买商品失败");
//            resultData.setCode(515);
//            resultData.setMsg("购买商品失败");
//            resultData.setData(null);
//            send(resp,resultData);
//        }else {
//            System.out.println("购买商品成功");
//            //返回购买的商品给前端
//            Good sendGood = goodService.selectGoodByGid(Gid);
//
//            resultData.setCode(516);
//            resultData.setMsg("购买商品成功");
//            resultData.setData(sendGood);
//            send(resp,resultData);
//        }
//    }

    /**
     * 查询所有商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected  void selectGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ResultData resultData = new ResultData();
        List<Good> goodList = goodService.selectGoods();
        List<Good> satisfygoodList = new ArrayList<>();
        if (goodList != null&&goodList.size()>0){
            for (Good list_good : goodList){
                if (list_good.getGStatus() == 0||list_good.getGStatus()==null){
                    System.out.println(list_good.getGcoverpic());
                    satisfygoodList.add(list_good);
                }
            }
            resultData.setCode(517);
            resultData.setMsg("查询成功");
            resultData.setData(satisfygoodList);
            send(resp,resultData);
        }else {
            resultData.setCode(518);
            resultData.setMsg("无商品!");
            send(resp,resultData);
        }
    }

    /**
     * 查找物品详情信息 当用户查看某个商品的详细信息时，让这个商品的热度值加1
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectgoodDetil(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gid = req.getParameter("gid");

        Integer Goodid = Integer.valueOf(gid);
        ResultData resultData = new ResultData();

        //查找物品的热度
        Good findGood = goodService.selectGoodHot(Goodid);
        Integer goodhot = findGood.getHot() + 1;
        System.out.println(goodhot);
        //修改物品的热度
        if ( goodService.updateGoodHot(goodhot,Goodid)== -1){
            System.out.println("修改物品热度失败");
            resultData.setCode(519);
            resultData.setMsg("修改物品热度失败");
            resultData.setData(null);
            send(resp,resultData);

        }else {
            System.out.println("修改物品热度成功");
//            Good findGoodHot = goodService.selectGoodHot(Goodid);
//            resultData.setData(findGoodHot);
//            send(resp,resultData);
        }
        Good goodDetil = goodService.selectGoodByGid(Goodid);
        User finduser = userService.findUserByuserid(goodDetil.getUserid());
        List<Object> arrayLists = new ArrayList<>();
        arrayLists.add(goodDetil);
        arrayLists.add(finduser);
        if (goodDetil == null){
            resultData.setCode(520);
            resultData.setMsg("查询失败");
            resultData.setData(null);
            send(resp,resultData);

        }else {
            resultData.setCode(521);
            resultData.setMsg("查询成功");
            resultData.setData(goodDetil);
            send(resp,arrayLists);
        }

    }

    /**
     * 根据商品分类查询商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectGoodsBytype(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gtype = req.getParameter("gtype");

        System.out.println("选择的类型为：" + gtype);
        ResultData resultData = new ResultData();
        List<Good> goodList = goodService.selectGoodsBytype(gtype);
        //System.out.println(goodList.size());
        List<Good> satisfygoodList = new ArrayList<>();
        if (goodList != null&&goodList.size()>0){
            for (Good list_good : goodList){
                if (list_good.getGStatus() == 0||list_good.getGStatus()==null){
                    satisfygoodList.add(list_good);
                }
            }
            resultData.setCode(522);
            resultData.setMsg("查询成功");
            resultData.setData(satisfygoodList);
            send(resp,resultData);
        }else {
            resultData.setCode(523);
            resultData.setMsg("无所选类型商品!");
            send(resp,resultData);
        }
    }

    /**
     * 降低商品热度
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void reducehot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gid = req.getParameter("gid");

        Integer Goodid = Integer.valueOf(gid);
        ResultData resultData = new ResultData();

        //查找物品的热度
        Good findGood = goodService.selectGoodHot(Goodid);
        Integer goodhot = findGood.getHot() - 2;
        if ( goodService.updateGoodHot(goodhot,Goodid)== -1){
            System.out.println("降低物品热度失败");
            resultData.setCode(522);
            resultData.setMsg("降低物品热度失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            System.out.println("降低物品热度成功");
            Good Good = goodService.selectGoodHot(Goodid);
            resultData.setCode(523);
            resultData.setMsg("降低物品热度成功");
            resultData.setData(Good);
            send(resp,resultData);

        }
    }

    /**
     * 增加商品热度
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void increasehot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String gid = req.getParameter("gid");

        Integer Goodid = Integer.valueOf(gid);
        ResultData resultData = new ResultData();

        //查找物品的热度
        Good findGood = goodService.selectGoodHot(Goodid);
        Integer goodhot = findGood.getHot();
        if ( goodService.updateGoodHot(goodhot,Goodid)== -1){
            System.out.println("增加物品热度失败");
            resultData.setCode(524);
            resultData.setMsg("增加物品热度失败");
            resultData.setData(null);
            send(resp,resultData);
        }else {
            System.out.println("增加物品热度成功");
            Good Good = goodService.selectGoodHot(Goodid);
            resultData.setCode(525);
            resultData.setMsg("增加物品热度成功");
            resultData.setData(Good);
            send(resp,resultData);

        }
    }

    /**
     * 测试接收数组
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收字符串 把传送过来的数组当成字符串接收
        String id = req.getParameter("id");
        System.out.println(id);
        //将字符串转换为字符数组 逗号字符串切割
        String[] gid = id.split(",");

        //新定义一个int数组 将字符数组的值赋给int数组
        int[] id1 = new int[gid.length];
        int i;
        for (i = 0; i < gid.length; i++) {
            int number = Integer.parseInt(gid[i].trim());
            id1[i] = number;
            System.out.println(id1[i]);
        }
    }

}


