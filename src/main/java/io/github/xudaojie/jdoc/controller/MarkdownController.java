package io.github.xudaojie.jdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.model.MarkdownModel;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class MarkdownController {

    @Autowired
    private MarkdownDAO mMarkdownDAO;

    @RequestMapping("create_markdown.form")
    public String createMarkdown(Model model) {
        return "editormd";
    }

    @RequestMapping("create_markdown.do")
    public void createMarkdown(
            @RequestParam("project_id") long projectId,
            @RequestParam(value = "module_id", required = false) Long moduleId,
            @RequestParam("name") String name,
            @RequestParam("markdown") String markdown,
            Model model) {
        MarkdownModel markdownModel = new MarkdownModel();
        markdownModel.setProjectId(projectId);
        markdownModel.setModuleId(moduleId);
        markdownModel.setName(name);
        markdownModel.setContent(markdown);
        markdownModel.setCreator(1L);
        markdownModel.setHandler(1L);
        if (mMarkdownDAO.insert(markdownModel) > 0) {

        }
    }
}
