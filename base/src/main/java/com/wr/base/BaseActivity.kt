package com.wr.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseActivity : AppCompatActivity() {
    var TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(bindLayout())
        initData()
        initView()
        initValue()
        initListener()
    }
    fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    abstract fun bindLayout(): Int
    protected open fun initView() {}
    protected open fun initValue() {}
    protected open fun initData() {}
    protected open fun initListener() {}
}