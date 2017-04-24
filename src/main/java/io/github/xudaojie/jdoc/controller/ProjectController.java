package io.github.xudaojie.jdoc.controller;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.dao.ModuleDAO;
import io.github.xudaojie.jdoc.dao.ProjectDAO;
import io.github.xudaojie.jdoc.model.BaseResponseBody;
import io.github.xudaojie.jdoc.model.ProjectModel;
import io.github.xudaojie.jdoc.util.JsonUtils;
import io.github.xudaojie.jdoc.util.TokenUtils;

/**
 * Created by xdj on 2017/4/18.
 */
@RestController
public class ProjectController {

    @Autowired
    private ProjectDAO mProjectDAO;
    @Autowired
    private ModuleDAO mModuleDAO;
    @Autowired
    private MarkdownDAO mMarkdownDAO;

    public void setProjectDAO(ProjectDAO projectDAO) {
        mProjectDAO = projectDAO;
    }

    public void setModuleDAO(ModuleDAO moduleDAO) {
        mModuleDAO = moduleDAO;
    }

    public void setMarkdownDAO(MarkdownDAO markdownDAO) {
        mMarkdownDAO = markdownDAO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insert(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        Long userId = decodedJWT.getClaim("user_id").as(Long.class);
        String name = decodedJWT.getClaim("name").asString();
        String description = decodedJWT.getClaim("description").asString();
        String password = decodedJWT.getClaim("password").asString();

        ProjectModel projectModel = new ProjectModel();
        projectModel.setName(name);
        projectModel.setDescription(description);
        projectModel.setPassword(password);
        projectModel.setOwner(userId);
        projectModel.setCreator(userId);

        BaseResponseBody responseBody = new BaseResponseBody();
        if (mProjectDAO.insert(projectModel) > 0) {
            responseBody.setCode(0);
            responseBody.setData(projectModel);
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("保存失败");
        }

        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "project", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        Long id = decodedJWT.getClaim("project_id").as(Long.class);
        BaseResponseBody responseBody = new BaseResponseBody();
        int rowCount = mMarkdownDAO.delete(id);
        if (rowCount > 0) {
            responseBody.setCode(0);
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("删除失败");
        }
        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/project", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String put(@RequestHeader("X-Access-Token") String token) {
        DecodedJWT decodedJWT = TokenUtils.decode(token);
        Long userId = decodedJWT.getClaim("user_id").as(Long.class);
        Long projectId = decodedJWT.getClaim("project_id").as(Long.class);
        String name = decodedJWT.getClaim("name").asString();
        String description = decodedJWT.getClaim("description").asString();
        String password = decodedJWT.getClaim("password").asString();

        BaseResponseBody responseBody = new BaseResponseBody();
        ProjectModel projectModel = mProjectDAO.get(projectId);
        if (projectModel != null) {
            projectModel.setId(projectId);
            projectModel.setName(name);
            projectModel.setDescription(description);
            projectModel.setPassword(password);
            if (mProjectDAO.update(projectModel) > 0) {
                responseBody.setCode(0);
                responseBody.setData(projectModel);
            } else {
                responseBody.setCode(102);
                responseBody.setMsg("更新失败");
            }
//        projectModel.setOwner(id);
//        projectModel.setCreator(id);
        } else {
            responseBody.setCode(102);
            responseBody.setMsg("项目不存在");
        }

        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/{user_id}/project/", produces = "application/json;charset=UTF-8")
    public String list(@PathVariable("user_id") Long userId) {
        BaseResponseBody responseBody = new BaseResponseBody();

        List<ProjectModel> projectModelList = mProjectDAO.getListByOwner(userId);
        responseBody.setCode(0);
        responseBody.setData(projectModelList);
        return JsonUtils.toJSONString(responseBody);
    }

    @RequestMapping(method = RequestMethod.GET, value = "project/", produces = "application/json;charset=UTF-8")
    public String single(@PathVariable("project_id") Long projectId) {
        BaseResponseBody responseBody = new BaseResponseBody();

        ProjectModel projectModelList = mProjectDAO.get(projectId);
        responseBody.setCode(0);
        responseBody.setData(projectModelList);
        return JsonUtils.toJSONString(responseBody);
    }

}
