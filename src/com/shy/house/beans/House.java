package com.shy.house.beans;

import java.io.Serializable;

public class House implements Serializable {
      private static final long serialVersionUID = -1905402830138393292L;
      private int id;
      private String name;
      private String address;
      private double price;
      private double size;
      private String description;
      private int userId;
      private String path;
      private String type;
      private int saleRent;
      private int isCheck;
      private String createDate;
      private int del;

      public House()
      {
        this.id = 0;
        this.name = "";
        this.address = "";
        this.price = 0.0D;
        this.size = 0.0D;
        this.description = "";
        this.userId = 0;
        this.path = "";
        this.type = "";
        this.saleRent = 0;
        this.isCheck = 0;
        this.createDate = "";
        this.del = 0;
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
      public String getAddress() {
        return this.address;
      }
      public void setAddress(String address) {
        this.address = address;
      }
      public double getPrice() {
        return this.price;
      }
      public void setPrice(double price) {
        this.price = price;
      }
      public double getSize() {
        return this.size;
      }
      public void setSize(double size) {
        this.size = size;
      }
      public String getDescription() {
        return this.description;
      }
      public void setDescription(String description) {
        this.description = description;
      }
      public int getUserId() {
        return this.userId;
      }
      public void setUserId(int userId) {
        this.userId = userId;
      }
      public String getPath() {
        return this.path;
      }
      public void setPath(String path) {
        this.path = path;
      }
      public String getType() {
        return this.type;
      }
      public void setType(String type) {
        this.type = type;
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
      public int getSaleRent() {
        return this.saleRent;
      }
      public void setSaleRent(int saleRent) {
        this.saleRent = saleRent;
      }
      public int getIsCheck() {
        return this.isCheck;
      }
      public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
      }
}

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.beans.House
 * JD-Core Version:    0.6.0
 */