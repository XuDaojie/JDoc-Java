package io.github.xudaojie.jdoc.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by xdj on 2017/4/19.
 */
public class JsonUtils {
    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj);
    }
}
