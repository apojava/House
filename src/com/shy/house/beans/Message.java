package com.shy.house.beans;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
  private static final long serialVersionUID = 1627595640180922729L;
  private int id;
  private String content;
  private int userId;
  private Date createDate;
  private int del;

  public int getId()
  {
    return this.id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getContent() {
    return this.content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getUserId() {
    return this.userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
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

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.beans.Message
 * JD-Core Version:    0.6.0
 */