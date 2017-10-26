package com.yihaomen.test;

import com.yihaomen.mybatis.inter.IUserOperation;
import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class TestAddUser {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("cag");
        user.setUserAge("30");
        user.setUserAddress("山东 济南");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
            userOperation.addUser(user);
            sqlSession.commit();
            System.out.println("当前增加的用户id为 " + user.getId());
        } finally {
            sqlSession.close();
        }
    }
}
