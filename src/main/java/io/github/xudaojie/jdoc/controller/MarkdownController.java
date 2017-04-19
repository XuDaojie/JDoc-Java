package io.github.xudaojie.jdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.model.AccountModel;
import io.github.xudaojie.jdoc.model.MarkdownModel;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class MarkdownController {

    @Autowired
    private MarkdownDAO mMarkdownDAO;

    @RequestMapping("create_markdown.form")
    public String createMarkdown(@RequestParam("project_id") long projectId,
                                 @RequestParam(value = "module_id", required = false) Long moduleId,
                                 Model model) {
        return "editormd";
    }

    @RequestMapping("create_markdown.do")
    public void createMarkdown(
            @RequestParam("project_id") long projectId,
            @RequestParam(value = "module_id", required = false) Long moduleId,
            @RequestParam("name") String name,
            @RequestParam("markdown") String markdown,
            Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AccountModel account = (AccountModel) session.getAttribute("account");
        MarkdownModel markdownModel = new MarkdownModel();
        markdownModel.setProjectId(projectId);
        markdownModel.setModuleId(moduleId);
        markdownModel.setName(name);
        markdownModel.setContent(markdown);
        markdownModel.setCreator(account.getId());
        markdownModel.setHandler(account.getId());
        if (mMarkdownDAO.insert(markdownModel) > 0) {

        }
    }

    @RequestMapping("editormd.form")
    public String editormd(@RequestParam("id") long id,
                           Model model) {
        MarkdownModel markdownModel = mMarkdownDAO.get(id);
        model.addAttribute("markdown", markdownModel);
        return "editormd";
    }

    @RequestMapping("save_markdown.do")
    public void saveMarkdown(
            @RequestParam("id") long id,
            @RequestParam("project_id") long projectId,
            @RequestParam(value = "module_id", required = false) Long moduleId,
            @RequestParam("name") String name,
            @RequestParam("markdown") String markdown,
            Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AccountModel account = (AccountModel) session.getAttribute("account");
        MarkdownModel markdownModel = new MarkdownModel();
        markdownModel.setId(id);
        markdownModel.setProjectId(projectId);
        markdownModel.setModuleId(moduleId);
        markdownModel.setName(name);
        markdownModel.setContent(markdown);
        markdownModel.setHandler(account.getId());
        if (mMarkdownDAO.update(markdownModel) > 0) {
        }
    }
}
