package com.wr.tv.adaper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainAdapter(fm: FragmentManager?,var mData:List<Fragment>?,var titles:Array<String>) : FragmentPagerAdapter(fm) {
    fun setData(data: List<Fragment>) {
        this.mData = data
    }

    override fun getItem(position: Int): Fragment {
        return mData!!.get(position)
    }

    override fun getCount(): Int {
        return mData?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}