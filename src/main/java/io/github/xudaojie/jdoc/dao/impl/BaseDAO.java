package io.github.xudaojie.jdoc.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by xdj on 2017/4/18.
 */
public class BaseDAO {
//    static SqlSessionFactory sSqlSessionFactory;
//
//    static {
//        String resource = "mybatis-config.xml";
//        try {
//            InputStream inputStream = Resources.getResourceAsStream(resource);
//            sSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private SqlSessionFactory mSqlSessionFactory;


    public SqlSessionFactory getSqlSessionFactory() {
        return mSqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        mSqlSessionFactory = sqlSessionFactory;
    }
}
