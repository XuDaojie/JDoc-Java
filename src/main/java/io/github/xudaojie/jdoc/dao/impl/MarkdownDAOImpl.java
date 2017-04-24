package io.github.xudaojie.jdoc.dao.impl;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.model.MarkdownModel;
import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 */
public class MarkdownDAOImpl extends BaseDAO<MarkdownModel> implements MarkdownDAO {

    @Override
    public MarkdownModel get(long id) {
        return selectOne(id);
    }

    @Override
    public List<MarkdownModel> getListByOwner(long ownerId) {
        return selectList("getListByOwner", ownerId);
    }

    @Override
    public List<MarkdownModel> getListByProject(long projectId) {
        return super.selectList("getListByProject", projectId);
    }

    @Override
    public int update(MarkdownModel markdownModel) {
        return super.update(markdownModel);
    }

    @Override
    public int insert(MarkdownModel markdownModel) {
        return super.insert(markdownModel);
    }

    @Override
    public int insert(MarkdownModel markdownModel, ProjectModel projectModel) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        int rowCountP = sqlSession.insert(
                ProjectModel.class.getName() + "." + "insert",
                projectModel);
        markdownModel.setProjectId(projectModel.getId());
        int rowCount = sqlSession.insert(
                MarkdownModel.class.getName() + "." + "insert",
                markdownModel);
        sqlSession.commit();
        sqlSession.close();
        return 1;
    }

    @Override
    public int delete(long id) {
        return super.delete(id);
    }
}
