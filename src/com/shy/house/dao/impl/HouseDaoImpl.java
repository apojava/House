 package com.shy.house.dao.impl;

 import com.shy.house.beans.House;
 import com.shy.house.beans.UserHouseVO;
 import com.shy.house.dao.HouseDao;
 import com.shy.house.utils.DBUtil;
 import java.io.PrintStream;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;

 public class HouseDaoImpl implements HouseDao

 {
   public boolean add(House house)
   {
     SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
     String dateString = dateFm.format(new Date());

     Connection conn = null;
     PreparedStatement psmt = null;

     boolean flag = false;
     int result = -1;
     try {
       conn = DBUtil.getInstance().getConnection();

       String sql = "insert into t_house (name, address, price, size, description, userId, path, type, sale_rent, isCheck, create_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

       psmt = conn.prepareStatement(sql);

       psmt.setString(1, house.getName());
       psmt.setString(2, house.getAddress());
       psmt.setDouble(3, house.getPrice());
       psmt.setDouble(4, house.getSize());
       psmt.setString(5, house.getDescription());
       psmt.setInt(6, house.getUserId());
       psmt.setString(7, house.getPath());
       psmt.setString(8, house.getType());
       psmt.setInt(9, house.getSaleRent());
       psmt.setInt(10, house.getIsCheck());
       psmt.setString(11, dateString);

       result = psmt.executeUpdate();
       if (result > 0)
         flag = true;
     }
     catch (ClassNotFoundException e) {
       e.printStackTrace();
       try
       {
         DBUtil.closeStatement(psmt);
         DBUtil.closeConnection(conn);
       } catch (SQLException e1) {
         e.printStackTrace();
       }
     }
     catch (SQLException e)
     {
       e.printStackTrace();
       try
       {
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
         DBUtil.closeStatement(psmt);
         DBUtil.closeConnection(conn);
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return flag;
   }

   public House findHouseById(int id)
   {
     Connection conn = null;
     PreparedStatement psmt = null;
     ResultSet rs = null;
     House house = null;
     try
     {
       conn = DBUtil.getInstance().getConnection();
       String sql = "select * from t_house where id = ?";
       psmt = conn.prepareStatement(sql);
       psmt.setInt(1, id);

       rs = psmt.executeQuery();
       while (rs.next()) {
         house = new House();
         house.setAddress(rs.getString("address"));
         house.setCreateDate(rs.getString("create_date"));
         house.setDescription(rs.getString("description"));
         house.setName(rs.getString("name"));
         house.setPrice(rs.getDouble("price"));
         house.setSize(rs.getDouble("size"));
         house.setType(rs.getString("type"));
         house.setPath(rs.getString("path"));
         house.setUserId(rs.getInt("userId"));
         house.setSaleRent(rs.getInt("sale_rent"));
       }
     } catch (ClassNotFoundException e) {
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
     return house;
   }

   public boolean del(int id)
   {
     boolean flag = false;

     int result = -1;

     Connection conn = null;
     PreparedStatement psmt = null;
     try
     {
       try
       {
         conn = DBUtil.getInstance().getConnection();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }

       String sql = "update t_house set del=1 where id=?";
       System.out.println("sql: " + sql);
       psmt = conn.prepareStatement(sql);

       psmt.setInt(1, id);

       result = psmt.executeUpdate();

       if (result > 0)
         flag = true;
     }
     catch (SQLException e)
     {
       e.printStackTrace();
       try
       {
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
         DBUtil.closeStatement(psmt);
         DBUtil.closeConnection(conn);
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }

     return flag;
   }

   public List<House> houseList()
   {
     List houseList = new ArrayList();

     Connection conn = null;
     PreparedStatement psmt = null;
     ResultSet rs = null;
     try
     {
       try {
         conn = DBUtil.getInstance().getConnection();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }

       String sql = "select id,name,address,price,size,description,path,type,sale_rent,isCheck,create_date from t_house where del = 0";

       psmt = conn.prepareStatement(sql);

       rs = psmt.executeQuery();

       House house = null;

       while (rs.next()) {
         house = new House();
         house.setId(rs.getInt("id"));
         house.setName(rs.getString("name"));
         house.setAddress(rs.getString("address"));
         house.setPrice(Double.parseDouble(rs.getString("price")));
         house.setSize(Double.parseDouble(rs.getString("size")));
         house.setDescription(rs.getString("description"));
         house.setPath(rs.getString("path"));
         house.setType(rs.getString("type"));
         house.setSaleRent(rs.getInt("sale_rent"));
         house.setIsCheck(rs.getInt("isCheck"));
         house.setCreateDate(rs.getString("create_date"));

         houseList.add(house);
       }
     } catch (SQLException e) {
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
     return houseList;
   }

   public List<House> houseListByUid(int uid)
   {
     List houseList = new ArrayList();

     Connection conn = null;
     PreparedStatement psmt = null;
     ResultSet rs = null;
     try
     {
       try {
         conn = DBUtil.getInstance().getConnection();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }

       String sql = "select id,name,address,price,size,description,path,type,sale_rent,isCheck,create_date from t_house where userId = ? and del = 0";

       psmt = conn.prepareStatement(sql);

       psmt.setInt(1, uid);

       rs = psmt.executeQuery();

       House house = null;

       while (rs.next()) {
         house = new House();
         house.setId(rs.getInt("id"));
         house.setName(rs.getString("name"));
         house.setAddress(rs.getString("address"));
         house.setPrice(Double.parseDouble(rs.getString("price")));
         house.setSize(Double.parseDouble(rs.getString("size")));
         house.setDescription(rs.getString("description"));
         house.setPath(rs.getString("path"));
         house.setType(rs.getString("type"));
         house.setSaleRent(rs.getInt("sale_rent"));
         house.setIsCheck(rs.getInt("isCheck"));
         house.setCreateDate(rs.getString("create_date"));

         houseList.add(house);
       }
     } catch (SQLException e) {
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
     return houseList;
   }

   public List<House> searchHouseList(String searchWords)
   {
     List houseList = new ArrayList();

     Connection conn = null;
     PreparedStatement psmt = null;
     ResultSet rs = null;
     try
     {
       try {
         conn = DBUtil.getInstance().getConnection();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }

       String sql = "select id,name,address,price,size,description,path,type,sale_rent,isCheck,create_date from t_house where (name like ? or address like ?) and isCheck=1 and del = 0";

       psmt = conn.prepareStatement(sql);

       psmt.setString(1, "%" + searchWords + "%");
       psmt.setString(2, "%" + searchWords + "%");

       rs = psmt.executeQuery();

       House house = null;

       while (rs.next()) {
         System.out.println("搜索结果 DAO");
         house = new House();
         house.setId(rs.getInt("id"));
         house.setName(rs.getString("name"));
         house.setAddress(rs.getString("address"));
         house.setPrice(Double.parseDouble(rs.getString("price")));
         house.setSize(Double.parseDouble(rs.getString("size")));
         house.setDescription(rs.getString("description"));
         house.setPath(rs.getString("path"));
         house.setType(rs.getString("type"));
         house.setSaleRent(rs.getInt("sale_rent"));
         house.setIsCheck(rs.getInt("isCheck"));
         house.setCreateDate(rs.getString("create_date"));

         houseList.add(house);
       }
     } catch (SQLException e) {
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
     return houseList;
   }

   public List<UserHouseVO> findByChkState(int chkState)
   {
     List houseList = new ArrayList();

     Connection conn = null;
     PreparedStatement psmt = null;
     ResultSet rs = null;
     try
     {
       try {
         conn = DBUtil.getInstance().getConnection();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
       String sql = "select u.real_name, u.mobile, h.name, h.create_date, h.id, h.isCheck from t_user u, t_house h where u.id=h.userId and h.isCheck=? and h.del=0";

       psmt = conn.prepareStatement(sql);

       psmt.setInt(1, chkState);

       rs = psmt.executeQuery();

       UserHouseVO uhVO = null;

       while (rs.next()) {
         uhVO = new UserHouseVO();

         uhVO.setHouseId(rs.getInt("h.id"));
         uhVO.setHouseName(rs.getString("name"));
         uhVO.setUserName(rs.getString("u.real_name"));
         uhVO.setMobile(rs.getString("u.mobile"));
         uhVO.setCreateDate(rs.getString("h.create_date"));
         uhVO.setIsCheck(rs.getInt("h.isCheck"));

         houseList.add(uhVO);
       }
     } catch (SQLException e) {
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
     return houseList;
   }

   public boolean updateChkState(int id, int chkState)
   {
     boolean flag = false;

     int result = -1;

     Connection conn = null;
     PreparedStatement psmt = null;
     try
     {
       try
       {
         conn = DBUtil.getInstance().getConnection();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }

       String sql = "update t_house set isCheck=? where del=0 and id=?";
       System.out.println("sql: " + sql);
       psmt = conn.prepareStatement(sql);

       psmt.setInt(1, chkState);
       psmt.setInt(2, id);

       result = psmt.executeUpdate();

       if (result > 0)
         flag = true;
     }
     catch (SQLException e)
     {
       e.printStackTrace();
       try
       {
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
         DBUtil.closeStatement(psmt);
         DBUtil.closeConnection(conn);
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }

     return flag;
   }
 }

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.dao.impl.HouseDaoImpl
 * JD-Core Version:    0.6.0
 */