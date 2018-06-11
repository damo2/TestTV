package com.wr.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup

abstract class BaseFragment :Fragment(){
    protected var mRootView: View?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView=inflater!!.inflate(setLayoutResouceId(), container, false)
        initValue()
        return mRootView
    }
    protected abstract fun setLayoutResouceId(): Int

    protected open fun initView(){}
    protected open fun initValue(){}
}