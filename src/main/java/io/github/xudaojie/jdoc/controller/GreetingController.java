package io.github.xudaojie.jdoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class GreetingController {

    @RequestMapping("greeting.form")
    public String greeting(Model model) {
        model.addAttribute("name", "World");
        return "greeting";
    }
}
