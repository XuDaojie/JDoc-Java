package io.github.xudaojie.jdoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.xudaojie.jdoc.dao.AccountDAO;
import io.github.xudaojie.jdoc.dao.impl.AccountDAOImpl;
import io.github.xudaojie.jdoc.model.AccountModel;
import io.github.xudaojie.jdoc.util.TextUtils;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class LoginController {

    private AccountDAO mAccountDAO = new AccountDAOImpl();

    @RequestMapping("login.form")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {

        AccountModel accountModel = mAccountDAO.getByName(username);
        if (accountModel!= null &&
                TextUtils.equals(password, accountModel.getPassword())) {
            return "projects";
        }

        return "login_failed";
    }
}
