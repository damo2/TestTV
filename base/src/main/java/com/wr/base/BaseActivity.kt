package com.wr.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate

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