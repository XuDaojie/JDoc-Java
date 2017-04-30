package io.github.xudaojie.jdoc.controller;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Base64;

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

    @RequestMapping(method = RequestMethod.GET, value = "account", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestHeader("Authorization") String auth) {
//      Basic username:password
        auth = auth.replace("Basic ", "");
        auth = new String(Base64.getDecoder().decode(auth));
        String username = auth.split(":")[0];
        String password = auth.split(":")[1];

        BaseResponseBody responseBody = new BaseResponseBody();
        AccountModel accountModel = mAccountDAO.getByName(username);
        if (accountModel == null) {
            responseBody.setCode(1);
            responseBody.setMsg("用户名不存在");
        } else if (TextUtils.equals(accountModel.getPassword(), password)) {
            accountModel.setPassword(null);
            String token = TokenUtils.create("jdoc", accountModel.getUsername(), System.currentTimeMillis());
            responseBody.setCode(0);
            responseBody.setData(accountModel);
            responseBody.setToken(token);
        } else {
            responseBody.setCode(2);
            responseBody.setMsg("用户名或密码错误");
        }
        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.POST, value = "account", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "nickname", required = false) String nickname) {
        AccountModel accountModel = new AccountModel();
        accountModel.setUsername(username);
        accountModel.setPassword(password);
        accountModel.setNickname("Default");
//        accountModel.setNickname(System.currentTimeMillis() + "");

        BaseResponseBody responseBody = new BaseResponseBody();
        if (TextUtils.isEmpty(username)) {
            responseBody.setCode(12);
            responseBody.setData("用户名不能为空");
        } else if (TextUtils.isEmpty(password)) {
            responseBody.setCode(12);
            responseBody.setData("密码不能为空");
        } else {
            AccountModel selectAccountModel = mAccountDAO.getByName(username);
            if (selectAccountModel == null) {
                if (mAccountDAO.insert(accountModel) > 0) {
                    responseBody.setData(accountModel);
                }
            } else {
                responseBody.setCode(11);
                responseBody.setMsg("用户名已注册");
            }
        }

        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "account", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponseBody update(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        String username = decodedJWT.getClaim("username").asString();
        String password = decodedJWT.getClaim("password").asString();
        String nickname = decodedJWT.getClaim("nickname").asString();
        AccountModel accountModel = new AccountModel();
        accountModel.setUsername(username);
        accountModel.setPassword(password);
        accountModel.setNickname(nickname);

        BaseResponseBody responseBody = new BaseResponseBody();
        if (TextUtils.isEmpty(username)) {
            responseBody.setCode(12);
            responseBody.setData("用户名不能为空");
        } else {
            AccountModel selectAccountModel = mAccountDAO.getByName(username);
            if (selectAccountModel == null) {
                responseBody.setCode(1);
                responseBody.setMsg("用户名不存在");
            } else if (mAccountDAO.update(accountModel) > 0) {
                responseBody.setCode(0);
            } else {
                responseBody.setCode(102);
                responseBody.setMsg("修改失败");
            }
        }

        return responseBody;
    }
}
