package io.github.xudaojie.jdoc.dao.impl;

import java.util.List;

import io.github.xudaojie.jdoc.dao.ModuleDAO;
import io.github.xudaojie.jdoc.model.ModuleModel;

/**
 * Created by xdj on 2017/4/19.
 */
public class ModuleDAOImpl extends BaseDAO<ModuleModel> implements ModuleDAO {
    @Override
    public ModuleModel get(long id) {
        return super.selectOne(id);
    }

    @Override
    public List<ModuleModel> getListByProject(long projectId) {
        return super.selectList("getListByProject", projectId);
    }

    @Override
    public int update(ModuleModel moduleModel) {
        return super.update(moduleModel);
    }

    @Override
    public int insert(ModuleModel moduleModel) {
        return super.insert(moduleModel);
    }

    @Override
    public int delete(long id) {
        return super.upDelete(id);
    }
}
