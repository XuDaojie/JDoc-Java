package io.github.xudaojie.jdoc.dao;

import java.util.List;

import io.github.xudaojie.jdoc.model.ModuleModel;

/**
 * Created by xdj on 2017/4/18.
 */
public interface ModuleDAO {
    ModuleModel get(long id);

    List<ModuleModel> getListByProject(long projectId);

    int update(ModuleModel moduleModel);

    int insert(ModuleModel moduleModel);

    int delete(long id);
}
