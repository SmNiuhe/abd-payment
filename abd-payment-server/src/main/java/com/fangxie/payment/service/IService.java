package com.fangxie.payment.service;


import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 通用接口，偏向于单表操作
 *
 * @author <a href="smniuhe@gmail.com">smniuhe</a>
 * @Description: CRUD 常见操作封装
 */
@Service
public interface IService<T> {

    T getById(Serializable id);

    boolean save(T entity);

    /*@Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean saveBatch(Collection<T> entityList) {
        return this.saveBatch(entityList, 1000);
    }

    boolean saveBatch(Collection<T> entityList, int batchSize);

    @Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean saveOrUpdateBatch(Collection<T> entityList) {
        return this.saveOrUpdateBatch(entityList, 1000);
    }

    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);
*/    boolean removeById(Serializable id);

    boolean removeByMap(Map<String, Object> columnMap);


    boolean removeByIds(Collection<? extends Serializable> idList);

    /*boolean updateById(T entity);

    @Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean updateBatchById(Collection<T> entityList) {
        return this.updateBatchById(entityList, 1000);
    }

    boolean updateBatchById(Collection<T> entityList, int batchSize);

    boolean saveOrUpdate(T entity);

    Collection<T> listByIds(Collection<? extends Serializable> idList);

    Collection<T> listByMap(Map<String, Object> columnMap);*/


    int updateAll(T entity);

    boolean updateNotNull(T entity);

    List<T> listByExample(Object example);

    List<T> list();
}