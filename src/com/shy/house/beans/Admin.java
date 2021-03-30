package com.shy.house.beans;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    private static final long serialVersionUID = 3660910246368406504L;
    private int id;
    private String name;
    private String password;
    private Date createDate;
    private int del;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
    this.id = id;
    }

    public String getName() {
    return this.name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getPassword() {
    return this.password;
    }

    public void setPassword(String password) {
    this.password = password;
    }

    public Date getCreateDate() {
    return this.createDate;
    }

    public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    }

    public int getDel() {
    return this.del;
    }

    public void setDel(int del) {
    this.del = del;
    }
}

