package com.wr.anime

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.wr.anime.R.id.recyclerview_main_anime
import com.wr.anime.adapter.MainAdapter
import com.wr.anime.bean.AnimeBean
import com.wr.anime.constant.TypeConstant
import com.wr.base.BaseFragment

class MainAnimeFragment : BaseFragment() {
    private var mAdapter: MainAdapter? = null
    private val mList=ArrayList<AnimeBean>()
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

    override fun initData() {
        super.initData()
        for (i in 0..5) {
            mList.add(AnimeBean(TypeConstant.Main.Recommend, "title" + i))
        }
        for (i in 0..5) {
            mList.add(AnimeBean(TypeConstant.Main.Type, "type" + i))
        }
    }

    override fun initView() {
        super.initView()
        mRootView?.let {
            mRecyclerView = mRootView?.findViewById(R.id.recyclerview_main_anime) as RecyclerView
        }
        initAdapter()
    }

    override fun initValue() {
        super.initValue()
    }

    private fun initAdapter() {
        mAdapter = MainAdapter(context, mList)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.adapter = mAdapter
    }


}