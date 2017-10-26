package com.yihaomen.mybatis.inter;

import com.yihaomen.mybatis.model.User;

public interface IUserOperation {
    User selectUserByID(int id);
}
