package com.daowen.ssm.simplecrud;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SimpleBizservice<M extends SimpleMapper, E> {

    @Autowired
    protected M mapper;

    public int insert(E entity) {
        return mapper.insert(entity);
    }

    public int update(E entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int delete(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int delete(String where) {
        return mapper.delete(where);
    }

    @SuppressWarnings("unchecked")
    public E load(Object id) {
        return (E) mapper.selectByPrimaryKey(id);
    }

    @SuppressWarnings("unchecked")
    public E load(String where) {
        return (E) mapper.load(where);
    }

    @SuppressWarnings("unchecked")
    public List<E> getEntityPlus(Map map) {
        return (List<E>) mapper.getEntityPlus(map);
    }

    @SuppressWarnings("unchecked")
    public E loadPlus(Map map) {
        return (E) mapper.loadPlus(map);
    }

    @SuppressWarnings("unchecked")
    public List<E> getEntity(String filter) {
        return (List<E>) mapper.getEntity(filter);
    }

    @SuppressWarnings("unchecked")
    public E loadPlus(Object id) {
        return (E) mapper.loadPlus(id);
    }

    @SuppressWarnings("unchecked")
    public List<E> query(Map map) {
        return (List<E>) mapper.query(map);
    }

    @SuppressWarnings("unchecked")
    public List<E> query(String where) {
        Map<String, String> map = new java.util.HashMap<>();
        map.put("where", where);
        return (List<E>) mapper.query(map);
    }

    public int count(Map map) {
        return mapper.count(map);
    }

    @SuppressWarnings("unchecked")
    public List<E> find(Map map) {
        return (List<E>) mapper.find(map);
    }

    @SuppressWarnings("unchecked")
    public List<E> selectAll() {
        return (List<E>) mapper.selectAll();
    }

    public int executeUpdate(String sql) {
        return mapper.executeUpdate(sql);
    }

    public int getRecordCount(String where) {
        return mapper.getRecordCount(where);
    }

    public void save(E entity) {
        insert(entity);
    }

    public boolean isExist(String where) {
        return mapper.getRecordCount(where) > 0;
    }

    @SuppressWarnings("unchecked")
    public E getEntity(String filter, boolean single) {
        List<E> list = getEntity(filter);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}