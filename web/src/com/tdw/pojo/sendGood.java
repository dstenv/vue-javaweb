package com.tdw.pojo;

import java.math.BigDecimal;

public class sendGood {
    private Integer Gid;
    private Integer Userid;
    private String Gname;
    private String Ginformation;
    private BigDecimal price;
    private Integer GStatus;
    private Integer Payuserid;
    private String Gcoverpicpath;
    private String Gpicpath;
    private String Gpic2path;
    private String Gpic3path;

    public sendGood() {
    }

    public Integer getGid() {
        return Gid;
    }

    public void setGid(Integer gid) {
        this.Gid = gid;
    }

    public Integer getUserid() {
        return Userid;
    }

    public void setUserid(Integer userid) {
        this.Userid = userid;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        this.Gname = gname;
    }

    public String getGinformation() {
        return Ginformation;
    }

    public void setGinformation(String ginformation) {
        this.Ginformation = ginformation;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getGStatus() {
        return GStatus;
    }

    public void setGStatus(Integer GStatus) {
        this.GStatus = GStatus;
    }

    public Integer getPayuserid() {
        return Payuserid;
    }

    public void setPayuserid(Integer payuserid) {
        this.Payuserid = payuserid;
    }

    public String getGcoverpicpath() {
        return Gcoverpicpath;
    }

    public void setGcoverpicpath(String gcoverpicpath) {
        this.Gcoverpicpath = gcoverpicpath;
    }

    public String getGpicpath() {
        return Gpicpath;
    }

    public void setGpicpath(String gpicpath) {
        this.Gpicpath = gpicpath;
    }

    public String getGpic2path() {
        return Gpic2path;
    }

    public void setGpic2path(String gpic2path) {
        this.Gpic2path = gpic2path;
    }

    public String getGpic3path() {
        return Gpic3path;
    }

    public void setGpic3path(String gpic3path) {
        this.Gpic3path = gpic3path;
    }

    @Override
    public String toString() {
        return "sendGood{" +
                "Gid=" + Gid +
                ", Userid=" + Userid +
                ", Gname='" + Gname + '\'' +
                ", Ginformation='" + Ginformation + '\'' +
                ", price=" + price +
                ", GStatus=" + GStatus +
                ", Payuserid=" + Payuserid +
                ", Gcoverpicpath='" + Gcoverpicpath + '\'' +
                ", Gpicpath='" + Gpicpath + '\'' +
                ", Gpic2path='" + Gpic2path + '\'' +
                ", Gpic3path='" + Gpic3path + '\'' +
                '}';
    }


    public sendGood(Integer gid, Integer userid, String gname, String ginformation, BigDecimal price, Integer GStatus, Integer payuserid, String gcoverpicpath, String gpicpath, String gpic2path, String gpic3path) {
        this.Gid = gid;
        this.Userid = userid;
        this.Gname = gname;
        this.Ginformation = ginformation;
        this.price = price;
        this.GStatus = GStatus;
        this.Payuserid = payuserid;
        this.Gcoverpicpath = gcoverpicpath;
        this.Gpicpath = gpicpath;
        this.Gpic2path = gpic2path;
        this.Gpic3path = gpic3path;
    }
}
