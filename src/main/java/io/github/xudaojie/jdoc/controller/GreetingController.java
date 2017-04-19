package io.github.xudaojie.jdoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class GreetingController {

    @RequestMapping("greeting.form")
    public String greeting(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "greeting"; // 跳转页面
    }

    // produces 在ajax调用时默认编码为ISO-8859-1
    @RequestMapping(value = "greeting.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String greeting() {
        return "Hello World!"; // 返回字符串
    }
}
