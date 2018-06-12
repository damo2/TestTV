package com.wr.anime.adapter

import com.leimo.common.adapter.util.ItemViewDelegate
import com.leimo.common.adapter.util.ViewHolder
import com.wr.anime.R
import com.wr.anime.bean.AnimeBean
import com.wr.anime.constant.TypeConstant

class MainItemBanner : ItemViewDelegate<AnimeBean> {
    override fun getItemViewLayoutId(): Int {
        return R.layout.item_main_type
    }

    override fun convert(holder: ViewHolder?, t: AnimeBean?, position: Int) {
    }

    override fun isForViewType(item: AnimeBean?, position: Int): Boolean {
      return  item?.type== TypeConstant.Main.Type
    }
}