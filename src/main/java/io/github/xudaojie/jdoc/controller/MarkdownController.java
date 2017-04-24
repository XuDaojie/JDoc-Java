package io.github.xudaojie.jdoc.controller;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.model.AccountModel;
import io.github.xudaojie.jdoc.model.BaseResponseBody;
import io.github.xudaojie.jdoc.model.MarkdownModel;
import io.github.xudaojie.jdoc.util.JsonUtils;
import io.github.xudaojie.jdoc.util.TokenUtils;

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
            responseBody.setMsg("保存成功");
        } else {
            responseBody.setCode(102);
            responseBody.setData(markdownModel);
            responseBody.setMsg("保存失败");
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

    @RequestMapping(value = "markdown.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String markdown(@RequestParam("id") long id) {
        MarkdownModel markdownModel = mMarkdownDAO.get(id);
        BaseResponseBody responseBody = new BaseResponseBody();
        if (markdownModel != null) {
            responseBody.setCode(0);
            responseBody.setData(markdownModel);
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("内容不存在");
        }
        return JsonUtils.toJSONString(responseBody);
    }





    @RequestMapping(value = "markdown", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insert(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        Long userId = decodedJWT.getClaim("user_id").as(Long.class);
        Long projectId = decodedJWT.getClaim("project_id").as(Long.class);
        String name = decodedJWT.getClaim("name").asString();
        String content = decodedJWT.getClaim("content").asString();
        String description = decodedJWT.getClaim("description").asString();
//        String password = decodedJWT.getClaim("password").asString();


        MarkdownModel markdownModel = new MarkdownModel();
        markdownModel.setProjectId(projectId);
//        markdownModel.setModuleId(moduleId);
        markdownModel.setName(name);
        markdownModel.setContent(content);
        markdownModel.setDescription(description);
//        markdownModel.setPassword(password);
        markdownModel.setHandler(userId);
        markdownModel.setCreator(userId);

        BaseResponseBody responseBody = new BaseResponseBody();
        if (mMarkdownDAO.insert(markdownModel) > 0) {
            responseBody.setCode(0);
            responseBody.setData(markdownModel);
            responseBody.setMsg("保存成功");
        } else {
            responseBody.setCode(102);
            responseBody.setData(markdownModel);
            responseBody.setMsg("保存失败");
        }

        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(value = "markdown", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        Long markdownId = decodedJWT.getClaim("id").as(Long.class);

        BaseResponseBody responseBody = new BaseResponseBody();
        int rowCount = mMarkdownDAO.delete(markdownId);
        if (rowCount > 0) {
            responseBody.setCode(0);
        }else {
            responseBody.setCode(102);
            responseBody.setMsg("删除失败");
        }

        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "markdown", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String put(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        Long markdownId = decodedJWT.getClaim("markdown_id").as(Long.class);
        Long userId = decodedJWT.getClaim("user_id").as(Long.class);
        Long projectId = decodedJWT.getClaim("project_id").as(Long.class);
        String name = decodedJWT.getClaim("name").asString();
        String content = decodedJWT.getClaim("content").asString();
        String description = decodedJWT.getClaim("description").asString();

        MarkdownModel markdownModel = new MarkdownModel();
        markdownModel.setId(markdownId);
        markdownModel.setProjectId(projectId);
//        markdownModel.setModuleId(moduleId);
        markdownModel.setName(name);
        markdownModel.setContent(content);
        markdownModel.setDescription(description);
        markdownModel.setHandler(userId);

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
