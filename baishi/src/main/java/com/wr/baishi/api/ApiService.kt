package com.wr.comic.api

import com.wr.baishi.bean.DataVideoBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by wangru
 * Date: 2018/6/15  15:56
 * mail: 1902065822@qq.com
 * describe:
 */
interface ApiService {

    @GET("/post/baisibudejie")
    fun getBaishiData(@Query("apikey") apikey: String, @Query("catid") id: Int, @Query("pageToken") pageToken: String?): Observable<DataVideoBean>
    @GET("/post/baisibudejie?catid=41&apikey=ERWbzCNCoCj7oRaVEJeNMFKz5VJiDzWLb6cQpsuMeyhPL3GQPn9fQbr9DuL16kam")
    fun getBaishiDataTest() : Observable<DataVideoBean>
}