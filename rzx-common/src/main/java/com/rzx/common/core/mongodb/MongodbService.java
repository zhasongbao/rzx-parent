package com.rzx.common.core.mongodb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * mongodb工具类
 *
 * @author hk
 * @date: 2021-09-14
 */
@Component
public class MongodbService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询多条
     *
     * @param query
     * @return
     */
    public <T> T find(Query query, Class<T> entityClass) {
        return (T) this.mongoTemplate.find(query, entityClass);
    }

    /**
     * 查询多条
     *
     * @param query
     * @param collectionName
     * @return
     */
    public <T> List<T> findList(Query query, Class<T> entityClass, String collectionName) {
        return this.mongoTemplate.find(query, entityClass, collectionName);
    }

    /**
     * 查询单条
     *
     * @param query
     * @return
     */
    public <T> T findOne(Query query, Class<T> entityClass) {
        return this.mongoTemplate.findOne(query, entityClass);
    }

    /**
     * 查询单条
     *
     * @param query          查询条件
     * @param collectionName 表名
     * @return
     */
    public <T> T findOne(Query query, String collectionName, Class<T> entityClass) {
        return this.mongoTemplate.findOne(query, entityClass, collectionName);
    }

    /**
     * 查询并修改
     *
     * @param query       查询条件
     * @param update      修改条件
     * @param entityClass 类名
     */
    public <T> T update(Query query, Update update, Class<T> entityClass) {
        return this.mongoTemplate.findAndModify(query, update, entityClass);
    }

    /**
     * 查询并修改
     *
     * @param query       查询条件
     * @param entity      对象
     * @param entityClass 类名
     */
    public <T> T update(Query query, T entity, Class<T> entityClass) {
        Update update = new Update();

        JSONObject entityJson = JSON.parseObject(JSON.toJSONString(entity));

        Iterator<String> iterator = entityJson.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = entityJson.get(key);

            if ("updateDate".equals(key) || "createDate".equals(key)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long lt = (long) value;
                Date date = new Date(lt);
                update.set(key, date);
            } else {
                update.set(key, value);
            }
        }
        return this.mongoTemplate.findAndModify(query, update, entityClass);
    }

    /**
     * 查询并修改
     *
     * @param query          查询条件
     * @param update         修改条件
     * @param collectionName 表名
     * @param entityClass    实体类
     */
    public <T> T update(Query query, Update update, String collectionName, Class<T> entityClass) {
        return this.mongoTemplate.findAndModify(query, update, entityClass, collectionName);
    }

    /**
     * 保存
     *
     * @param entity 实体类
     * @return
     */
    public <T> T insert(T entity) {
        return this.mongoTemplate.insert(entity);
    }

    /**
     * 保存
     *
     * @param entity         实体类
     * @param collectionName 表名
     * @return
     */
    public <T> T insert(T entity, String collectionName) {
        return this.mongoTemplate.insert(entity, collectionName);
    }

    /**
     * 保存
     *
     * @param entity 实体类
     * @return
     */
    public <T> T save(T entity) {
        return this.mongoTemplate.save(entity);
    }

    /**
     * 保存
     *
     * @param entity         实体类
     * @param collectionName 表名
     * @return
     */
    public <T> T save(T entity, String collectionName) {
        return this.mongoTemplate.save(entity, collectionName);
    }


    /**
     * 批量保存
     *
     * @param entities    集合实体类
     * @param entityClass 实体类Class
     */
    public <T> Collection<T> batchSave(Collection<T> entities, Class<T> entityClass) {
        return this.mongoTemplate.insert(entities, entityClass);
    }

    /**
     * 批量保存
     *
     * @param entities       集合实体类对象
     * @param collectionName 表名
     */
    public <T> Collection<T> batchSave(Collection<T> entities, String collectionName) {
        return this.mongoTemplate.insert(entities, collectionName);
    }

    /**
     * 删除
     *
     * @param entity 实体类
     * @return
     */
    public <T> T delete(T entity) {
        this.mongoTemplate.remove(entity);
        return entity;
    }

    /**
     * 删除
     *
     * @param entity         实体类
     * @param collectionName 表名
     * @return
     */
    public <T> T delete(T entity, String collectionName) {
        this.mongoTemplate.remove(entity, collectionName);
        return entity;
    }

    /**
     * 删除
     *
     * @param query       条件参数
     * @param entityClass class
     */
    public void delete(Query query, Class<?> entityClass) {
        this.mongoTemplate.remove(query, entityClass);
    }

    /**
     * 删除
     *
     * @param query          删除参数对象
     * @param collectionName 表名
     * @param entityClass    class
     */
    public void delete(Query query, String collectionName, Class<?> entityClass) {
        this.mongoTemplate.remove(query, entityClass, collectionName);
    }

    /**
     * 根据id查询
     *
     * @param id          id
     * @param entityClass 类名
     * @return
     */
    public <T> T findById(String id, Class<T> entityClass) {
        return this.mongoTemplate.findById(id, entityClass);
    }

    /**
     * 根据id查询
     *
     * @param id             id
     * @param collectionName 表名
     * @param entityClass    类名
     * @return
     */
    public <T> T findById(String id, String collectionName, Class<T> entityClass) {
        return this.mongoTemplate.findById(id, entityClass, collectionName);
    }

    /**
     * 分页查询
     *
     * @param pageable    分页参数
     * @param query       查询条件
     * @param entityClass class
     * @return
     */
    public PageInfo<Object> findPage(Page pageable, Query query, Class<?> entityClass) {
        PageInfo<Object> page = new PageInfo<>();
        long count = count(query, entityClass);
        page.setTotal(Long.valueOf(count).intValue());

        int pageNum = pageable.getPageNum();
        int pageSize = pageable.getPageSize();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);

        query.skip((pageNum - 1) * pageSize).limit(pageSize);

        //total=(pageNum-1) * pageSize+ pageSize
        //x=pageSize
        List<Object> rows = (List<Object>) find(query, entityClass);
        page.setList(rows);
        return page;
    }

    /**
     * 查询并分页
     *
     * @param pageable    分页参数
     * @param count       数量
     * @param query       查新条件
     * @param entityClass class
     * @return
     */
    public PageInfo<Object> findPage(Page pageable, int count, Query query, Class<?> entityClass) {
        PageInfo<Object> page = new PageInfo<>();
        page.setTotal(Long.valueOf(count).intValue());

        int pageNum = pageable.getPageNum();
        int pageSize = pageable.getPageSize();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);

        int yu = count % pageSize;
        if (yu != 0 && page.getPages() > 1) {
            //前面将页码反转，现在如果是查询第一页,100=100
            if (pageNum == 1) {
                query.limit(yu);
            } else {
                query.skip((pageNum - 2) * pageSize + yu).limit(pageSize);
            }

        } else {
            query.skip((pageNum - 1) * pageSize).limit(pageSize);
        }

        /**
         * total=(pageNum-1) * pageSize+ pageSize
         * x=pageSize
         *
         */
        List<Object> rows = (List<Object>) find(query, entityClass);
        page.setList(rows);
        return page;
    }

    /**
     * 分页查询
     *
     * @param pageable       分页查询
     * @param query          查询条件
     * @param collectionName 表名
     * @param entityClass    class
     * @return
     */
    public PageInfo<Object> findPage(Page pageable, Query query, String collectionName, Class<?> entityClass) {
        PageInfo<Object> page = new PageInfo<>();
        long count = count(query, entityClass, collectionName);
        page.setTotal(Long.valueOf(count).intValue());

        int pageNum = pageable.getPageNum();
        int pageSize = pageable.getPageSize();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);

        query.skip((pageNum - 1) * pageSize).limit(pageSize);
        List<Object> rows = findList(query, (Class<Object>) entityClass, collectionName);
        page.setList(rows);
        return page;
    }

    /**
     * 获取数量
     *
     * @param query          查询条件
     * @param collectionName 表名
     * @return
     */
    public long count(Query query, String collectionName) {
        return this.mongoTemplate.count(query, collectionName);
    }

    /**
     * 获取数量
     *
     * @param query       查询条件
     * @param entityClass class
     * @return
     */
    public long count(Query query, Class<?> entityClass) {
        return this.mongoTemplate.count(query, entityClass);
    }

    /**
     * 获取数量
     *
     * @param query          查询条件
     * @param collectionName 表名
     * @param entityClass    class
     * @return
     */
    public long count(Query query, Class<?> entityClass, String collectionName) {
        return this.mongoTemplate.count(query, entityClass, collectionName);
    }

    /**
     * 获取mgdb 模板
     *
     * @return
     */
    public MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }
}
