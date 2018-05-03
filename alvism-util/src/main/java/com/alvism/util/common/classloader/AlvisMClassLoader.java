package com.alvism.util.common.classloader;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类加载器工具类
 * Created by JiaMingChen on 2018/4/13.
 */
public class AlvisMClassLoader {

    private static final Lock lock = new ReentrantLock();

    private static AlvisMClassLoader instance;

    // 使用乐观锁方式解决多线程并发导致单例失败问题
    public static AlvisMClassLoader getInstance(){
        if(instance == null){
            lock.lock();
            try{
                if(instance == null){
                    instance = new AlvisMClassLoader();
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * 获取类加载器
     * @return
     */
    public ClassLoader getClassLoader(){
        return AlvisMClassLoader.class.getClassLoader();
    }

    /**
     * 获取类加载器
     * @param o 相关对象
     * @return
     */
    public ClassLoader getClassLoader(Object o){
        return o.getClass().getClassLoader();
    }

}
