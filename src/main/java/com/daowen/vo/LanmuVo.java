package com.daowen.vo;

import java.util.List;

public class LanmuVo {

    private int id;
    private String name;
    private String imgurl;
    private int parentid;
    private String description;
    private int sort;
    private List<LanmuVo> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<LanmuVo> getChildren() {
        return children;
    }

    public void setChildren(List<LanmuVo> children) {
        this.children = children;
    }
}