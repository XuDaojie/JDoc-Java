package io.github.xudaojie.jdoc.dao;

import java.util.List;

import io.github.xudaojie.jdoc.model.MarkdownModel;
import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 */
public interface MarkdownDAO {
    MarkdownModel get(long id);

    List<MarkdownModel> getListByOwner(long ownerId);

    List<MarkdownModel> getListByProject(long projectId);

    int update(MarkdownModel markdownModel);

    int insert(MarkdownModel markdownModel);

    int insert(MarkdownModel markdownModel, ProjectModel projectModel);

    int delete(long id);
}
