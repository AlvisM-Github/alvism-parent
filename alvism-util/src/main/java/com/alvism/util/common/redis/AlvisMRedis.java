package com.alvism.util.common.redis;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis工具类
 * Created by JiaMingChen on 2018/4/13.
 */
public class AlvisMRedis{

    private static final Lock lock = new ReentrantLock();

    private static final String REDIS_HOST = "127.0.0.1";
    private static final int REDIS_PORT = 6379;

    private static Map<String, AlvisMRedis> instances = new ConcurrentHashMap<>();
    private Jedis jedis;

    // 使用乐观锁方式解决多线程并发导致单例失败问题
    public static AlvisMRedis getInstance(String host, int port){
        AlvisMRedis instance;
        instance = instances.get(host + ":" + port);
        if(instance == null){
            lock.lock();
            try{
                if((instance = instances.get(host + ":" + port)) == null){
                    instance = new AlvisMRedis();
                    instance.initRedisClient(host, port);
                    instances.put(host + ":" + port, instance);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static AlvisMRedis getInstance(String host){
        return AlvisMRedis.getInstance(host, REDIS_PORT);
    }

    public static AlvisMRedis getInstance(){
        return AlvisMRedis.getInstance(REDIS_HOST, REDIS_PORT);
    }

    // 初始化Redis客户端
    public void initRedisClient(String host, int port){
        jedis = new Jedis(host, port);
    }

    // 添加字符串
    public void setString(String key, String value){
        jedis.set(key, value);
    }

    // 获取字符串
    public String getString(String key){
        return jedis.get(key);
    }

    public static void main(String[] args) {
        AlvisMRedis alvisMRedis = AlvisMRedis.getInstance();
        AlvisMRedis alvisMRedis1 = AlvisMRedis.getInstance("39.108.122.119");
        AlvisMRedis alvisMRedis2 = AlvisMRedis.getInstance("39.108.122.119");
        System.out.println(alvisMRedis);
        System.out.println(alvisMRedis1);
        System.out.println(alvisMRedis2);
        alvisMRedis.setString("test11", "陈嘉明");
        System.out.println(alvisMRedis.getString("test"));
        //alvisMRedis1.setString("test", "陈嘉明1");
        System.out.println(alvisMRedis1.getString("test11"));
    }

}
