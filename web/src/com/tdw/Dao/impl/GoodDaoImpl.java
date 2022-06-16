package com.tdw.Dao.impl;

import com.tdw.Dao.GoodDao;
import com.tdw.pojo.Good;
import com.tdw.pojo.User;
import com.tdw.utils.ImgUtil;
import com.tdw.utils.JdbcUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodDaoImpl extends BaseDao implements GoodDao {
    @Override
    public List<Good> queryGoods() {
        String sql = "select * from goods where GStatus=0 order by Hot desc";
        return queryForList(Good.class,sql);
    }

    @Override
    public List<Good> queryGoodsBytype(String type) {
        String sql = "select * from goods where Gtype=?";
        return queryForList(Good.class,sql,type);
    }

    @Override
    public Good queryGoodHot(Integer Gid) {
        String sql = "select `Hot` from goods where Gid = ?";
        return queryForOne(Good.class,sql,Gid);
    }

    @Override
    public int updateGoodHot(Integer Hot,Integer Gid) {
        String sql = "update goods " + "set Hot=? where Gid=?";
        return update(sql,Hot,Gid);
    }

    @Override
    public Good queryGoodByGid(Integer Gid) {
        String sql = "select * from goods where Gid=?";
        return queryForOne(Good.class,sql,Gid);
    }

    @Override
    public Good queryGoodByGname(String gname) {
        String sql = "select `Gid` from goods where Gname=?";
        return queryForOne(Good.class,sql,gname);
    }

    @Override
    public List<Good> queryGoodByUserid(Integer Userid) {
        String sql = "select `Gid`,`Userid`,`Gname`,`Gtype`,`Ginformation`,`Gprice`,`GStatus`,`Payuserid`,`Gcoverpic`,`Gpic`,`Gpic2`,`Gpic3`,`Hot` from goods where Userid=?";
        return queryForList(Good.class,sql,Userid);
    }

    @Override
    public Good queryPayuserid(Integer Gid) {
        String sql = "select `Payuserid` from goods where Gid=?";
        return queryForOne(Good.class,sql,Gid);
    }

    @Override
    public User queryPayuser(Integer Payuserid) {
        String sql = "select `username`,`phone` from user where id=?";
        return queryForOne(User.class,sql,Payuserid);
    }


    //    @Override
//    public String queryGoodcoverpic(Integer Userid,String targetPath) {
//        String path = "";
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = JdbcUtils.getConnection();
//            String sql = "select * from goods where Userid =?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, Userid);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                InputStream in = rs.getBinaryStream("Gcoverpic");
//                path = ImgUtil.readBin2Image(in, targetPath);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(conn);
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return path;
//    }
//
//    @Override
//    public String queryGoodpic(Integer Userid,String targetPath) {
//        String path = "";
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = JdbcUtils.getConnection();
//            String sql = "select * from goods where Userid =?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, Userid);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                InputStream in = rs.getBinaryStream("Gpic");
//                path = ImgUtil.readBin2Image(in, targetPath);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(conn);
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return path;
//    }
//
//    @Override
//    public String queryGoodpic2(Integer Userid,String targetPath) {
//        String path = "";
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = JdbcUtils.getConnection();
//            String sql = "select * from goods where Userid =?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, Userid);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                InputStream in = rs.getBinaryStream("Gpic2");
//                path = ImgUtil.readBin2Image(in, targetPath);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(conn);
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return path;
//    }
//
//    @Override
//    public String queryGoodpic3(Integer Userid,String targetPath) {
//        String path = "";
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = JdbcUtils.getConnection();
//            String sql = "select * from goods where Userid =?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, Userid);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                InputStream in = rs.getBinaryStream("Gpic3");
//                path = ImgUtil.readBin2Image(in, targetPath);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(conn);
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return path;
//    }

    @Override
    public int saveGood(Good good) {
        String sql = "insert into goods(`Userid`,`Gname`,`Gtype`,`Ginformation`,`Gprice`,`GStatus`,`Payuserid`,`Gcoverpic`,`Gpic`,`Gpic2`,`Gpic3`,`Hot`) values(?,?,?,?,?,?,?,?,?,?,?,?)";

//        try (Connection connection = JdbcUtils.getConnection()) {
//            System.out.println(connection);
//            //以字节形式读取文件
//            FileInputStream fileInputStream = new FileInputStream(path);
//            FileInputStream fileInputStream1 = new FileInputStream(path1);
//            FileInputStream fileInputStream2 = new FileInputStream(path2);
//            FileInputStream fileInputStream3 = new FileInputStream(path3);
//            good.setGStatus(0);
//            good.setPayuserid(0);
//            //preparedStatement通过向数据库发送要执行的sql语句
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            for(int i=0;i<4;i++){
//                if (i==0){
//                    //为给定的参数索引，设置不同类型的值
//                    preparedStatement.setInt(1,good.getUserid());
//                    preparedStatement.setString(2,good.getGname());
//                    preparedStatement.setString(3,good.getGinformation());
//                    preparedStatement.setBigDecimal(4,good.getGprice());
//                    preparedStatement.setInt(5,good.getGStatus());
//                    preparedStatement.setInt(6,good.getPayuserid());
//                    preparedStatement.setBinaryStream(7, fileInputStream, fileInputStream.available());
//                    System.out.println("封面图片录入数据库成功");
//                }
//                if (i==1){
//                    preparedStatement.setBinaryStream(8, fileInputStream1, fileInputStream1.available());
//                    System.out.println("商品图片1录入数据库成功");
//                }
//                if (i==2){
//                    preparedStatement.setBinaryStream(9, fileInputStream2, fileInputStream2.available());
//                    System.out.println("商品图片2数据库成功");
//
//                }
//                if (i==3){
//                    preparedStatement.setBinaryStream(10, fileInputStream3, fileInputStream3.available());
//                    System.out.println("商品图片3数据库成功");
//
//                }
//            }
//
//
//            //executeUpdate方法用于执行增删改和DDL语句，返回结果为影响的行数
//            if (preparedStatement.executeUpdate() > 0) {
//                System.out.println("插入成功！");
//            }
//            else {
//                System.out.println("插入失败！");
//            }
//            try {
//                connection.close();
//                preparedStatement.close();
//            } catch (SQLException e) {
//                System.out.println("关闭连接失败！");
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 1;
        return update(sql,good.getUserid(),good.getGname(),good.getGtype(),good.getGinformation(),good.getGprice(),good.getGStatus(),good.getPayuserid(),good.getGcoverpic(),good.getGpic(),good.getGpic2(),good.getGpic3(),good.getHot());
    }

//    @Override
//    public int saveGoodpic(Good good, String path) {
//        String sql = "insert into goods(`Gpic`) values(?)";
//        try (Connection connection = JdbcUtils.getConnection()) {
//            System.out.println(connection);
//            //以字节形式读取文件
//            FileInputStream fileInputStream = new FileInputStream(path);
//
//            //preparedStatement通过向数据库发送要执行的sql语句
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            //为给定的参数索引，设置不同类型的值
//            preparedStatement.setBinaryStream(1, fileInputStream, fileInputStream.available());
//
//            //executeUpdate方法用于执行增删改和DDL语句，返回结果为影响的行数
//            if (preparedStatement.executeUpdate() > 0) {
//                System.out.println("插入成功！");
//            }
//            else {
//                System.out.println("插入失败！");
//            }
//            try {
//                connection.close();
//                preparedStatement.close();
//            } catch (SQLException e) {
//                System.out.println("关闭连接失败！");
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 1;
//    }
//
//    @Override
//    public int saveGoodpic2(Good good, String path) {
//        String sql = "insert into goods(`Gpic2`) values(?)";
//        try (Connection connection = JdbcUtils.getConnection()) {
//            System.out.println(connection);
//            //以字节形式读取文件
//            FileInputStream fileInputStream = new FileInputStream(path);
//
//            //preparedStatement通过向数据库发送要执行的sql语句
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            //为给定的参数索引，设置不同类型的值
//            preparedStatement.setBinaryStream(1, fileInputStream, fileInputStream.available());
//
//            //executeUpdate方法用于执行增删改和DDL语句，返回结果为影响的行数
//            if (preparedStatement.executeUpdate() > 0) {
//                System.out.println("插入成功！");
//            }
//            else {
//                System.out.println("插入失败！");
//            }
//            try {
//                connection.close();
//                preparedStatement.close();
//            } catch (SQLException e) {
//                System.out.println("关闭连接失败！");
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 1;
//    }
//
//    @Override
//    public int saveGoodpic3(Good good, String path) {
//        String sql = "insert into goods(`Gpic3`) values(?)";
//        try (Connection connection = JdbcUtils.getConnection()) {
//            System.out.println(connection);
//            //以字节形式读取文件
//            FileInputStream fileInputStream = new FileInputStream(path);
//
//            //preparedStatement通过向数据库发送要执行的sql语句
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            //为给定的参数索引，设置不同类型的值
//            preparedStatement.setBinaryStream(1, fileInputStream, fileInputStream.available());
//
//            //executeUpdate方法用于执行增删改和DDL语句，返回结果为影响的行数
//            if (preparedStatement.executeUpdate() > 0) {
//                System.out.println("插入成功！");
//            }
//            else {
//                System.out.println("插入失败！");
//            }
//            try {
//                connection.close();
//                preparedStatement.close();
//            } catch (SQLException e) {
//                System.out.println("关闭连接失败！");
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 1;
//    }

    @Override
    public int updateGoodname(Good good) {
        String sql = "update goods " + "set Gname=? where Gid=?";
        return update(sql,good.getGname(),good.getGid());
    }

    @Override
    public int updateGoodcoverpic(Good good) {
        String sql = "update goods " + "set Gcoverpic=? where Gid=?";
//        try (Connection connection = JdbcUtils.getConnection()) {
//            System.out.println(connection);
//            //以字节形式读取文件
//            FileInputStream fileInputStream = new FileInputStream(path);
//
//            //preparedStatement通过向数据库发送要执行的sql语句
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            //为给定的参数索引，设置不同类型的值
//            preparedStatement.setInt(2,good.getGid());
//            preparedStatement.setBinaryStream(1, fileInputStream, fileInputStream.available());
//
//            //executeUpdate方法用于执行增删改和DDL语句，返回结果为影响的行数
//            if (preparedStatement.executeUpdate() > 0) {
//                System.out.println("插入成功！");
//            }
//            else {
//                System.out.println("插入失败！");
//            }
//            try {
//                connection.close();
//                preparedStatement.close();
//            } catch (SQLException e) {
//                System.out.println("关闭连接失败！");
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 1;
        return update(sql,good.getGcoverpic(),good.getGid());
    }

    @Override
    public int updateGoodpic(Good good) {
        String sql = "update goods " + "set Gpic=?,Gpic2=?,Gpic3=? where Gid=?";
        return update(sql,good.getGpic(),good.getGpic2(),good.getGpic3(),good.getGid());
    }

    @Override
    public int updateGoodinformation(Good good) {
        String sql = "update goods " + "set Ginformation=? where Gid=?";
        return update(sql,good.getGinformation(),good.getGid());
    }

    @Override
    public int updateGoodprice(Good good) {
        String sql = "update goods " + "set Gprice=? where Gid=?";
        return update(sql,good.getGprice(),good.getGid());
    }

    @Override
    public int updayeGoodstatus(Integer Status, Integer PayUserid,Integer Gid) {
        String sql = "update goods " + "set GStatus=?,Payuserid=? where Gid=?";
        return update(sql,Status,PayUserid,Gid);
    }

    @Override
    public int deleteGood(Integer gid) {
        String sql = "delete from goods where Gid=?";
        return update(sql,gid);
    }
}
