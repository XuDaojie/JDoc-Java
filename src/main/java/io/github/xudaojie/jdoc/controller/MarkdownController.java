package io.github.xudaojie.jdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.model.AccountModel;
import io.github.xudaojie.jdoc.model.BaseResponseBody;
import io.github.xudaojie.jdoc.model.MarkdownModel;
import io.github.xudaojie.jdoc.util.JsonUtils;

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
        model.addAttribute("project_id", projectId);
        model.addAttribute("module_id", moduleId);
        return "editormd";
    }

    @RequestMapping(value = "create_markdown.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String createMarkdown(
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

        BaseResponseBody responseBody = new BaseResponseBody();
        if (mMarkdownDAO.insert(markdownModel) > 0) {
            responseBody.setCode(0);
            responseBody.setData(markdownModel);
        } else {
            responseBody.setCode(102);
            responseBody.setData(markdownModel);
        }
        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping("editormd.form")
    public String editormd(@RequestParam("id") long id,
                           Model model) {
        MarkdownModel markdownModel = mMarkdownDAO.get(id);
        model.addAttribute("markdown", markdownModel);
        return "editormd";
    }

    @RequestMapping(value = "save_markdown.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveMarkdown(
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

        BaseResponseBody responseBody = new BaseResponseBody();
        if (mMarkdownDAO.update(markdownModel) > 0) {
            responseBody.setCode(0);
            responseBody.setData(markdownModel);
            responseBody.setMsg("保存成功");
        } else {
            responseBody.setCode(102);
            responseBody.setData(markdownModel);
            responseBody.setMsg("保存失败");
        }

//        request.setAttribute("project_id", projectId);
//        request.setAttribute("module_id", moduleId);

        return JsonUtils.toJSONString(responseBody);
    }
}
