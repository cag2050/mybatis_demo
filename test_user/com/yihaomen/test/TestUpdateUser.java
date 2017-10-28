package com.yihaomen.test;

import com.yihaomen.mybatis.inter.IUserOperation;
import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class TestUpdateUser {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
            User user = userOperation.selectUserByID(3);
            System.out.println("update前地址为：" + user.getUserAddress());
            user.setUserAddress("北京 朝阳");
            userOperation.updateUser(user);
            User userUpdated = userOperation.selectUserByID(3);
            System.out.println("update后地址为：" + userUpdated.getUserAddress());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
