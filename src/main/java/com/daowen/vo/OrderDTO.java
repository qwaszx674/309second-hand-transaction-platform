package com.daowen.vo;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private int id;
    private String ddno;
    private double totalfee;
    private int purchaser;
    private int publisher;
    private Date createtime;
    private int state;
    private List<OrderItemDTO> items;
    private String purchaserName;
    private String publisherName;
    private String purchaserMobile;
    private String publisherMobile;

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

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPurchaserMobile() {
        return purchaserMobile;
    }

    public void setPurchaserMobile(String purchaserMobile) {
        this.purchaserMobile = purchaserMobile;
    }

    public String getPublisherMobile() {
        return publisherMobile;
    }

    public void setPublisherMobile(String publisherMobile) {
        this.publisherMobile = publisherMobile;
    }
}