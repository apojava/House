/*    */ package com.shy.house.web;
/*    */ 
/*    */ import com.shy.house.beans.Message;
/*    */ import com.shy.house.beans.User;
/*    */ import com.shy.house.dao.MessageDao;
/*    */ import com.shy.house.dao.impl.MessageDaoImpl;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.List;
/*    */ import javax.servlet.RequestDispatcher;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ public class MessageServlet extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 38 */     doPost(request, response);
/*    */   }
/*    */ 
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 47 */     request.setCharacterEncoding("utf-8");
/* 48 */     response.setContentType("text/html;charset=utf-8");
/* 49 */     response.setCharacterEncoding("utf-8");
/* 50 */     String method = request.getParameter("method");
/* 51 */     if (method.equals("saveMessage"))
/* 52 */       saveMessage(request, response);
/* 53 */     else if (method.equals("getMessageList"))
/* 54 */       getMessageList(request, response);
/*    */   }
/*    */ 
/*    */   private void getMessageList(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 60 */     MessageDao messageDao = new MessageDaoImpl();
/* 61 */     List userAndMessages = messageDao.getMessageList();
/* 62 */     request.setAttribute("userAndMessages", userAndMessages);
/* 63 */     request.getRequestDispatcher("pages/message.jsp").forward(
/* 64 */       request, response);
/*    */   }
/*    */ 
/*    */   private void saveMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*    */   {
/* 69 */     MessageDao messageDao = new MessageDaoImpl();
/* 70 */     String content = request.getParameter("content");
/* 71 */     if ((content != "") || (content != null)) {
/* 72 */       HttpSession session = request.getSession();
/* 73 */       User user = (User)session.getAttribute("user");
/* 74 */       if (user != null)
/*    */       {
/* 76 */         int userId = user.getId();
/* 77 */         Message message = new Message();
/* 78 */         message.setContent(content);
/* 79 */         message.setUserId(userId);
/*    */ 
/* 82 */         boolean result = messageDao.save(message);
/* 83 */         if (result)
/* 84 */           getMessageList(request, response);
/*    */       }
/*    */       else {
/* 87 */         PrintWriter out = response.getWriter();
/*    */         try {
/* 89 */           out.write("<script>alert('请先登录！');</script>");
/*    */         } catch (Exception e) {
/* 91 */           e.printStackTrace();
/*    */         } finally {
/* 93 */           out.flush();
/* 94 */           out.close();
/*    */         }
/* 96 */         response.sendRedirect("userServlet?method=toLogin");
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.web.MessageServlet
 * JD-Core Version:    0.6.0
 */