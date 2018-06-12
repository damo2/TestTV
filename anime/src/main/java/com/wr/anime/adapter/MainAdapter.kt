package com.wr.anime.adapter

import android.content.Context
import com.leimo.common.adapter.MultiItemTypeAdapter
import com.wr.anime.bean.AnimeBean

class MainAdapter(var context:Context,var data:List<AnimeBean>): MultiItemTypeAdapter<AnimeBean>(context,data) {
    init {
        addItemViewDelegate(MainItemDelegate())
        addItemViewDelegate(MainItemType())
    }
}
