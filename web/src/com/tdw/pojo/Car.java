package com.tdw.pojo;

public class Car {
    Integer Userid;
    Integer Goodid;

    public Integer getUserid() {
        return Userid;
    }

    public void setUserid(Integer userid) {
        Userid = userid;
    }

    public Integer getGoodid() {
        return Goodid;
    }

    public void setGoodid(Integer goodid) {
        Goodid = goodid;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Userid=" + Userid +
                ", Goodid=" + Goodid +
                '}';
    }

    public Car() {
    }

    public Car(Integer userid, Integer goodid) {
        Userid = userid;
        Goodid = goodid;
    }
}
