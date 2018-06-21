package com.leimo.common.greendao.cache;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.leimo.common.greendao.bean.CacheInfoEntity;
import com.leimo.common.greendao.ope.CacheInfoDaoOpe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 缓存数据和取缓存数据
 * Created by wangru
 * Date: 2018/5/10  15:26
 * mail: 1902065822@qq.com
 * describe:
 */

public class CacheDataRequest<I> {
    public void getCache(final String cacheKey, final Class<I> type, final CacheDataListener<I> cacheDataListener) {
        Observable.create(new ObservableOnSubscribe<I>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<I> emitter) throws Exception {
                String data = CacheInfoDaoOpe.getCacheInfoDataByKey(cacheKey);
                if (!TextUtils.isEmpty(data)) {
                    I info = new Gson().fromJson(data, type);
                    if (info != null) {
                        emitter.onNext(info);
                    }
                }
                emitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<I>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(@NonNull I info) {
                if (cacheDataListener != null) {
                    cacheDataListener.onSuccess(info);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (cacheDataListener != null) {
                    cacheDataListener.onFail(e);
                }
            }

            @Override
            public void onComplete() {
                if (cacheDataListener != null) {
                    cacheDataListener.onComplete();
                }
            }
        });
    }

    public void saveCache(final String cacheKey, final I info, final CacheDataListener<CacheInfoEntity> cacheDataListener) {
        Observable.create(new ObservableOnSubscribe<CacheInfoEntity>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<CacheInfoEntity> emitter) throws Exception {
                CacheInfoEntity cacheInfo = new CacheInfoEntity();
                cacheInfo.setTimeCreate(System.currentTimeMillis());
                cacheInfo.setKey(cacheKey);
                cacheInfo.setData(new Gson().toJson(info));
                CacheInfoDaoOpe.insertOrUpdateVersionByKey(cacheKey, cacheInfo);
                if (cacheInfo != null) {
                    emitter.onNext(cacheInfo);
                }
                emitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<CacheInfoEntity>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(@NonNull CacheInfoEntity cacheInfo) {
                if (cacheDataListener != null) {
                    cacheDataListener.onSuccess(cacheInfo);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (cacheDataListener != null) {
                    cacheDataListener.onFail(e);
                }
            }

            @Override
            public void onComplete() {
                if (cacheDataListener != null) {
                    cacheDataListener.onComplete();
                }
            }
        });
    }

}
