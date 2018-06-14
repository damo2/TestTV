package com.wr.comic.ui.activity

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.wr.base.BaseActivity
import com.wr.comic.R

/**
 * Created by wangru
 * Date: 2018/6/14  19:33
 * mail: 1902065822@qq.com
 * describe:
 */
@Route(path = "/com/Activity1")
class ComicDetailActivity : BaseActivity() {

    override fun bindLayout(): Int {
        return R.layout.activity_main_comic_detail
    }

}