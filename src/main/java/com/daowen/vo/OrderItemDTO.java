package com.daowen.vo;

public class OrderItemDTO {

    private int id;
    private int orderid;
    private int spid;
    private String spname;
    private double price;
    private int quantity;
    private int state;
    private String wlno;
    private String tupian;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getWlno() {
        return wlno;
    }

    public void setWlno(String wlno) {
        this.wlno = wlno;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }
}