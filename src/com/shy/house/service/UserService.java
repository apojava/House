/*    */ package com.shy.house.service;
/*    */ 
/*    */ import com.shy.house.beans.User;
/*    */ import com.shy.house.dao.UserDao;
/*    */ import com.shy.house.dao.impl.UserDaoImpl;
/*    */ 
/*    */ public class UserService
/*    */ {
/*    */   private UserDao userDao;
/*    */ 
/*    */   public UserService()
/*    */   {
/* 12 */     this.userDao = new UserDaoImpl();
/*    */   }
/*    */ 
/*    */   public User login(String userName, String pwd)
/*    */   {
/* 22 */     return this.userDao.findUser(userName, pwd);
/*    */   }
/*    */ 
/*    */   public boolean save(User user)
/*    */   {
/* 31 */     return this.userDao.save(user);
/*    */   }
/*    */ 
/*    */   public User get(int uid) {
/* 35 */     return this.userDao.get(uid);
/*    */   }
/*    */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.service.UserService
 * JD-Core Version:    0.6.0
 */