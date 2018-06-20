package com.wr.tv

import com.alibaba.android.arouter.launcher.ARouter
import com.wr.base.BaseActivity
import com.wr.base.router.RouteUtil

class IndexActivity : BaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_index;
    }

    override fun initView() {
        super.initView()
        ARouter.getInstance().build(RouteUtil.APP_MAIN).navigation()
    }

}
