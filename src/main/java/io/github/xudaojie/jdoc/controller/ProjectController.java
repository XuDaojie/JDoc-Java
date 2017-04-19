package io.github.xudaojie.jdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.dao.ModuleDAO;
import io.github.xudaojie.jdoc.dao.ProjectDAO;
import io.github.xudaojie.jdoc.model.MarkdownModel;
import io.github.xudaojie.jdoc.model.ModuleModel;
import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
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

    @RequestMapping("create_project.form")
    public String createProject(@RequestParam(value = "name") String name,
                                @RequestParam(value = "description", required = false) String description,
                                @RequestParam(value = "password", required = false) String password) {
        ProjectModel projectModel = new ProjectModel();
        projectModel.setName(name);
        projectModel.setDescription(description);
        projectModel.setPassword(password);
        projectModel.setOwner(1L);
        projectModel.setCreator(1L);

        if(mProjectDAO.insert(projectModel) > 0) {
            return "redirect:/project.form";
        }

        return "error";
    }

    @RequestMapping("project.form")
    public String project(Model model) {
        List<ProjectModel> projectModels = mProjectDAO.getListByOwner(1L);
        String projectNames = "";
        for (ProjectModel projectModel:
             projectModels) {
            projectNames += projectModel.getName() + "， ";
        }
        model.addAttribute("project_names", projectNames);
        model.addAttribute("project_list", projectModels);
        return "project";
    }

    @RequestMapping("project_main.form")
    public String projectMain(@RequestParam("project_id") Long projectId, Model model) {
        ProjectModel projectModel = mProjectDAO.get(projectId);
        List<ModuleModel> moduleList = mModuleDAO.getListByProject(projectId);
        List<MarkdownModel> markdownModels = mMarkdownDAO.getListByProject(projectId);

        if (projectModel != null) {
            model.addAttribute("project_name", projectModel.getName());
            model.addAttribute("module_list", moduleList);
            model.addAttribute("markdown_list", markdownModels);
            return "project_main";
        }
        return "error";
    }

}
