/*     */ package com.shy.house.web;
/*     */ 
/*     */ import com.shy.house.beans.User;
/*     */ import com.shy.house.dao.UserDao;
/*     */ import com.shy.house.dao.impl.UserDaoImpl;
/*     */ import com.shy.house.service.UserService;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.RequestDispatcher;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ import org.apache.commons.fileupload.FileItemFactory;
/*     */ import org.apache.commons.fileupload.FileUploadException;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ 
/*     */ public class UserServlet extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  47 */     System.out.println("get");
/*  48 */     doPost(request, response);
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  57 */     System.out.println("post");
/*     */ 
/*  59 */     request.setCharacterEncoding("UTF-8");
/*  60 */     String method = request.getParameter("method");
/*     */ 
/*  62 */     if (method.equals("toLogin"))
/*  63 */       request.getRequestDispatcher("pages/login.jsp").forward(
/*  64 */         request, response);
/*  65 */     else if (method.equals("login"))
/*  66 */       login(request, response);
/*  67 */     else if (method.equals("logout"))
/*  68 */       logout(request, response);
/*  69 */     else if (method.equals("toRegister"))
/*  70 */       request.getRequestDispatcher("pages/register.jsp").forward(
/*  71 */         request, response);
/*  72 */     else if (method.equals("register"))
/*  73 */       register(request, response);
/*  74 */     else if (method.equals("toUserCenter"))
/*  75 */       toUserCenter(request, response);
/*  76 */     else if ("personInfo".equals(method))
/*  77 */       request.getRequestDispatcher("pages/personInfo.jsp").forward(
/*  78 */         request, response);
/*  79 */     else if ("toAdminCenter".equals(method))
/*  80 */       toAdminCenter(request, response);
/*  81 */     else if ("registerValidater".equals(method))
/*  82 */       registerValidater(request, response);
/*     */     else
/*  84 */       System.out.println("输出 未找到");
/*     */   }
/*     */ 
/*     */   private void login(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 101 */     request.setCharacterEncoding("UTF-8");
/* 102 */     System.out.println("login 方法");
/* 103 */     String userName = request.getParameter("name");
/* 104 */     String password = request.getParameter("password");
/*     */ 
/* 106 */     System.out.println("sss: " + userName + " " + password);
/*     */ 
/* 108 */     UserService us = new UserService();
/* 109 */     User user = us.login(userName, password);
/* 110 */     if (user != null)
/*     */     {
/* 112 */       HttpSession session = request.getSession();
/* 113 */       session.setAttribute("user", user);
/* 114 */       if (user.getType() == 0)
/* 115 */         response.sendRedirect("userServlet?method=toUserCenter");
/* 116 */       else if (user.getType() == 1) {
/* 117 */         response.sendRedirect("userServlet?method=toAdminCenter");
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 123 */       request.setAttribute("err", "用户名或密码有误");
/* 124 */       request.getRequestDispatcher("userServlet?method=toLogin").forward(
/* 125 */         request, response);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void toUserCenter(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 140 */     request.setCharacterEncoding("UTF-8");
/* 141 */     System.out.println("跳转到用户中心页面toUserCenter");
/*     */ 
/* 143 */     HttpSession session = null;
/*     */ 
/* 145 */     session = request.getSession();
/* 146 */     User user = (User)session.getAttribute("user");
/*     */ 
/* 149 */     if (user == null) {
/* 150 */       System.out.println(1);
/* 151 */       response.sendRedirect("userServlet?method=toLogin");
/*     */     } else {
/* 153 */       System.out.println(2);
/*     */ 
/* 155 */       request.getRequestDispatcher("pages/person.jsp").forward(
/* 156 */         request, response);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void toAdminCenter(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 168 */     System.out.println("跳转到用户中心页面toUserCenter");
/*     */ 
/* 170 */     HttpSession session = null;
/*     */ 
/* 172 */     session = request.getSession();
/* 173 */     User user = (User)session.getAttribute("user");
/*     */ 
/* 176 */     if (user == null) {
/* 177 */       System.out.println(1);
/* 178 */       response.sendRedirect("userServlet?method=toLogin");
/*     */     } else {
/* 180 */       System.out.println(2);
/*     */ 
/* 182 */       request.getRequestDispatcher("pages/admin.jsp").forward(
/* 183 */         request, response);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void logout(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 198 */     HttpSession session = null;
/*     */ 
/* 200 */     session = request.getSession();
/*     */ 
/* 202 */     session.removeAttribute("user");
/*     */ 
/* 204 */     response.sendRedirect("userServlet?method=toLogin");
/*     */   }
/*     */ 
/*     */   private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*     */   {
/* 209 */     System.out.println("注册");
/* 210 */     request.setCharacterEncoding("UTF-8");
/*     */ 
/* 214 */     FileItemFactory fac = new DiskFileItemFactory();
/* 215 */     ServletFileUpload fileUpload = new ServletFileUpload(fac);
/* 216 */     fileUpload.setFileSizeMax(20971520L);
/* 217 */     fileUpload.setHeaderEncoding("utf-8");
/*     */ 
/* 219 */     User user = new User();
/*     */ 
/* 221 */     String filePath = getServletContext().getRealPath("images");
/*     */ 
/* 224 */     List fileList = null;
/*     */     try {
/* 226 */       fileList = fileUpload.parseRequest(request);
/*     */     } catch (FileUploadException e) {
/* 228 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 232 */     Iterator iterator = fileList.iterator();
/* 233 */     while (iterator.hasNext())
/*     */     {
/* 235 */       FileItem fileItem = (FileItem)iterator.next();
/*     */ 
/* 237 */       if (fileItem.isFormField())
/*     */       {
/* 239 */         String fieldName = fileItem.getFieldName();
/* 240 */         String content = fileItem.getString("utf-8");
/* 241 */         if ((content == null) || (content.trim() == "")) continue;
/*     */         try {
/* 243 */           BeanUtils.copyProperty(user, fieldName, content);
/*     */         } catch (IllegalAccessException e) {
/* 245 */           e.printStackTrace();
/*     */         } catch (InvocationTargetException e) {
/* 247 */           e.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 254 */         String filename = fileItem.getName();
/* 255 */         String uploadFileName = "";
/*     */ 
/* 257 */         if ((filename != null) && (filename.trim() != ""))
/*     */         {
/* 261 */           int index = filename.lastIndexOf(".");
/* 262 */           String extention = filename.substring(index + 1);
/* 263 */           uploadFileName = System.currentTimeMillis() + "0." + 
/* 264 */             extention;
/*     */         } else {
/* 266 */           uploadFileName = "avator.png";
/*     */         }
/*     */ 
/* 270 */         File file = new File(filePath + "/" + uploadFileName);
/* 271 */         user.setImgPath("images/" + uploadFileName);
/*     */         try
/*     */         {
/* 274 */           fileItem.write(file);
/* 275 */           System.out.println("注册finish");
/*     */         } catch (Exception e) {
/* 277 */           e.printStackTrace();
/*     */         }
/*     */ 
/* 280 */         System.out.println("文件地址：" + filePath + "\\" + uploadFileName + 
/* 281 */           "\n\n\n\n\n\n\n\n");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 286 */     UserService us = new UserService();
/* 287 */     boolean flag = us.save(user);
/*     */ 
/* 289 */     if (flag) {
/* 290 */       System.out.println("flag baocun true");
/* 291 */       response.sendRedirect("userServlet?method=toLogin");
/*     */     } else {
/* 293 */       request.setAttribute("err", "注册失败");
/* 294 */       request.getRequestDispatcher("").forward(request, response);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void registerValidater(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 307 */     System.out.println("Ajax 请求 验证同名用户注册");
/* 308 */     System.out.println("oo: " + request.getParameter("name"));
/* 309 */     String userName = request.getParameter("name");
/*     */ 
/* 311 */     boolean flag = false;
/* 312 */     if ((userName != null) && (!"".equals(userName))) {
/* 313 */       UserDao userDao = new UserDaoImpl();
/* 314 */       flag = userDao.isExist(userName);
/*     */     }
/*     */ 
/* 317 */     PrintWriter pw = response.getWriter();
/*     */     try {
/* 319 */       if (flag) {
/* 320 */         pw.write("fail");
/* 321 */         System.out.println("1");
/*     */       } else {
/* 323 */         pw.write("success");
/* 324 */         System.out.println("2");
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 328 */       e.printStackTrace();
/*     */     } finally {
/* 330 */       pw.flush();
/* 331 */       pw.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.web.UserServlet
 * JD-Core Version:    0.6.0
 */