/*     */ package com.shy.house.service;
/*     */ 
/*     */ import com.shy.house.beans.House;
/*     */ import com.shy.house.beans.UserHouseVO;
/*     */ import com.shy.house.dao.HouseDao;
/*     */ import com.shy.house.dao.impl.HouseDaoImpl;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HouseService
/*     */ {
/*     */   private HouseDao houseDao;
/*     */ 
/*     */   public HouseService()
/*     */   {
/*  19 */     this.houseDao = new HouseDaoImpl();
/*     */   }
/*     */ 
/*     */   public boolean save(House house)
/*     */   {
/*  28 */     return this.houseDao.add(house);
/*     */   }
/*     */ 
/*     */   public List<House> getListByUid(int uid)
/*     */   {
/*  37 */     return this.houseDao.houseListByUid(uid);
/*     */   }
/*     */ 
/*     */   public List<House> getListChkedByUid(int uid)
/*     */   {
/*  46 */     List<House>  allList = this.houseDao.houseListByUid(uid);
/*  47 */     List list = new ArrayList(0);
/*  48 */     for (House h : allList) {
/*  49 */       if (h.getIsCheck() == 1) {
/*  50 */         list.add(h);
/*     */       }
/*     */     }
/*  53 */     return list;
/*     */   }
/*     */ 
/*     */   public List<House> searchHouseList(String searchWords)
/*     */   {
/*  62 */     return this.houseDao.searchHouseList(searchWords);
/*     */   }
/*     */ 
/*     */   public House houseDetail(int id)
/*     */   {
/*  72 */     return this.houseDao.findHouseById(id);
/*     */   }
/*     */ 
/*     */   public List<UserHouseVO> findListByChkState(int chkState)
/*     */   {
/*  82 */     return this.houseDao.findByChkState(chkState);
/*     */   }
/*     */ 
/*     */   public boolean check(int id, int chkState)
/*     */   {
/*  92 */     return this.houseDao.updateChkState(id, chkState);
/*     */   }
/*     */ 
/*     */   public List<House> advanceSearch(String address, int price1, int price2, String type)
/*     */   {
/* 104 */     List<House> houseList = new ArrayList();
/* 105 */     List list1 = new ArrayList();
/*     */ 
/* 108 */     houseList = this.houseDao.houseList();
/*     */ 
/* 110 */     if ((houseList != null) && (houseList.size() > 0)) {
/* 111 */       for (House h : houseList)
/*     */       {
/* 113 */         if ((h.getPrice() >= price1) && (h.getPrice() <= price2))
/*     */         {
/* 115 */           if ((h.getAddress().contains(address)) && 
/* 116 */             (h.getType().equals(type))) {
/* 117 */             System.out.println(h.getId());
/* 118 */             list1.add(h);
/*     */           } else {
/* 120 */             System.out.println("查询结果区域不匹配");
/*     */           }
/*     */         }
/* 123 */         else System.out.println("查询结果价格区间不匹配");
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 128 */       System.out.println("未查询到结果");
/*     */     }
/* 130 */     return list1;
/*     */   }
/*     */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.service.HouseService
 * JD-Core Version:    0.6.0
 */