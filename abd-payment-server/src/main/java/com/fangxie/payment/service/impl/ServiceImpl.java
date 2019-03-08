package com.fangxie.payment.service.impl;

import com.fangxie.payment.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础的服务抽象类
 *
 * @author smniuhe
 * @Description:
 */
public abstract class ServiceImpl<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T getById(Serializable key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public boolean save(T entity) {

        if (mapper.insert(entity) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeById(Serializable key) {

        if (mapper.deleteByPrimaryKey(key) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {

        // TODO: 暂未实现
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {

        return false;
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public boolean updateNotNull(T entity) {

        if (mapper.updateByPrimaryKeySelective(entity) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<T> listByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> list() {
        return mapper.selectAll();
    }


}
