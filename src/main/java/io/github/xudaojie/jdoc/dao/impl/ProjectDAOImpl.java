package io.github.xudaojie.jdoc.dao.impl;

import java.util.List;

import io.github.xudaojie.jdoc.dao.ProjectDAO;
import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 */
public class ProjectDAOImpl extends BaseDAO<ProjectModel> implements ProjectDAO {

    @Override
    public ProjectModel get(long id) {
        return super.selectOne(id);
    }

    @Override
    public ProjectModel getByName(Object name) {
        return selectOne("getByName", name);
    }

    @Override
    public List<ProjectModel> getListByPublic() {
        return selectList("getListByPublic", null);
    }

    @Override
    public List<ProjectModel> getListByOwner(long id) {
        return selectList("getListByOwner", id);
    }

    @Override
    public int update(ProjectModel projectModel) {
        return super.update(projectModel);
    }

    @Override
    public int insert(ProjectModel projectModel) {
        return super.insert(projectModel);
    }

    @Override
    public int delete(long id) {
        return super.upDelete(id);
    }
}
