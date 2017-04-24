package io.github.xudaojie.jdoc.controller;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.github.xudaojie.jdoc.dao.AccountDAO;
import io.github.xudaojie.jdoc.model.AccountModel;
import io.github.xudaojie.jdoc.model.BaseResponseBody;
import io.github.xudaojie.jdoc.util.JsonUtils;
import io.github.xudaojie.jdoc.util.TextUtils;
import io.github.xudaojie.jdoc.util.TokenUtils;

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

    @RequestMapping(value = "login.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginDo(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          HttpServletRequest request, HttpServletResponse response,
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

    @RequestMapping(value = "account", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String dispose(@RequestHeader("X-Access-Token") String token) {
        BaseResponseBody responseBody = null;
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        if (decodedJWT == null || decodedJWT.getHeaderClaim("action") == null) {
            responseBody = new BaseResponseBody();
            responseBody.setCode(401);
            responseBody.setData("token已失效");
        } else {
            String username = decodedJWT.getClaim("username").asString();
            String password = decodedJWT.getClaim("password").asString();
            String nickname = decodedJWT.getClaim("nickname").asString();

            if (TextUtils.isEmpty(username)) {
                responseBody = new BaseResponseBody();
                responseBody.setCode(1);
                responseBody.setData("用户名不存在");
            }

            AccountModel accountModel = new AccountModel();
            accountModel.setUsername(username);
            accountModel.setPassword(password);
            accountModel.setNickname(nickname);

            switch (decodedJWT.getHeaderClaim("action").asString()) {
                case "login":
                    responseBody = login(accountModel);
                    break;
                case "register":
                    responseBody = register(accountModel);
                    break;
                case "update":
                    responseBody = update(accountModel);
                    break;
                case "reset":
                    responseBody = update(accountModel);
                    break;
                default:
                    responseBody = new BaseResponseBody();
                    responseBody.setCode(5);
                    responseBody.setData("token已失效");
                    break;
            }
        }

        String username = null;
        String password = null;
        if (decodedJWT != null) {
            username = decodedJWT.getClaim("username").asString();
        }
        if (decodedJWT != null) {
            password = decodedJWT.getClaim("password").asString();
        }

        AccountModel accountModel = mAccountDAO.getByName(username);
        if (accountModel == null) {
            responseBody.setCode(1);
            responseBody.setMsg("账号不存在");
        } else if (TextUtils.equals(password, accountModel.getPassword())) {
            accountModel.setPassword(null);

            responseBody.setCode(0);
            responseBody.setData(accountModel);
        } else {
            responseBody.setCode(2);
            responseBody.setMsg("用户名或密码错误");
        }

        return JsonUtils.toJSONString(responseBody);
    }

    public BaseResponseBody login(AccountModel accountModel) {
        BaseResponseBody responseBody = new BaseResponseBody();
        AccountModel selectAccountModel = mAccountDAO.getByName(accountModel.getUsername());
        if (selectAccountModel == null) {
            responseBody.setCode(1);
            responseBody.setMsg("账号不存在");
        } else if (TextUtils.equals(accountModel.getPassword(), accountModel.getPassword())) {
            accountModel.setPassword(null);

            responseBody.setCode(0);
            responseBody.setData(accountModel);
        } else {
            responseBody.setCode(2);
            responseBody.setMsg("用户名或密码错误");
        }
        return responseBody;
    }

    public BaseResponseBody register(AccountModel accountModel) {
        BaseResponseBody responseBody = new BaseResponseBody();

        if (TextUtils.isEmpty(accountModel.getPassword())) {
            responseBody = new BaseResponseBody();
            responseBody.setCode(12);
            responseBody.setData("密码不能为空");
        }
        AccountModel selectAccountModel = mAccountDAO.getByName(accountModel.getUsername());
        if (selectAccountModel == null) {
            if (mAccountDAO.insert(accountModel) > 0) {
                responseBody.setData(accountModel);
            }
        } else {
            responseBody.setCode(11);
            responseBody.setMsg("用户名已注册");
        }

        return responseBody;
    }

    public BaseResponseBody update(AccountModel accountModel) {
        BaseResponseBody responseBody = new BaseResponseBody();

        AccountModel selectAccountModel = mAccountDAO.getByName(accountModel.getUsername());
        if (selectAccountModel == null) {
            responseBody.setCode(1);
            responseBody.setMsg("账号不存在");
        } else if (mAccountDAO.update(accountModel) > 0){
            responseBody.setCode(0);
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("修改失败");
        }

        return responseBody;
    }
}
