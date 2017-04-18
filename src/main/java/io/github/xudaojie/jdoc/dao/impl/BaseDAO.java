package io.github.xudaojie.jdoc.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by xdj on 2017/4/18.
 */
public class BaseDAO<T> {
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
    private String mNamespace = null;

    public SqlSessionFactory getSqlSessionFactory() {
        return mSqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        mSqlSessionFactory = sqlSessionFactory;
    }

    protected T selectOne(String statement, Object param) {
        SqlSession sqlSession = mSqlSessionFactory.openSession();
        T tObj = sqlSession.selectOne(format(statement), param);
        sqlSession.close();
        return tObj;
    }

    protected T selectOne(Object param) {
        return selectOne("get", param);
    }

    protected List<T> selectList(String statement, Object param) {
        SqlSession sqlSession = mSqlSessionFactory.openSession();
        List<T> tObjList = sqlSession.selectList(format(statement), param);
        sqlSession.close();;
        return tObjList;
    }

    protected int update(String statement, Object param) {
        SqlSession sqlSession = mSqlSessionFactory.openSession();
        int rowCount = sqlSession.update(format(statement), param);
        sqlSession.commit();
        sqlSession.close();

        return rowCount;
    }

    protected int update(Object param) {
        return update("update", param);
    }

    protected int delete(String statement, Object param) {
        SqlSession sqlSession = mSqlSessionFactory.openSession();
        int rowCount = sqlSession.delete(format(statement), param);
        sqlSession.commit();
        sqlSession.close();

        return rowCount;
    }

    protected int delete(Object param) {
        return update("delete", param);
    }

    protected int insert(String statement, Object param) {
        SqlSession sqlSession = mSqlSessionFactory.openSession();
        int rowCount = sqlSession.insert(format(statement), param);
        sqlSession.commit();
        sqlSession.close();

        return rowCount;
    }

    protected int insert( Object param) {
        return insert("insert", param);
    }

    protected String format(String statement) {
        if (mNamespace == null) {
            // http://blog.csdn.net/gengv/article/details/5178055
            Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            mNamespace = entityClass.getName();
        }

        return String.format("%s.%s", mNamespace, statement);
    }
}
