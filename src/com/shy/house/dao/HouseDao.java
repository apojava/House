package com.shy.house.dao;

import com.shy.house.beans.House;
import com.shy.house.beans.UserHouseVO;
import java.util.List;

public abstract interface HouseDao
{
  public abstract boolean add(House paramHouse);

  public abstract House findHouseById(int paramInt);

  public abstract boolean del(int paramInt);

  public abstract List<House> houseList();

  public abstract List<House> houseListByUid(int paramInt);

  public abstract List<House> searchHouseList(String paramString);

  public abstract List<UserHouseVO> findByChkState(int paramInt);

  public abstract boolean updateChkState(int paramInt1, int paramInt2);
}

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.dao.HouseDao
 * JD-Core Version:    0.6.0
 */