package com.wr.movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wr.base.BaseActivity
import com.wr.base.router.RouteUtil
import com.wr.movie.R

@Route(path = RouteUtil.MOVIE_MAIN_MODULE)
class MainMovieActivity : BaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_main_movie
    }

    override fun initListener() {
        super.initListener()
    }

}
