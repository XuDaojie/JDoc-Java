package io.github.xudaojie.jdoc.dao.impl;

import io.github.xudaojie.jdoc.dao.AccountDAO;
import io.github.xudaojie.jdoc.model.AccountModel;

/**
 * Created by xdj on 2017/4/18.
 */
public class AccountDAOImpl extends BaseDAO<AccountModel> implements AccountDAO {

    @Override
    public AccountModel get(long id) {
        return super.selectOne(id);
    }

    @Override
    public AccountModel getByName(String username) {
        AccountModel accountModel = selectOne("getByName", username);
        return accountModel;
    }

    @Override
    public int update(AccountModel accountModel) {
        return super.update(accountModel);
    }

    @Override
    public int insert(AccountModel accountModel) {
        return super.insert(accountModel);
    }


}
