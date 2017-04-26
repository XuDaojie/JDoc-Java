package io.github.xudaojie.jdoc.controller;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.dao.ProjectDAO;
import io.github.xudaojie.jdoc.model.BaseResponseBody;
import io.github.xudaojie.jdoc.model.MarkdownModel;
import io.github.xudaojie.jdoc.model.ProjectModel;
import io.github.xudaojie.jdoc.util.JsonUtils;
import io.github.xudaojie.jdoc.util.TokenUtils;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class MarkdownController {

    @Autowired
    private MarkdownDAO mMarkdownDAO;
    @Autowired
    private ProjectDAO mProjectDAO;

    @RequestMapping("editormd.form")
    public String editormd(@RequestParam("id") long id,
                           Model model) {
        MarkdownModel markdownModel = mMarkdownDAO.get(id);
        model.addAttribute("markdown", markdownModel);
        return "editormd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "markdown", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insert(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        Long userId = decodedJWT.getClaim("user_id").as(Long.class);
//        Long projectId = decodedJWT.getClaim("project_id").as(Long.class);
        String projectName = decodedJWT.getClaim("project_name").asString();
        String projectPwd = decodedJWT.getClaim("project_password").asString();
        String name = decodedJWT.getClaim("name").asString();
        String content = decodedJWT.getClaim("content").asString();
        String description = decodedJWT.getClaim("description").asString();
//        String password = decodedJWT.getClaim("password").asString();

        MarkdownModel markdownModel = new MarkdownModel();
//        markdownModel.setProjectId(projectId);
//        markdownModel.setModuleId(moduleId);
        markdownModel.setName(name);
        markdownModel.setContent(content);
        markdownModel.setDescription(description);
//        markdownModel.setPassword(password);
        markdownModel.setHandler(userId);
        markdownModel.setCreator(userId);

        int rowNum;
        Map<String, Object> map = new HashMap<>();
        map.put("name", projectName);
        map.put("owner", userId);
        ProjectModel projectModel = mProjectDAO.getByName(map);
        if (projectModel == null) {
            // 创建Markdown、Project
            projectModel = new ProjectModel();
            projectModel.setName(projectName);
            projectModel.setPassword(projectPwd);
            projectModel.setOwner(userId);
            projectModel.setCreator(userId);
            rowNum = mMarkdownDAO.insert(markdownModel, projectModel);
        } else {
            markdownModel.setProjectId(projectModel.getId());
            rowNum = mMarkdownDAO.insert(markdownModel);
        }

        BaseResponseBody responseBody = new BaseResponseBody();
        if (rowNum > 0) {
            responseBody.setCode(0);
            responseBody.setData(markdownModel);
            responseBody.setMsg("保存成功");
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("保存失败");
        }

        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "markdown", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestHeader("X-Access-Token") String token,
                         @RequestParam("user_id") Long userId,
                         @RequestParam("markdown_id") Long markdownId) {
        BaseResponseBody responseBody = new BaseResponseBody();
        int rowCount = mMarkdownDAO.delete(markdownId);
        if (rowCount > 0) {
            responseBody.setCode(0);
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("删除失败");
        }

        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "markdown", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String put(@RequestHeader("X-Access-Token") String token,
                      @RequestParam("markdown_id") Long markdownId,
                      @RequestParam("user_id") Long userId,
                      @RequestParam(value = "project_id", required = false) Long projectId,
                      @RequestParam(value = "project_name", required = false) Long projectName,
                      @RequestParam("name") String name,
                      @RequestParam("content") String content,
                      @RequestParam(value = "description", required = false) String description) {

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

    @RequestMapping(method = RequestMethod.GET, value = "project/{project_id}/markdown", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list(@PathVariable("project_id") Long projectId) {
        List<MarkdownModel> markdownModelList = mMarkdownDAO.getListByProject(projectId);
        BaseResponseBody responseBody = new BaseResponseBody();
        if (markdownModelList != null) {
            responseBody.setCode(0);
            responseBody.setData(markdownModelList);
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("内容不存在");
        }
        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.GET, value = "markdown/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String single(@PathVariable("id") Long id) {
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

}
