package com.shy.house.dao.impl;

import com.shy.house.beans.User;
import com.shy.house.dao.UserDao;
import com.shy.house.utils.DBUtil;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class UserDaoImpl implements UserDao {
    public boolean save(User user) {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFm.format(new java.util.Date());

        Connection conn = null;
        PreparedStatement psmt = null;

        boolean flag = false;
        int result = -1;
        try {
          conn = DBUtil.getInstance().getConnection();

          String sql = "insert into t_user (name,password,real_name,img_path,mobile,email,create_date) values (?,?,?,?,?,?,?)";

          psmt = conn.prepareStatement(sql);

          psmt.setString(1, user.getName());
          psmt.setString(2, user.getPassword());
          psmt.setString(3, user.getRealName());
          psmt.setString(4, user.getImgPath());
          psmt.setString(5, user.getMobile());
          psmt.setString(6, user.getEmail());
          psmt.setString(7, dateString);

          result = psmt.executeUpdate();
          if (result > 0)
            flag = true;
        }
        catch (SQLException e) {
          e.printStackTrace();
          try
          {
            DBUtil.closeStatement(psmt);
            DBUtil.closeConnection(conn);
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
        }
        catch (ClassNotFoundException e)
        {
          e.printStackTrace();
          try
          {
            DBUtil.closeStatement(psmt);
            DBUtil.closeConnection(conn);
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
        }
        finally
        {
          try
          {
            DBUtil.closeStatement(psmt);
            DBUtil.closeConnection(conn);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
        return flag;
    }

    public User findUser(String userName, String pwd) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try
        {
              conn = DBUtil.getInstance().getConnection();
              String sql = "select * from t_user where name=? and password=? and del=0";
              psmt = conn.prepareStatement(sql);
              psmt.setString(1, userName);
              psmt.setString(2, pwd);

              rs = psmt.executeQuery();

              User user = null;

              if (rs.next()) {
                System.out.println("find");
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setImgPath(rs.getString("img_path"));
                user.setCreateDate(rs.getDate("create_date").toString());
                user.setRealName(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
              }

              User localUser1 = user;
              return localUser1;
        }
        catch (ClassNotFoundException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          try {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(psmt);
            DBUtil.closeConnection(conn);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
        return null;
    }

    public boolean isExist(String userName)
    {
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;

    boolean flag = false;
    try {
      conn = DBUtil.getInstance().getConnection();

      String sql = "select id from t_user where name = ? and del = 0";
      System.out.println("验证同名： " + sql);
      psmt = conn.prepareStatement(sql);
      psmt.setString(1, userName);

      rs = psmt.executeQuery();
      if (rs.next())
        flag = true;
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
      try
      {
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(psmt);
        DBUtil.closeConnection(conn);
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      try
      {
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(psmt);
        DBUtil.closeConnection(conn);
      } catch (SQLException e1) {
        e.printStackTrace();
      }
    }
    finally
    {
      try
      {
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(psmt);
        DBUtil.closeConnection(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    System.out.println("验证： " + flag);
    return flag;
    }

    public static void main(String[] args)
    {
    System.out.println(new UserDaoImpl().isExist(""));
    }

    public User get(int uid)
    {
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    try
    {
      conn = DBUtil.getInstance().getConnection();
      String sql = "select * from t_user where id=? and del=0";
      psmt = conn.prepareStatement(sql);
      psmt.setInt(1, uid);

      rs = psmt.executeQuery();

      User user = null;

      if (rs.next()) {
        System.out.println("find");
        user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setMobile(rs.getString("mobile"));
        user.setImgPath(rs.getString("img_path"));
        user.setCreateDate(rs.getDate("create_date").toString());
        user.setRealName(rs.getString("real_name"));
        user.setType(rs.getInt("type"));
      }

      User localUser1 = user;
      return localUser1;
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(psmt);
        DBUtil.closeConnection(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
    }
}

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.dao.impl.UserDaoImpl
 * JD-Core Version:    0.6.0
 */