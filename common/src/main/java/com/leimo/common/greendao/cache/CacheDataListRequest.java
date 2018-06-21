package com.leimo.common.greendao.cache;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.leimo.common.greendao.bean.CacheInfoEntity;
import com.leimo.common.greendao.ope.CacheInfoDaoOpe;
import com.leimo.common.json.GsonConvert;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 缓存数据和取缓存数据
 * Created by wangru
 * Date: 2018/5/10  15:26
 * mail: 1902065822@qq.com
 * describe:
 */

public class CacheDataListRequest<I> {
    public void getCache(final String cacheKey, final Class<I> type, final CacheDataListener<List<I>> cacheDataListener) {
        Observable.create(new ObservableOnSubscribe<List<I>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<I>> emitter) throws Exception {
                String data = CacheInfoDaoOpe.getCacheInfoDataByKey(cacheKey);
                if (!TextUtils.isEmpty(data)) {

                    List<I> info= GsonConvert.jsonToBeanList(data,type);
                    if (info != null) {
                        emitter.onNext(info);
                    }
                }
                emitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<I>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<I> info) {
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

    public void saveCache(final String cacheKey, final List<I> info, final CacheDataListener<CacheInfoEntity> cacheDataListener) {
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


    public void getCache2(final String cacheKey, final Class<I> type, final Type typeJ, final JsonDeserializer jsonDeserializer, final CacheDataListener<List<I>> cacheDataListener) {
        Observable.create(new ObservableOnSubscribe<List<I>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<I>> emitter) throws Exception {
                String data = CacheInfoDaoOpe.getCacheInfoDataByKey(cacheKey);
                if (!TextUtils.isEmpty(data)) {
                    List<I> info= GsonConvert.jsonToBeanList2(data,type,typeJ,jsonDeserializer);
                    if (info != null) {
                        emitter.onNext(info);
                    }
                }
                emitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<I>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<I> info) {
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

}
