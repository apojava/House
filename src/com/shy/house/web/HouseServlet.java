/*     */ package com.shy.house.web;
/*     */ 
/*     */ import com.shy.house.beans.House;
/*     */ import com.shy.house.beans.User;
/*     */ import com.shy.house.dao.HouseDao;
/*     */ import com.shy.house.dao.impl.HouseDaoImpl;
/*     */ import com.shy.house.service.HouseService;
/*     */ import com.shy.house.service.UserService;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
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
/*     */ public class HouseServlet extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  51 */     request.setCharacterEncoding("utf-8");
/*  52 */     response.setContentType("text/html;charset=utf-8");
/*  53 */     String method = request.getParameter("method");
/*  54 */     if ("toCreateHouse".equals(method))
/*  55 */       request.getRequestDispatcher("pages/createHouse.jsp")
/*  56 */         .forward(request, response);
/*  57 */     else if ("personHouse".equals(method))
/*  58 */       personHouse(request, response);
/*  59 */     else if ("houseDetail".equals(method))
/*  60 */       houseDetail(request, response);
/*  61 */     else if ("toHouseCheck".equals(method))
/*  62 */       toHouseCheck(request, response);
/*  63 */     else if ("check".equals(method))
/*  64 */       check(request, response);
/*  65 */     else if ("toAdvanceSearch".equals(method))
/*  66 */       request.getRequestDispatcher("pages/advanceSearch.jsp")
/*  67 */         .forward(request, response);
/*  68 */     else if ("delHouse".equals(method))
/*  69 */       delHouse(request, response);
/*     */     else
/*  71 */       System.out.println("HouseServlet doGet 未匹配到方法");
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  81 */     request.setCharacterEncoding("utf-8");
/*  82 */     response.setContentType("text/html;charset=utf-8");
/*     */ 
/*  84 */     String method = request.getParameter("method");
/*  85 */     if ("createHouse".equals(method))
/*  86 */       createHouse(request, response);
/*  87 */     else if ("searchHouse".equals(method))
/*  88 */       search(request, response);
/*  89 */     else if ("advanceSearch".equals(method))
/*  90 */       advanceSearch(request, response);
/*     */     else
/*  92 */       System.out.println("HouseServlet doPost 未匹配到方法");
/*     */   }
/*     */ 
/*     */   private void createHouse(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 105 */     System.out.println("createHouse: ");
/*     */ 
/* 107 */     request.setCharacterEncoding("UTF-8");
/*     */ 
/* 111 */     HttpSession session = null;
/*     */ 
/* 113 */     session = request.getSession();
/* 114 */     User user = (User)session.getAttribute("user");
/*     */ 
/* 117 */     if (user == null) {
/* 118 */       response.sendRedirect("userServlet?method=toLogin");
/* 119 */       return;
/*     */     }
/*     */ 
/* 123 */     FileItemFactory fac = new DiskFileItemFactory();
/* 124 */     ServletFileUpload fileUpload = new ServletFileUpload(fac);
/* 125 */     fileUpload.setFileSizeMax(20971520L);
/* 126 */     fileUpload.setHeaderEncoding("utf-8");
/*     */ 
/* 128 */     House house = new House();
/*     */ 
/* 130 */     house.setUserId(user.getId());
/*     */ 
/* 132 */     String filePath = getServletContext().getRealPath("images");
/*     */ 
/* 135 */     List fileList = null;
/*     */     try {
/* 137 */       fileList = fileUpload.parseRequest(request);
/*     */     } catch (FileUploadException e) {
/* 139 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 143 */     Iterator iterator = fileList.iterator();
/* 144 */     while (iterator.hasNext())
/*     */     {
/* 146 */       FileItem fileItem = (FileItem)iterator.next();
/*     */ 
/* 148 */       if (fileItem.isFormField())
/*     */       {
/* 150 */         String fieldName = fileItem.getFieldName();
/* 151 */         String content = fileItem.getString("utf-8");
/* 152 */         if ((content == null) || (content.trim() == "")) continue;
/*     */         try {
/* 154 */           BeanUtils.copyProperty(house, fieldName, content);
/*     */         } catch (IllegalAccessException e) {
/* 156 */           e.printStackTrace();
/*     */         } catch (InvocationTargetException e) {
/* 158 */           e.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 164 */         String filename = fileItem.getName();
/*     */ 
/* 167 */         int index = filename.lastIndexOf(".");
/* 168 */         String extention = filename.substring(index + 1);
/* 169 */         String uploadFileName = System.currentTimeMillis() + "0." + 
/* 170 */           extention;
/*     */ 
/* 173 */         File file = new File(filePath + "/" + uploadFileName);
/* 174 */         house.setPath("images/" + uploadFileName);
/*     */         try
/*     */         {
/* 177 */           fileItem.write(file);
/* 178 */           System.out.println("新增房屋finish");
/*     */         } catch (Exception e) {
/* 180 */           e.printStackTrace();
/*     */         }
/*     */ 
/* 183 */         System.out.println("文件地址：" + filePath + "\\" + uploadFileName + 
/* 184 */           "\n\n\n\n\n\n\n\n");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 189 */     HouseService hs = new HouseService();
/* 190 */     boolean flag = hs.save(house);
/*     */ 
/* 192 */     if (flag) {
/* 193 */       System.out.println("flag baocun true");
/* 194 */       response.sendRedirect("userServlet?method=toUserCenter");
/*     */     } else {
/* 196 */       request.setAttribute("err", "注册失败");
/* 197 */       request.getRequestDispatcher("").forward(request, response);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void personHouse(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 211 */     System.out.println("HosueServlet personHouse");
/* 212 */     HttpSession session = request.getSession();
/* 213 */     User user = (User)session.getAttribute("user");
/*     */ 
/* 215 */     HouseService houseService = new HouseService();
/*     */ 
/* 217 */     List houses = houseService.getListByUid(user.getId());
/* 218 */     System.out.println("houses.size(): " + houses.size());
/* 219 */     request.setAttribute("houses", houses);
/* 220 */     request.getRequestDispatcher("pages/personHouse.jsp").forward(
/* 221 */       request, response);
/*     */   }
/*     */ 
/*     */   private void search(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 233 */     String searchWords = request.getParameter("name");
/* 234 */     System.out.println("搜索 Action 搜索词: " + searchWords);
/*     */ 
/* 236 */     HouseService hs = new HouseService();
/* 237 */     List houseList = hs.searchHouseList(searchWords);
/* 238 */     request.setAttribute("list1", houseList);
/* 239 */     request.getRequestDispatcher("pages/index.jsp").forward(request,
/* 240 */       response);
/*     */   }
/*     */ 
/*     */   private void houseDetail(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 253 */     System.out.println("hosueDetail servlet");
/* 254 */     int houseId = Integer.parseInt(request.getParameter("id"));
/* 255 */     HouseService hs = new HouseService();
/* 256 */     House house = hs.houseDetail(houseId);
/* 257 */     request.setAttribute("house", house);
/*     */ 
/* 260 */     int userId = house.getUserId();
/* 261 */     List houses = hs.getListChkedByUid(userId);
/* 262 */     request.setAttribute("houses", houses);
/*     */ 
/* 266 */     UserService us = new UserService();
/* 267 */     User user = us.get(userId);
/* 268 */     request.setAttribute("houseUser", user);
/*     */ 
/* 270 */     request.getRequestDispatcher("pages/houseDetail.jsp").forward(
/* 271 */       request, response);
/*     */   }
/*     */ 
/*     */   private void toHouseCheck(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 284 */     HouseService hs = new HouseService();
/* 285 */     int chkState = 0;
/* 286 */     List userAndHouses = hs.findListByChkState(0);
/* 287 */     request.setAttribute("userAndHouses", userAndHouses);
/* 288 */     request.getRequestDispatcher("pages/adminCheckHouse.jsp")
/* 289 */       .forward(request, response);
/*     */   }
/*     */ 
/*     */   public void check(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 302 */     int id = Integer.parseInt(request.getParameter("id"));
/* 303 */     int chkState = Integer.parseInt(request.getParameter("chkState"));
/* 304 */     System.out.println("审核操作  id: " + id + " chkState: " + chkState);
/* 305 */     HouseService hs = new HouseService();
/* 306 */     hs.check(id, chkState);
/* 307 */     response.sendRedirect("houseServlet?method=toHouseCheck");
/*     */   }
/*     */ 
/*     */   private void advanceSearch(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 320 */     List houseList = new ArrayList();
/* 321 */     List list1 = new ArrayList();
/*     */ 
/* 323 */     String strPrice1 = request.getParameter("price1");
/* 324 */     String strPrice2 = request.getParameter("price2");
/* 325 */     int price1 = Integer.parseInt(strPrice1);
/* 326 */     int price2 = Integer.parseInt(strPrice2);
/*     */ 
/* 328 */     String address = request.getParameter("address");
/* 329 */     String type = request.getParameter("type");
/*     */ 
/* 331 */     HouseService hs = new HouseService();
/* 332 */     list1 = hs.advanceSearch(address, price1, price2, type);
/*     */ 
/* 361 */     request.setAttribute("list1", list1);
/* 362 */     request.getRequestDispatcher("pages/advanceSearch.jsp").forward(
/* 363 */       request, response);
/*     */   }
/*     */ 
/*     */   private void delHouse(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, ServletException
/*     */   {
/* 369 */     int id = Integer.parseInt(request.getParameter("id"));
/*     */ 
/* 371 */     HouseDao houseDao = new HouseDaoImpl();
/* 372 */     boolean flag = houseDao.del(id);
/*     */ 
/* 374 */     if (flag)
/* 375 */       System.out.println("success");
/*     */     else {
/* 377 */       System.out.println("fail");
/*     */     }
/* 379 */     response.sendRedirect("houseServlet?method=personHouse");
/*     */   }
/*     */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.web.HouseServlet
 * JD-Core Version:    0.6.0
 */