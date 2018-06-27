package com.wr.baishi.api

import com.wr.baishi.api.retrofit.RetrofitRequest

/**
 * Created by wangru
 * Date: 2018/6/15  16:20
 * mail: 1902065822@qq.com
 * describe:
 */
class RequestFactory {
    companion object {
        var comicService: ApiService? = null
        fun getApiServiceInstance(): ApiService {
            synchronized(this) {
                if (comicService == null) {
                    comicService = RetrofitRequest().service
                }
                return comicService as ApiService
            }
        }
    }

}