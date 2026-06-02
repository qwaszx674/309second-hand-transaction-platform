package com.daowen.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shorder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String ddno;
    private double totalfee;
    private int purchaser;
    private int publisher;
    private Date createtime;
    private int state;
    private int receaddressid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDdno() {
        return ddno;
    }

    public void setDdno(String ddno) {
        this.ddno = ddno;
    }

    public double getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(double totalfee) {
        this.totalfee = totalfee;
    }

    public int getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(int purchaser) {
        this.purchaser = purchaser;
    }

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getReceaddressid() {
        return receaddressid;
    }

    public void setReceaddressid(int receaddressid) {
        this.receaddressid = receaddressid;
    }
}