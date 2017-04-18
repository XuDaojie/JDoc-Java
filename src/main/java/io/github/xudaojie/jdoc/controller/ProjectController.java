package io.github.xudaojie.jdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import io.github.xudaojie.jdoc.dao.ProjectDAO;
import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectDAO mProjectDAO;

    public void setProjectDAO(ProjectDAO projectDAO) {
        mProjectDAO = projectDAO;
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
        return "project";
    }
}
