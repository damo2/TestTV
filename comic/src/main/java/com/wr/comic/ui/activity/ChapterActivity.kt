package com.wr.comic.ui.activity

import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.app.common.adapter.CommonAdapter
import com.app.common.adapter.util.ViewHolder
import com.app.common.greendao.cache.CacheDataListRequest
import com.app.common.greendao.cache.CacheDataListener
import com.app.common.greendao.cache.CacheKey
import com.wr.base.BaseActivity
import com.wr.base.router.RouteUtil
import com.wr.comic.R
import com.wr.comic.api.request.MainRequest
import com.wr.comic.db.DBChapters
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
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
    @Autowired
    var id: Long = 0
    @JvmField
    @Autowired
    var chapterPos: Int = 0
    var mListImg = ArrayList<String>()

    lateinit var mCacheKey: String

    override fun bindLayout(): Int {
        return R.layout.activity_comic_chapter
    }

    override fun initData() {
        super.initData()
        mCacheKey = CacheKey.getKey("$id#$chapterPos")
    }

    override fun initView() {
        super.initView()
        initAdapter()
    }

    override fun initValue() {
        super.initValue()
        Log.d(TAG, "id=" + id + "#chapterPos=" + chapterPos)
        CacheDataListRequest<String>().getCache(mCacheKey,String::class.java,object : CacheDataListener<List<String>> {
            override fun onSuccess(i: List<String>?) {
                mListImg.clear()
                mListImg.addAll(i!!)
                mAdapter?.notifyDataSetChanged()
            }

            override fun onFail(error: Throwable?) {
            }

            override fun onComplete() {
            }

        }
        )

        MainRequest.getDBChapter(id, chapterPos, object : Observer<DBChapters> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }

            override fun onNext(dbChapters: DBChapters) {
                mListImg.clear()
                mListImg.addAll(dbChapters.comiclist as ArrayList<String>)
                mAdapter?.notifyDataSetChanged()
                CacheDataListRequest<String>().saveCache(mCacheKey, dbChapters.comiclist, null)
            }

            override fun onComplete() {
            }
        })
    }


    private fun initAdapter() {
        mAdapter = object : CommonAdapter<String>(applicationContext, R.layout.item_comic_chapter, mListImg) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int, payloads: List<Any>?) {
                holder?.let {
                    holder.setImageLoad(R.id.iv_comic_chapter_img, t)
                }
            }
        }
        recyclerview_chapter.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerview_chapter.adapter = mAdapter

    }
}