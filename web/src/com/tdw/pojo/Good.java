package com.tdw.pojo;

import java.math.BigDecimal;
import java.sql.Blob;

public class Good {
    private Integer Gid;
    private Integer Userid;
    private String Gname;
    private String Ginformation;
    private BigDecimal Gprice;
    //private String Gaddress;
    private Integer GStatus;
    private Integer Payuserid;
    private String Gcoverpic;
    private String Gpic;
    private String Gpic2;
    private String Gpic3;
    private String Gtype;
    private Integer Hot;

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

    public BigDecimal getGprice() {
        return Gprice;
    }

    public void setGprice(BigDecimal Gprice) {
        this.Gprice = Gprice;
    }

//    public String getGaddress() {
//        return Gaddress;
//    }
//
//    public void setGaddress(String gaddress) {
//        this.Gaddress = gaddress;
//    }

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

    public String getGcoverpic() {
        return Gcoverpic;
    }

    public void setGcoverpic(String gcoverpic) {
        this.Gcoverpic = gcoverpic;
    }

    public String getGpic() {
        return Gpic;
    }

    public void setGpic(String gpic) {
        this.Gpic = gpic;
    }

    public String getGpic2() {
        return Gpic2;
    }

    public void setGpic2(String gpic2) {
        this.Gpic2 = gpic2;
    }

    public String getGpic3() {
        return Gpic3;
    }

    public void setGpic3(String gpic3) {
        this.Gpic3 = gpic3;
    }

    public String getGtype() {
        return Gtype;
    }

    public void setGtype(String gtype) {
        Gtype = gtype;
    }

    public Integer getHot() {
        return Hot;
    }

    public void setHot(Integer hot) {
        Hot = hot;
    }

    @Override
    public String toString() {
        return "Good{" +
                "Gid=" + Gid +
                ", Userid=" + Userid +
                ", Gname='" + Gname + '\'' +
                ", Ginformation='" + Ginformation + '\'' +
                ", Gprice=" + Gprice +
                ", GStatus=" + GStatus +
                ", Payuserid=" + Payuserid +
                ", Gcoverpic='" + Gcoverpic + '\'' +
                ", Gpic='" + Gpic + '\'' +
                ", Gpic2='" + Gpic2 + '\'' +
                ", Gpic3='" + Gpic3 + '\'' +
                ", Gtype='" + Gtype + '\'' +
                ", Hot=" + Hot +
                '}';
    }

    public Good() {
    }



    public Good(Integer gid, Integer userid, String gname,String gtype, String ginformation, BigDecimal gprice, Integer GStatus, Integer payuserid, String gcoverpic, String gpic, String gpic2, String gpic3, Integer hot) {
        Gid = gid;
        Userid = userid;
        Gname = gname;
        Gtype = gtype;
        Ginformation = ginformation;
        Gprice = gprice;
        this.GStatus = GStatus;
        Payuserid = payuserid;
        Gcoverpic = gcoverpic;
        Gpic = gpic;
        Gpic2 = gpic2;
        Gpic3 = gpic3;
        Hot = hot;
    }

}
