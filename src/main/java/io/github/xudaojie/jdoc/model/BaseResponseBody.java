package io.github.xudaojie.jdoc.model;

import java.io.Serializable;

/**
 * Created by xdj on 2017/4/19.
 */
public class BaseResponseBody implements Serializable {

    /**
     * code : 0
     * msg : 返回结果的错误码，0表示成功，其它值表示失败。
     * data : {}
     *
     * code = 0 登录成功
     * code = 1 用户名不存在
     * code = 2 密码错误
     * code = 3 系统已屏蔽此账号
     * code = 4 密码错误次数超过了最大的重试次数
     * code = 11 用户已存在
     * code = 12 密码不能为空
     * code = 401 token失效
     * code = 102 请求错误
     */

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
