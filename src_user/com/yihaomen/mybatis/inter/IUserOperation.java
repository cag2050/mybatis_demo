package com.yihaomen.mybatis.inter;

import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.model.User;

import java.util.List;

public interface IUserOperation {
    User selectUserByID(int id);

    void addUser(User user);

    List<User> selectUsers(String userName);

    List<User> selectAllUsers();

    void updateUser(User user);

    void deleteUser(int id);

    List<Article> getUserArticles(int id);
}
