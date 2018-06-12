package com.leimo.common.thread;

/**
 * Created by wangru
 * Date: 2018/1/27  14:40
 * mail: 1902065822@qq.com
 * describe:
 */

public class PriorityRunnable implements Runnable {

    private final Priority priority;

    public PriorityRunnable(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        // nothing to do here.
    }

    public Priority getPriority() {
        return priority;
    }
}