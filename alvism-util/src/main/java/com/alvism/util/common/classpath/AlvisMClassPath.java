package com.alvism.util.common.classpath;

import com.alvism.util.common.classloader.AlvisMClassLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类路径资源工具类
 * Created by JiaMingChen on 2018/4/13.
 */
public class AlvisMClassPath {

    private static final Lock lock = new ReentrantLock();

    private static AlvisMClassPath instance;

    // 使用乐观锁方式解决多线程并发导致单例失败问题
    public static AlvisMClassPath getInstance(){
        if(instance == null){
            lock.lock();
            try{
                if(instance == null){
                    instance = new AlvisMClassPath();
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private AlvisMClassLoader alvisMClassLoader = AlvisMClassLoader.getInstance();

    /**
     * 获取类路径资源
     * @param path 路径
     * @return
     */
    public URL getResource(String path){
        return alvisMClassLoader.getClassLoader().getResource(path);
    }

    /**
     * 获取类路径资源输入流
     * @param path
     * @return
     * @throws IOException
     */
    public InputStream getResourceAsStream(String path) throws IOException {
        return this.getResource(path).openStream();
    }

    /**
     * 获取类路径资源所在系统路径
     * @param path
     * @return
     */
    public String getSystemPath(String path){
        return this.getResource(path).getFile();
    }

}
