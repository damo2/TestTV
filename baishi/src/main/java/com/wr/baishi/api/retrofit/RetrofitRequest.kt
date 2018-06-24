package com.wr.comic.api.retrofit

import com.wr.baishi.api.UrlBaishi
import com.wr.base.retrofit.util.ProgressListener
import com.wr.base.retrofit.util.ProgressResponseBody
import com.wr.comic.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by 皓然 on 2017/7/5.
 */

internal class RetrofitRequest {
    open lateinit var service: ApiService
    val BASE_URL= UrlBaishi.URL_BASE
    private val retrofit: Retrofit

    init {
        val client = OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val orginalResponse = chain.proceed(chain.request())
                    orginalResponse.newBuilder()
                            .body(ProgressResponseBody(orginalResponse.body()!!, object : ProgressListener {
                                override fun onProgress(progress: Long, total: Long, done: Boolean) {
                                }
                            }))
                            .build()
                }
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()


        retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        service = retrofit.create(ApiService::class.java)
    }

    companion object {
        private val DEFAULT_TIMEOUT = 5
    }


}
