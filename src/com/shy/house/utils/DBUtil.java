package com.shy.house.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil
{
  private static String driver = "com.mysql.jdbc.Driver";
  private static String url = "jdbc:mysql://localhost:3306/housedb?characterEncoding=utf8&useSSL=true";
  private static String username = "root";
  private static String password = "123456";

  private static DBUtil dbUtil = null;

  public static synchronized DBUtil getInstance() {
      if (dbUtil == null) {
        return DBUtil.dbUtil = new DBUtil();
      }

      return dbUtil;
  }

  public Connection getConnection() throws ClassNotFoundException, SQLException
  {
    Class.forName(driver);
    Connection connection = DriverManager.getConnection(url, username, password);

    return connection;
  }

  public static void closeStatement(Statement st) throws SQLException
  {
    if (st != null)
      st.close();
  }

  public static void closeConnection(Connection conn) throws SQLException
  {
    if (conn != null)
      conn.close();
  }

  public static void closeResultSet(ResultSet rs) throws SQLException
  {
    if (rs != null)
      rs.close();
  }

  public static void main(String[] args)
  {
    try
    {
      System.out.println(getInstance().getConnection());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.utils.DBUtil
 * JD-Core Version:    0.6.0
 */