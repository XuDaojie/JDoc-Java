package io.github.xudaojie.jdoc.dao.impl;

import org.apache.ibatis.session.SqlSession;

import io.github.xudaojie.jdoc.dao.AccountDAO;
import io.github.xudaojie.jdoc.model.AccountModel;

/**
 * Created by xdj on 2017/4/18.
 */
public class AccountDAOImpl extends BaseDAO implements AccountDAO {

    @Override
    public AccountModel get(long id) {
        return null;
    }

    @Override
    public AccountModel getByName(String username) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        AccountModel accountModel = sqlSession.selectOne("getByName", username);
        sqlSession.close();
        return accountModel;
    }

    @Override
    public int update(AccountModel accountModel) {
        return 0;
    }

    @Override
    public int insert(AccountModel accountModel) {
        return 0;
    }


}
