package com.daowen.ssm.simplecrud;

import java.util.List;
import java.util.Map;

public interface SimpleMapper<T> {

    int insert(Object entity);

    int updateByPrimaryKey(Object entity);

    int deleteByPrimaryKey(Object id);

    Object selectByPrimaryKey(Object id);

    List getEntityPlus(Map map);

    Object loadPlus(Map map);

    Object loadPlus(Object id);

    List query(Map map);

    int count(Map map);

    List find(Map map);

    List selectAll();

    int delete(String where);

    Object load(String where);

    List getEntity(String filter);

    int executeUpdate(String sql);

    int getRecordCount(String where);
}