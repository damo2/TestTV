package com.wr.comic

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.LinearLayout
import com.leimo.common.adapter.wrapper.HeaderAndFooterWrapper
import com.leimo.common.app.DensityUtils
import com.leimo.common.log.LogUtil
import com.wr.comic.adapter.MainAdapter
import com.wr.comic.util.GlideImageLoader
import com.wr.base.BaseFragment
import com.wr.comic.api.request.MainRequest
import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.MainBanner
import com.youth.banner.Banner
import com.youth.banner.Transformer
import io.reactivex.observers.DisposableObserver
import kotlin.collections.ArrayList

class MainComicFragment : BaseFragment() {
    private var mAdapter: MainAdapter? = null
    private var mAdapterHeader: HeaderAndFooterWrapper<String>? = null
    private val mList = ArrayList<ComicBean>()
    private var mRecyclerView: RecyclerView? = null
    private var mBanner: Banner? = null;
    private val mBannerList=ArrayList<MainBanner>()

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
    }

    override fun initView() {
        super.initView()
        mRootView?.let {
            mRecyclerView = mRootView?.findViewById(R.id.recyclerview_main_comic) as RecyclerView
        }
        initAdapter()
    }

    override fun initValue() {
        super.initValue()
        setBannerData()
    }

    private fun setBannerData() {
        MainRequest.getBanner(object :DisposableObserver<List<MainBanner>>(){
            override fun onNext(t: List<MainBanner>) {
                mBannerList.clear()
                mBannerList.addAll(t)
                val imgList=ArrayList<String>()
                val titleList=ArrayList<String>()
                for (i in mBannerList.indices){
                    imgList.add(mBannerList.get(i).img!!)
                    titleList.add(mBannerList.get(i).title!!)
                }
                LogUtil.v("banner"+imgList.toArray().toString())
                mBanner?.setImages(imgList)
                mBanner?.setBannerTitles(titleList);
                mBanner?.start()
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })

        MainRequest.getRankList(object :DisposableObserver<List<ComicBean>>(){
            override fun onNext(t: List<ComicBean>) {
                mList.addAll(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })
    }

    private fun initAdapter() {

        mRecyclerView?.layoutManager = LinearLayoutManager(context)

        mAdapter = MainAdapter(context, mList)
        mAdapterHeader = HeaderAndFooterWrapper(mAdapter)
        mBanner = Banner(context)
        mBanner?.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(context,200)
        )

        mAdapterHeader!!.addHeaderView(mBanner)
        mRecyclerView?.adapter = mAdapterHeader


        mBanner?.setBannerAnimation(Transformer.Accordion);
        mBanner?.setImageLoader(GlideImageLoader())

    }

    override fun onDestroy() {
        //banner图停止自动播放
        mBanner?.stopAutoPlay()
        super.onDestroy()
    }

}