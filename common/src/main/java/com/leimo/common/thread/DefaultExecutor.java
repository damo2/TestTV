package com.leimo.common.thread;

import android.os.Process;

import java.util.concurrent.*;

/**
 * 线程池
 * Created by wangru
 * Date: 2018/1/27  14:30
 * mail: 1902065822@qq.com
 * describe:
 */

public class DefaultExecutor {
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static DefaultExecutor sInstance;
    private final PriorityThreadPoolExecutor mForBackgroundTasks;
    private final ExecutorService mCachedThreadPool;
    private final ExecutorService mFixedThreadPool;

    private DefaultExecutor() {
        mForBackgroundTasks = new PriorityThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES, 60L, TimeUnit.SECONDS, new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND));
        mCachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        mFixedThreadPool = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static DefaultExecutor getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutor.class) {
                sInstance = new DefaultExecutor();
            }
        }
        return sInstance;
    }

    /**
     * 用于执行后台任务
     * @return
     */
    public PriorityThreadPoolExecutor getForBackgroundTasks() {
        return mForBackgroundTasks;
    }

    public ExecutorService getCachedThreadPool() {
        return mCachedThreadPool;
    }

    public ExecutorService getFixedThreadPool() {
        return mFixedThreadPool;
    }
}
