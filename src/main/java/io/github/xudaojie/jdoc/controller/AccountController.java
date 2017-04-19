package io.github.xudaojie.jdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import io.github.xudaojie.jdoc.dao.AccountDAO;
import io.github.xudaojie.jdoc.model.AccountModel;
import io.github.xudaojie.jdoc.model.BaseResponseBody;
import io.github.xudaojie.jdoc.util.JsonUtils;
import io.github.xudaojie.jdoc.util.TextUtils;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class AccountController {

    @Autowired
    private AccountDAO mAccountDAO;

    public AccountDAO getAccountDAO() {
        return mAccountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        mAccountDAO = accountDAO;
    }

    @RequestMapping("login.form")
    public String loginForm(HttpSession session) {

        AccountModel accountModel = (AccountModel) session.getAttribute("account");
        if (accountModel != null) {
//            return "project";
            return "redirect:/project.form";
        }

        return "login_failed";
    }

    @RequestMapping("login.do")
    @ResponseBody
    public String loginDo(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "password") String password,
                                       HttpSession session) {
        BaseResponseBody responseBody = new BaseResponseBody();

        AccountModel accountModel = mAccountDAO.getByName(username);
        if (accountModel!= null &&
                TextUtils.equals(password, accountModel.getPassword())) {
            session.setAttribute("account", accountModel);
            responseBody.setCode(0);

        } else {
            responseBody.setCode(2);
            responseBody.setMsg("用户名或密码错误");
        }
        return JsonUtils.toJSONString(responseBody);
    }
}
