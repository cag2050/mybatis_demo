package com.yihaomen.test;

import com.yihaomen.mybatis.inter.IUserOperation;
import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class TestInterface {
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

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 先mapper映射类
            IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
            // session的函数读取xml中namespace和id
            User user = userOperation.selectUserByID(1);
            System.out.println(user.getUserAddress());
            System.out.println(user.getUserName());
        } finally {
            sqlSession.close();
        }
    }
}
