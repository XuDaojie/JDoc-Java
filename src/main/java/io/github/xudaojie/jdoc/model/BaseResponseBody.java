package io.github.xudaojie.jdoc.model;

/**
 * Created by xdj on 2017/4/19.
 */
public class BaseResponseBody {

    /**
     * code : 0
     * msg : 返回结果的错误码，0表示成功，其它值表示失败。
     * data : {}
     */

    private int code;
    private String msg;

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
}
