package io.github.xudaojie.jdoc.dao.impl;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import io.github.xudaojie.jdoc.dao.MarkdownDAO;
import io.github.xudaojie.jdoc.model.MarkdownModel;

/**
 * Created by xdj on 2017/4/18.
 */
public class MarkdownDAOImpl extends BaseDAO implements MarkdownDAO {

    private String mNamespace = MarkdownModel.class.getName();

    @Override
    public MarkdownModel get(long id) {
        return null;
    }

    @Override
    public List<MarkdownModel> getListByOwner(long ownerId) {
        return null;
    }

    @Override
    public int update(MarkdownModel markdownModel) {
        return 0;
    }

    @Override
    public int insert(MarkdownModel markdownModel) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        int rowCount = sqlSession.insert(mNamespace + ".insert", markdownModel);
        sqlSession.commit();
        sqlSession.close();
        return rowCount;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
