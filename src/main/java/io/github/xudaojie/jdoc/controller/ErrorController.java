package io.github.xudaojie.jdoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.xudaojie.jdoc.model.BaseResponseBody;
import io.github.xudaojie.jdoc.util.JsonUtils;

/**
 * Created by xdj on 2017/4/24.
 */
@RestController
public class ErrorController {
    @RequestMapping(method = RequestMethod.GET, value = "error/token", produces = "application/json;charset=UTF-8")
    public String token() {
        BaseResponseBody responseBody = new BaseResponseBody();
        responseBody.setCode(401);
        responseBody.setMsg("授权失败");
        return JsonUtils.toJSONString(responseBody);
    }
}
