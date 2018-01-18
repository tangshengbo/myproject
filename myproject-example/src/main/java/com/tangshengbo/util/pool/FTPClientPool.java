package com.tangshengbo.util.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2018/1/11.
 */
public class FTPClientPool implements ObjectPool<FTPClient> {

    private BlockingQueue<FTPClient> pool;

    private FTPClientFactory factory;

    public FTPClientPool(FTPClientFactory factory, int poolSize) throws Exception {
        this.factory = factory;
        pool = new ArrayBlockingQueue<>(poolSize);
        initPool(poolSize);
    }

    /**
     * 初始化连接池，需要注入一个工厂来提供FTPClient实例
     *
     * @param maxPoolSize
     * @throws Exception
     */
    private void initPool(int maxPoolSize) throws Exception {
        for (int i = 0; i < maxPoolSize; i++) {
            //往池中添加对象
            addObject();
        }
    }

    /**
     * 获取一个FTPClient实例
     *
     * @return
     * @throws Exception
     */
    public FTPClient borrowObject() throws Exception {
        FTPClient client = pool.take();
        if (!factory.validateObject(client)) {
            //使对象在池中失效
            invalidateObject(client);
            //制造并添加新对象到池中
            client = factory.makeObject();
            addObject();
        }
        return client;

    }

    /**
     * 回收对象
     *
     * @param client
     * @throws Exception
     */
    public void returnObject(FTPClient client) throws Exception {
        if ((client != null) && !pool.offer(client, 3, TimeUnit.SECONDS)) {
            factory.destroyObject(client);
        }
    }

    public void invalidateObject(FTPClient client) throws Exception {
        //移除无效的客户端
        pool.remove(client);
    }

    public void addObject() throws Exception {
        addObject(factory.makeObject());
    }

    private void addObject(FTPClient client) throws Exception {
        //插入对象到队列
        pool.offer(client, 3, TimeUnit.SECONDS);
    }

    public int getNumIdle() throws UnsupportedOperationException {
        return 0;
    }

    public int getNumActive() throws UnsupportedOperationException {
        return 0;
    }

    public void clear() throws Exception {

    }

    /**
     * 关闭连接池所有连接
     *
     * @throws Exception
     */
    public void close() throws Exception {
        while (pool.iterator().hasNext()) {
            FTPClient client = pool.take();
            factory.destroyObject(client);
        }
    }

    @Override
    public void setFactory(PoolableObjectFactory<FTPClient> poolableObjectFactory) throws IllegalStateException, UnsupportedOperationException {
    }
}
