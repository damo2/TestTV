package com.wr.movie

import com.wr.base.BaseFragment
import android.support.v7.widget.RecyclerView
import com.app.common.adapter.layoutrecycle.FullyLinearLayoutManager

class MainMovieFragment : BaseFragment() {
    lateinit var mRecyclerView: RecyclerView
    override fun setLayoutResouceId(): Int {
        return R.layout.fragment_main_movie
    }

    companion object {
        fun newInstance(): MainMovieFragment {
            val fragment = MainMovieFragment()
            return fragment
        }
    }

    override fun initView() {
        super.initView()
        mRecyclerView = mRootView.findViewById<RecyclerView>(R.id.recyclerView_movie)
    }

    override fun initValue() {
        super.initValue()
        initAdapter()
    }

    private fun initAdapter() {
        mRecyclerView.layoutManager= FullyLinearLayoutManager(context)

    }
}