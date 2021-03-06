package com.yihaomen.test;

import com.yihaomen.mybatis.inter.IUserOperation;
import com.yihaomen.mybatis.model.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

public class TestUserArticles {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 先mapper映射接口
            IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
            List<Article> list = userOperation.getUserArticles(1);
            for (Article article : list) {
                System.out.println("user id = " + article.getId() + ", article title = " + article.getTitle() + ", article content = " + article.getContent() + ", user name = " + article.getUser().getUserName() + ", user age = " + article.getUser().getUserAge() + ", user address = " + article.getUser().getUserAddress());
            }
            System.out.println("输出完毕");
        } finally {
            sqlSession.close();
        }
    }
}
