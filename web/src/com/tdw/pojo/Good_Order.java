package com.tdw.pojo;

public class Good_Order {

    private Integer Goodid;
    private Integer Orderid;

    public Integer getGoodid() {
        return Goodid;
    }

    public void setGoodid(Integer goodid) {
        Goodid = goodid;
    }

    public Integer getOrderid() {
        return Orderid;
    }

    public void setOrderid(Integer orderid) {
        Orderid = orderid;
    }


    @Override
    public String toString() {
        return "Good_Order{" +
                "Goodid=" + Goodid +
                ", Orderid=" + Orderid +
                '}';
    }

    public Good_Order() {
    }

    public Good_Order(Integer goodid, Integer orderid) {
        Goodid = goodid;
        Orderid = orderid;
    }
}
