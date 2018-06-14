package com.wr.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(bindLayout())
        initData()
        initView()
        initValue()
        initListener()
    }

    abstract fun bindLayout(): Int
    protected open fun initView() {}
    protected open fun initValue() {}
    protected open fun initData() {}
    protected open fun initListener() {}
}