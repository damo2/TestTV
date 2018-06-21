package com.leimo.common.greendao.cache;

/**
 * Created by wangru
 * Date: 2018/5/10  15:30
 * mail: 1902065822@qq.com
 * describe:
 */

public interface CacheDataListener<I> {
    void onSuccess(I i);
    void onFail(Throwable error);
    void onComplete();
}
