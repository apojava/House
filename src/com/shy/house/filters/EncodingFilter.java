/*    */ package com.shy.house.filters;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ 
/*    */ public class EncodingFilter
/*    */   implements Filter
/*    */ {
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 37 */     request.setCharacterEncoding("UTF-8");
/*    */ 
/* 41 */     chain.doFilter(request, response);
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig fConfig)
/*    */     throws ServletException
/*    */   {
/*    */   }
/*    */ }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.filters.EncodingFilter
 * JD-Core Version:    0.6.0
 */