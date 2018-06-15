package com.wr.comic.api

import com.wr.comic.api.retrofit.RetrofitRequest

/**
 * Created by wangru
 * Date: 2018/6/15  16:20
 * mail: 1902065822@qq.com
 * describe:
 */
class RequestFactory {
    companion object {
        var comicService: ComicService ? = null
        fun getComicServiceInstance(): ComicService {
            synchronized(this) {
                if (comicService == null) {
                    comicService = RetrofitRequest().service
                }
                return comicService as ComicService
            }
        }
    }

}