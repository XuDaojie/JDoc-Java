package io.github.xudaojie.jdoc.dao;

import java.util.List;

import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 */
public interface ProjectDAO {
    ProjectModel get(long id);

    ProjectModel getByName(String name);

    List<ProjectModel> getListByOwner(long id);

    int update(ProjectModel projectModel);

    int insert(ProjectModel projectModel);

    int delete(long id);
}
