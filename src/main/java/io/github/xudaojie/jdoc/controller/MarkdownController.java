package io.github.xudaojie.jdoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class MarkdownController {

    @RequestMapping("create_markdown.form")
    public String createMarkdown(Model model) {
        return "editormd";
    }
}
