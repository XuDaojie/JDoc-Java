package io.github.xudaojie.jdoc.dao;

import java.util.List;

import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 * 除了直接传入id的方法，其余全部都还需要传入userid
 */
public interface ProjectDAO {
    ProjectModel get(long id);

    ProjectModel getByName(Object object);

    List<ProjectModel> getListByPublic();

    List<ProjectModel> getListByOwner(long id);

    int update(ProjectModel projectModel);

    int insert(ProjectModel projectModel);

    int delete(long id);
}
