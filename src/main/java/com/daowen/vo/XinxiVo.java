package com.daowen.vo;

import java.util.Date;

public class XinxiVo {

    private int id;
    private String title;
    private String content;
    private Date pubtime;
    private int clickcount;
    private String typename;
    private String typeimg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    public int getClickcount() {
        return clickcount;
    }

    public void setClickcount(int clickcount) {
        this.clickcount = clickcount;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(String typeimg) {
        this.typeimg = typeimg;
    }
}