package com.wr.baishi.api

import com.wr.baishi.bean.DataVideoBean
import com.wr.baishi.constant.NetConst
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Administrator on 2018/6/24.
 */

class ApiRequest {
    companion object {
        fun getVideoData(pageToken: String, observer: Observer<DataVideoBean>) {
            RequestFactory.getApiServiceInstance().getBaishiData(UrlBaishi.APP_KEY, NetConst.CatId.VIDEO, pageToken).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
        }
    }
}
