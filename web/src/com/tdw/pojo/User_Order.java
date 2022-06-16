package com.tdw.pojo;

import java.math.BigDecimal;

public class User_Order {
    private Integer Orderid;
    private Integer Userid;
    private String Oaddress;
    private BigDecimal Price;
    private Integer Ostatus;

    public Integer getOrderid() {
        return Orderid;
    }

    public void setOrderid(Integer orderid) {
        Orderid = orderid;
    }

    public Integer getUserid() {
        return Userid;
    }

    public void setUserid(Integer userid) {
        Userid = userid;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public String getOaddress() {
        return Oaddress;
    }

    public void setOaddress(String oaddress) {
        Oaddress = oaddress;
    }

    public Integer getOstatus() {
        return Ostatus;
    }

    public void setOstatus(Integer ostatus) {
        Ostatus = ostatus;
    }

    @Override
    public String toString() {
        return "User_Order{" +
                "Orderid=" + Orderid +
                ", Userid=" + Userid +
                ", Oaddress='" + Oaddress + '\'' +
                ", Price=" + Price +
                ", Ostatus=" + Ostatus +
                '}';
    }

    public User_Order() {
    }

    public User_Order(Integer orderid, Integer userid, String oaddress, BigDecimal price, Integer ostatus) {
        Orderid = orderid;
        Userid = userid;
        Oaddress = oaddress;
        Price = price;
        Ostatus = ostatus;
    }
}
