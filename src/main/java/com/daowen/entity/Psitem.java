package com.daowen.entity;

import javax.persistence.*;

@Entity
public class Psitem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int pagesettingid;
    private String key;
    private String value;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPagesettingid() {
        return pagesettingid;
    }

    public void setPagesettingid(int pagesettingid) {
        this.pagesettingid = pagesettingid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}