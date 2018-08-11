package com.example.demo.cache;

import com.example.demo.utils.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 用的redis的hash结构  每个namespace作为key  清除的时候删除整个namespace的缓存
 * 粒度还是比较大的 而且连表查询会有脏数据问题 需要通过mapper里配置《cache-ref》
 * @author: swg
 * @created: 2018/8/11
 */
public class RedisCache implements Cache {

    private String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RedisCache(String id) {
        this.id = id;
    }

    private RedisTemplate<Object, Object> getRedisTemplate(){
        return ApplicationContextHolder.getBean("redisTemplate");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        getRedisTemplate().boundHashOps(getId()).put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return getRedisTemplate().boundHashOps(getId()).get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return getRedisTemplate().boundHashOps(getId()).delete(key);
    }

    @Override
    public void clear() {
        getRedisTemplate().delete(getId());
    }

    @Override
    public int getSize() {
        Long size = getRedisTemplate().boundHashOps(getId()).size();
        return size == null ? 0 : size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
