package com.wr.av.adaper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainAdapter(fm: FragmentManager?,var mData:List<Fragment>?) : FragmentPagerAdapter(fm) {
    fun setData(data: List<Fragment>) {
        mData = data
    }

    override fun getItem(position: Int): Fragment {
        return mData!!.get(position)
    }

    override fun getCount(): Int {
        return mData?.size ?: 0
    }
}