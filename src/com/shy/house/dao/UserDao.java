package com.shy.house.dao;

/**
 * shy
 */


import com.shy.house.beans.User;

public interface UserDao {
    boolean save(User var1);

    User findUser(String var1, String var2);

    boolean isExist(String var1);

    User get(int var1);
}
