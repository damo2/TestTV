package com.wr.comic

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.leimo.common.CommonInit
import com.leimo.common.adapter.MultiItemTypeAdapter
import com.leimo.common.adapter.layoutrecycle.FullyGridLayoutManager
import com.leimo.common.adapter.wrapper.HeaderAndFooterWrapper
import com.leimo.common.app.DensityUtils
import com.leimo.common.app.ScreenUtils
import com.leimo.common.greendao.cache.CacheDataListRequest
import com.leimo.common.greendao.cache.CacheDataListener
import com.leimo.common.log.LogSD
import com.leimo.common.log.LogUtil
import com.leimo.common.path.SDPathUtils
import com.wr.base.BaseFragment
import com.wr.base.router.RouteUtil
import com.wr.comic.adapter.MainAdapter
import com.wr.comic.api.UrlTencentComic
import com.wr.comic.api.request.MainRequest
import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.ComicRankListBean
import com.wr.comic.bean.MainBanner
import com.wr.comic.constant.LocConst
import com.wr.comic.util.Deserializer
import com.wr.comic.util.GlideImageLoader
import com.youth.banner.Banner
import com.youth.banner.Transformer
import io.reactivex.observers.DisposableObserver

class MainComicFragment : BaseFragment() {
    private var mAdapter: MainAdapter? = null
    private var mAdapterHeader: HeaderAndFooterWrapper<String>? = null
    private val mList = ArrayList<ComicBean>()
    private var mRecyclerView: RecyclerView? = null
    private var mBanner: Banner? = null;
    private val mBannerList = ArrayList<MainBanner>()

    override fun setLayoutResouceId(): Int {
        return R.layout.fragment_main_comic
    }

    override fun onStart() {
        super.onStart()
        //banner图开始自动播放
        mBanner?.startAutoPlay()
    }

    companion object {
        fun newInstance(): MainComicFragment {
            val fragment = MainComicFragment()
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        CommonInit.initDB(context)
        LogSD.setIsDebug(true)
        LogSD.setRootDirDefault(SDPathUtils.getSDCardPublicDir("tv/comic"))
    }

    override fun initView() {
        super.initView()
        mRootView?.let {
            mRecyclerView = mRootView!!.findViewById<RecyclerView>(R.id.recyclerview_main_comic)
        }
        initAdapter()
    }

    override fun initValue() {
        super.initValue()
        getDataByCache()
        getBannerData()
    }

    private fun getBannerData() {
        MainRequest.getBanner(object : DisposableObserver<List<MainBanner>>() {
            override fun onNext(t: List<MainBanner>) {
                CacheDataListRequest<MainBanner>().saveCache(UrlTencentComic.Banner, t, null)
                setDataBanner(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })

        MainRequest.getRankList(object : DisposableObserver<List<ComicBean>>() {
            override fun onNext(t: List<ComicBean>) {
                Log.d(TAG, "save cache:" + Gson().toJson(t))
                CacheDataListRequest<ComicBean>().saveCache(LocConst.CacheKey.COMIC_LIST, t, null)
                setDataComic(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })
    }

    private fun getDataByCache() {
        CacheDataListRequest<MainBanner>().getCache(UrlTencentComic.Banner, MainBanner::class.java, object : CacheDataListener<List<MainBanner>> {
            override fun onSuccess(i: List<MainBanner>?) {
                i.let {
                    Log.d(TAG, "cache:" + Gson().toJson(i))
                    setDataBanner(i!!)
                }
            }

            override fun onFail(error: Throwable?) {
            }

            override fun onComplete() {
            }
        })
        CacheDataListRequest<ComicBean>().getCache2(LocConst.CacheKey.COMIC_LIST, ComicBean::class.java, ComicBean::class.java, Deserializer.ComicMainDeserializer(), object : CacheDataListener<List<ComicBean>> {
            override fun onSuccess(i: List<ComicBean>?) {
                i.let {
                    LogSD.w("cache", Gson().toJson(i) + "\n\n")
                    Log.d(TAG, "getCache COMIC_LIST:" + Gson().toJson(i))
                    setDataComic(i!!)
                }
            }

            override fun onFail(error: Throwable?) {
            }

            override fun onComplete() {
            }
        })
    }

    private fun setDataComic(i: List<ComicBean>) {
        mList.clear()
        mList.addAll(i)
        mAdapter?.notifyDataSetChanged()
    }

    private fun setDataBanner(list: List<MainBanner>) {
        mBannerList.clear()
        mBannerList.addAll(list)
        val imgList = ArrayList<String>()
        val titleList = ArrayList<String>()
        for (i in mBannerList.indices) {
            imgList.add(mBannerList.get(i).img!!)
            titleList.add(mBannerList.get(i).title!!)
        }
        LogUtil.v("banner" + imgList.toArray().toString())
        mBanner?.setImages(imgList)
        mBanner?.setBannerTitles(titleList);
        mBanner?.start()
    }

    private fun initAdapter() {
        mRecyclerView?.layoutManager = FullyGridLayoutManager(context, LocConst.MAIN_ITEM_NUM)
        mAdapter = MainAdapter(context, mList)
        mAdapterHeader = HeaderAndFooterWrapper(mAdapter)
        mBanner = Banner(context)
        val bannerWidth = ScreenUtils.getScreenWidth(activity) + DensityUtils.dp2px(context, 8)
        val bannerHeight = DensityUtils.dp2px(context, 200)
        var layout = LinearLayout.LayoutParams(bannerWidth, bannerHeight)
        mBanner?.layoutParams = layout

        mAdapterHeader!!.addHeaderView(mBanner)
        mRecyclerView?.adapter = mAdapterHeader

        mBanner?.setBannerAnimation(Transformer.Accordion);
        mBanner?.setImageLoader(GlideImageLoader())

    }

    override fun initListener() {
        super.initListener()
        mAdapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                var comic = mList.get(position - 1)
                when (comic) {
                    is ComicRankListBean -> {
                        ARouter.getInstance().build(RouteUtil.COMIC_DETAIL).withString("title", comic.title).withLong("id", comic.id).navigation()
                    }
                }
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }


    override fun onDestroy() {
        //banner图停止自动播放
        mBanner?.stopAutoPlay()
        super.onDestroy()
    }


}