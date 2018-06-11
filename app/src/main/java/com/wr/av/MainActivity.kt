package com.wr.av

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.widget.Toast
import com.wr.anime.MainAnimeFragment
import com.wr.anime.PlusOneFragment
import com.wr.av.adaper.MainAdapter
import com.wr.base.BaseActivity
import com.wr.movie.MainMovieFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity() {

    var mAdapter: MainAdapter?=null
    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    init {

    }

    override fun initView() {
        super.initView()
        setViewPgerAdapter()
    }

    override fun initValue() {
        super.initValue()
        var mFragmentList = listOf<Fragment>(PlusOneFragment(),PlusOneFragment(), PlusOneFragment())
        mAdapter= MainAdapter(supportFragmentManager,mFragmentList)
        mAdapter?.notifyDataSetChanged()
    }

    override fun initListener() {
        super.initListener()
    }

    private fun setViewPgerAdapter() {
        viewpager_main.adapter = mAdapter
    }
}
