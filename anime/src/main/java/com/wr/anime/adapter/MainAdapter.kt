package com.wr.anime.adapter

import android.content.Context
import com.wr.anime.bean.AnimeBean
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter

class MainAdapter(var context:Context,var data:List<AnimeBean>?): MultiItemTypeAdapter<AnimeBean>(context,data) {
    init {
        addItemViewDelegate(MainItemDelegate())
        addItemViewDelegate(MainItemDelegate())
    }
}
