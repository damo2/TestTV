package com.wr.comic.adapter

import android.content.Context
import com.leimo.common.adapter.MultiItemTypeAdapter
import com.wr.comic.bean.ComicBean

class MainAdapter(var context:Context,var data:List<ComicBean>): MultiItemTypeAdapter<ComicBean>(context,data) {
    init {
        addItemViewDelegate(MainItemDelegate())
        addItemViewDelegate(MainItemType())
    }
}
