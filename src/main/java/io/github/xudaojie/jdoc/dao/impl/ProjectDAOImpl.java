package io.github.xudaojie.jdoc.dao.impl;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import io.github.xudaojie.jdoc.dao.ProjectDAO;
import io.github.xudaojie.jdoc.model.ProjectModel;

/**
 * Created by xdj on 2017/4/18.
 */
public class ProjectDAOImpl extends BaseDAO implements ProjectDAO {

    private String mNameSpace = ProjectModel.class.getName();

    @Override
    public ProjectModel get(long id) {
        SqlSession sqlSession  = getSqlSessionFactory().openSession();
        ProjectModel projectModel = sqlSession.selectOne(mNameSpace + ".get", id);
        sqlSession.close();
        return projectModel;
    }

    @Override
    public List<ProjectModel> getListByOwner(long id) {
        SqlSession sqlSession  = getSqlSessionFactory().openSession();
        List<ProjectModel> projectModels = sqlSession.selectList(mNameSpace + ".getListByOwner", id);
        sqlSession.close();
        return projectModels;
    }

    @Override
    public int update(ProjectModel projectModel) {
        return 0;
    }

    @Override
    public int insert(ProjectModel projectModel) {
        SqlSession sqlSession  = getSqlSessionFactory().openSession();
        int rowCount = sqlSession.insert(mNameSpace + ".insert", projectModel);
        sqlSession.commit();
        sqlSession.close();
        return rowCount;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
