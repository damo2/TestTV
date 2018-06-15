package com.wr.comic.api

import com.wr.comic.db.DBChapters
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by wangru
 * Date: 2018/6/15  15:56
 * mail: 1902065822@qq.com
 * describe:
 */
interface ComicService {
    @GET("getChapterList/{id}/{chapter}")
    fun getChapters(@Path("id") id: String, @Path("chapter") chapter: String): Observable<DBChapters>
}