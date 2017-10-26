package com.yihaomen.test;

import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class TestSessionSql {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // session的函数读取xml中namespace和id
            User user = (User) sqlSession.selectOne("com.yihaomen.mybatis.inter.IUserOperation.selectUserByID", 1);
            System.out.println(user.getUserName());
            System.out.println(user.getUserAge());
            System.out.println(user.getUserAddress());
        } finally {
            sqlSession.close();
        }
    }
}
