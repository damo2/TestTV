package com.wr.comic.ui.activity

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.leimo.common.adapter.CommonAdapter
import com.leimo.common.adapter.layoutrecycle.FullyLinearLayoutManager
import com.leimo.common.adapter.util.ViewHolder
import com.leimo.common.loadimg.ImageLoaderInter
import com.leimo.common.loadimg.ImageLoaderUtil
import com.wr.base.BaseActivity
import com.wr.base.router.RouteUtil
import com.wr.comic.R
import com.wr.comic.api.request.MainRequest
import com.wr.comic.bean.ComicBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main_comic_detail.*

/**
 * Created by wangru
 * Date: 2018/6/14  19:33
 * mail: 1902065822@qq.com
 * describe:
 */
@Route(path = RouteUtil.COMIC_DETAIL)
class ComicDetailActivity : BaseActivity() {
    @JvmField
    @Autowired
    var id: Long = 0
    @JvmField
    @Autowired
    var title: String = ""

    var mAdapter: CommonAdapter<String>? = null
    //章节标题
    var mChapters = ArrayList<String>()
    var mChaptersUrl = ArrayList<String>()

    override fun bindLayout(): Int {
        return R.layout.activity_main_comic_detail
    }

    override fun initData() {
        super.initData()
        ARouter.getInstance().inject(this@ComicDetailActivity);
        Log.d(TAG, "title=" + title + "# id=" + id)
    }

    override fun initView() {
        super.initView()
        initAdapter()
    }

    override fun initValue() {
        super.initValue()
        getData()
    }

    private fun getData() {
        MainRequest.getComicDetail(id, object : Observer<ComicBean> {
            override fun onNext(t: ComicBean) {
                setValue(t);
            }

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }

        })
    }

    private fun setValue(comic: ComicBean?) {
        comic?.let {
            tv_desc.text = comic.describe
            tv_popularity.text = comic.popularity
            tv_point.text = comic.point
            tv_status.text = comic.status
            tv_update.text = comic.updates
            mChapters.clear()
            mChapters.addAll(comic.chapters)
            mChaptersUrl.clear()
            mChaptersUrl.addAll(comic.chaptersUrl)
            mAdapter?.notifyDataSetChanged()

            ImageLoaderUtil.loadImage(this@ComicDetailActivity,iv_topImg,comic.cover, ImageLoaderInter().setCenterCrop(true))
        }
    }

    fun initAdapter() {
        mAdapter = object : CommonAdapter<String>(applicationContext, R.layout.item_comic_detail_item, mChapters) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                holder?.let {
                    holder.setText(R.id.tv_comic_detail_item, t)
                }
            }
        }
        val layoutManager = FullyLinearLayoutManager(applicationContext)
        layoutManager.setScrollEnabled(false)
        recyclerview_item.layoutManager = layoutManager
        recyclerview_item.adapter = mAdapter
    }
}