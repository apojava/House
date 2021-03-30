/*     */ package com.shy.house.beans;
/*     */ 
/*     */ public class UserHouseVO
/*     */ {
/*     */   private int houseId;
/*     */   private String houseName;
/*     */   private String address;
/*     */   private double size;
/*     */   private String type;
/*     */   private String userName;
/*     */   private String mobile;
/*     */   private int isCheck;
/*     */   private String createDate;
/*     */ 
/*     */   public UserHouseVO()
/*     */   {
/*  20 */     this.houseId = 0;
/*  21 */     this.houseName = "";
/*  22 */     this.address = "";
/*  23 */     this.size = 0.0D;
/*  24 */     this.type = "";
/*  25 */     this.userName = "";
/*  26 */     this.mobile = "";
/*  27 */     this.createDate = "";
/*  28 */     this.isCheck = 0;
/*     */   }
/*     */ 
/*     */   public int getIsCheck() {
/*  32 */     return this.isCheck;
/*     */   }
/*     */ 
/*     */   public void setIsCheck(int isCheck) {
/*  36 */     this.isCheck = isCheck;
/*     */   }
/*     */ 
/*     */   public int getHouseId() {
/*  40 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(int houseId) {
/*  44 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public String getHouseName() {
/*  48 */     return this.houseName;
/*     */   }
/*     */ 
/*     */   public void setHouseName(String houseName) {
/*  52 */     this.houseName = houseName;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/*  56 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/*  60 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getMobile() {
/*  64 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile) {
/*  68 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   public String getCreateDate() {
/*  72 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(String createDate) {
/*  76 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getAddress() {
/*  80 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/*  84 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public double getSize() {
/*  88 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setSize(double size) {
/*  92 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public String getType() {
/*  96 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/* 100 */     this.type = type;
/*     */   }
/*     */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.beans.UserHouseVO
 * JD-Core Version:    0.6.0
 */