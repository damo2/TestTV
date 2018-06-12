package com.wr.comic.adapter

import android.content.Context
import com.leimo.common.adapter.MultiItemTypeAdapter
import com.wr.comic.bean.MainTitleBean

class MainAdapter(var context: Context, var data: List<MainTitleBean>) : MultiItemTypeAdapter<MainTitleBean>(context, data) {
    init {
        addItemViewDelegate(MainItemDelegate())
        addItemViewDelegate(MainItemType())
    }
}
