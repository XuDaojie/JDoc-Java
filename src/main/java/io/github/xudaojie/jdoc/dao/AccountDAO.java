package io.github.xudaojie.jdoc.dao;

import io.github.xudaojie.jdoc.model.AccountModel;

/**
 * Created by xdj on 2017/4/18.
 */
public interface AccountDAO {
    AccountModel get(long id);

    AccountModel getByName(String username);

    int update(AccountModel accountModel);

    int insert(AccountModel accountModel);
}
