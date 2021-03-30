/*     */ package com.shy.house.dao.impl;
/*     */ 
/*     */ import com.shy.house.beans.Message;
/*     */ import com.shy.house.beans.UserAndMessage;
/*     */ import com.shy.house.dao.MessageDao;
/*     */ import com.shy.house.utils.DBUtil;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MessageDaoImpl
/*     */   implements MessageDao
/*     */ {
/*     */   public boolean save(Message message)
/*     */   {
/*  27 */     Connection conn = null;
/*  28 */     PreparedStatement psmt = null;
/*     */ 
/*  30 */     boolean result = false;
/*  31 */     SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
/*  32 */     String dateString = dateFm.format(new Date());
/*     */     try {
/*  34 */       conn = DBUtil.getInstance().getConnection();
/*     */ 
/*  36 */       String sql = "insert into t_message(content, userId, create_date) values(?, ?, ?)";
/*  37 */       psmt = conn.prepareStatement(sql);
/*  38 */       psmt.setString(1, message.getContent());
/*  39 */       psmt.setInt(2, message.getUserId());
/*  40 */       psmt.setString(3, dateString);
/*     */ 
/*  42 */       int i = psmt.executeUpdate();
/*  43 */       if (i > 0)
/*  44 */         result = true;
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  48 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  53 */         DBUtil.closeStatement(psmt);
/*  54 */         DBUtil.closeConnection(conn);
/*     */       } catch (SQLException e1) {
/*  56 */         e1.printStackTrace();
/*     */       }
/*     */     }
/*     */     catch (ClassNotFoundException e)
/*     */     {
/*  50 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  53 */         DBUtil.closeStatement(psmt);
/*  54 */         DBUtil.closeConnection(conn);
/*     */       } catch (SQLException e1) {
/*  56 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  53 */         DBUtil.closeStatement(psmt);
/*  54 */         DBUtil.closeConnection(conn);
/*     */       } catch (SQLException e) {
/*  56 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  59 */     return result;
/*     */   }
/*     */ 
/*     */   public List<UserAndMessage> getMessageList()
/*     */   {
/*  68 */     Connection conn = null;
/*  69 */     PreparedStatement psmt = null;
/*  70 */     ResultSet rs = null;
/*     */ 
/*  72 */     List userAndMessages = new ArrayList();
/*     */     try {
/*  74 */       conn = DBUtil.getInstance().getConnection();
/*  75 */       String sql = "select m.id, m.content,m.create_date,u.name,u.name from t_message m LEFT JOIN t_user u ON m.userId=u.id where m.del=0 ORDER BY m.create_date DESC";
/*  76 */       psmt = conn.prepareStatement(sql);
/*  77 */       rs = psmt.executeQuery();
/*  78 */       while (rs.next()) {
/*  79 */         int id = Integer.parseInt(rs.getString("id"));
/*  80 */         String content = rs.getString("content");
/*  81 */         String createDate = rs.getString("create_date");
/*  82 */         String userName = rs.getString("name");
/*  83 */         UserAndMessage userAndMessage = new UserAndMessage();
/*  84 */         userAndMessage.setId(id);
/*  85 */         userAndMessage.setContent(content);
/*  86 */         userAndMessage.setCreateDate(createDate);
/*  87 */         userAndMessage.setUserName(userName);
/*  88 */         userAndMessages.add(userAndMessage);
/*     */       }
/*     */     } catch (SQLException e) {
/*  91 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  97 */         DBUtil.closeResultSet(rs);
/*  98 */         DBUtil.closeStatement(psmt);
/*  99 */         DBUtil.closeConnection(conn);
/*     */       } catch (SQLException e1) {
/* 101 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     catch (ClassNotFoundException e)
/*     */     {
/*  93 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  97 */         DBUtil.closeResultSet(rs);
/*  98 */         DBUtil.closeStatement(psmt);
/*  99 */         DBUtil.closeConnection(conn);
/*     */       } catch (SQLException e1) {
/* 101 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  97 */         DBUtil.closeResultSet(rs);
/*  98 */         DBUtil.closeStatement(psmt);
/*  99 */         DBUtil.closeConnection(conn);
/*     */       } catch (SQLException e) {
/* 101 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 105 */     return userAndMessages;
/*     */   }
/*     */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.dao.impl.MessageDaoImpl
 * JD-Core Version:    0.6.0
 */