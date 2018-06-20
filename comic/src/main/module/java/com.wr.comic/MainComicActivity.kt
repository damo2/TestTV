package com.wr.comic


import com.alibaba.android.arouter.facade.annotation.Route
import com.wr.base.BaseActivity
import com.wr.base.router.RouteUtil

@Route(path = RouteUtil.COMIC_MAIN_MODULE)
class MainComicActivity : BaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_main_comic
    }

    override fun initListener() {
        super.initListener()
    }

}
