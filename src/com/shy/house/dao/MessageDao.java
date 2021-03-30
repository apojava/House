package com.shy.house.dao;

import com.shy.house.beans.Message;
import com.shy.house.beans.UserAndMessage;
import java.util.List;

public abstract interface MessageDao
{
  public abstract boolean save(Message paramMessage);

  public abstract List<UserAndMessage> getMessageList();
}

/* Location:           G:\Java_Web\apache-tomcat-8.0.43\webapps\HouseMS\WEB-INF\classes\
 * Qualified Name:     com.ruanko.house.dao.MessageDao
 * JD-Core Version:    0.6.0
 */