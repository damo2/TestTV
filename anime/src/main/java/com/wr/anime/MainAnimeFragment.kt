package com.wr.anime

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.wr.anime.R.id.recyclerview_main_anime
import com.wr.anime.adapter.MainAdapter
import com.wr.anime.bean.AnimeBean
import com.wr.base.BaseFragment
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class MainAnimeFragment : BaseFragment() {
    private var mAdapter: MainAdapter? = null
    private var mList: ArrayList<AnimeBean>? = null
    private var mRecyclerView: RecyclerView? = null
    override fun setLayoutResouceId(): Int {
        return R.layout.fragment_main_anime
    }

    companion object {
        fun newInstance(): MainAnimeFragment {
            val fragment = MainAnimeFragment()
            return fragment
        }
    }

    init {
        mRootView?.let {
            mRecyclerView = mRootView?.findViewById(R.id.recyclerview_main_anime) as RecyclerView
        }

    }

    override fun initView() {
        super.initView()
        mAdapter = MainAdapter(context, mList)
        initAdapter()
    }

    override fun initValue() {
        super.initValue()
        mList=ArrayList()
        for (i in 0..5) {
            mList?.add(AnimeBean(1, "title" + i))
        }
        for (i in 0..5) {
            mList?.add(AnimeBean(2, "type" + i))
        }
        mAdapter?.data=mList
        mAdapter?.notifyDataSetChanged()
    }

    private fun initAdapter() {
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.adapter = mAdapter
    }


}