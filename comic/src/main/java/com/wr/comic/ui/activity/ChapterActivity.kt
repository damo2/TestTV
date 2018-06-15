package com.wr.comic.ui.activity

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.leimo.common.adapter.CommonAdapter
import com.leimo.common.adapter.layoutrecycle.FullyLinearLayoutManager
import com.leimo.common.adapter.util.ViewHolder
import com.wr.base.BaseActivity
import com.wr.base.router.RouteUtil
import com.wr.comic.R
import kotlinx.android.synthetic.main.activity_comic_chapter.*

/**
 * Created by wangru
 * Date: 2018/6/15  17:21
 * mail: 1902065822@qq.com
 * describe:
 */
@Route(path = RouteUtil.COMIC_CHAPTER)
class ChapterActivity : BaseActivity() {
    var mAdapter: CommonAdapter<String>? = null
    @JvmField
    @Autowired(name = "listImg")
    var mListImg = ArrayList<String>()

    override fun bindLayout(): Int {
        return R.layout.activity_comic_chapter
    }

    override fun initValue() {
        super.initValue()
        initAdapter()
    }

    private fun initAdapter() {
        mAdapter = object : CommonAdapter<String>(applicationContext, R.layout.item_comic_chapter, mListImg) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {

            }
        }
        recyclerview_chapter.layoutManager=FullyLinearLayoutManager(applicationContext)
        recyclerview_chapter.adapter=mAdapter
    }
}