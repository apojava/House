package com.shy.house.beans;

import java.io.Serializable;

public class User
  implements Serializable
{
  private static final long serialVersionUID = -8249124033201452184L;
  private int id;
  private String name;
  private String password;
  private String realName;
  private String imgPath;
  private String mobile;
  private String email;
  private String createDate;
  private int type;
  private int del;

  public User()
  {
    this.id = 0;
    this.name = "";
    this.password = "";
    this.realName = "";
    this.imgPath = "";
    this.mobile = "";
    this.email = "";
    this.createDate = "";
    this.type = 0;
    this.del = 0;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

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
  public String getRealName() {
    return this.realName;
  }
  public void setRealName(String realName) {
    this.realName = realName;
  }
  public String getImgPath() {
    return this.imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public String getMobile() {
    return this.mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public String getEmail() {
    return this.email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getCreateDate() {
    return this.createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public int getDel() {
    return this.del;
  }
  public void setDel(int del) {
    this.del = del;
  }
}

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.beans.User
 * JD-Core Version:    0.6.0
 */