package io.github.xudaojie.jdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.github.xudaojie.jdoc.dao.AccountDAO;
import io.github.xudaojie.jdoc.model.AccountModel;
import io.github.xudaojie.jdoc.util.TextUtils;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class LoginController {

    @Autowired
    private AccountDAO mAccountDAO;

    public AccountDAO getAccountDAO() {
        return mAccountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        mAccountDAO = accountDAO;
    }

    @RequestMapping("login.form")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpServletRequest request,
                        HttpSession session) {

        AccountModel accountModel = mAccountDAO.getByName(username);
        if (accountModel!= null &&
                TextUtils.equals(password, accountModel.getPassword())) {
            request.setAttribute("account", accountModel);
            session.setAttribute("account", accountModel);
//            return "project";
            return "redirect:/project.form";
        }

        return "login_failed";
    }
}
