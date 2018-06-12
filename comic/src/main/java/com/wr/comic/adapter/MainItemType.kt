package com.wr.comic.adapter

import com.leimo.common.adapter.util.ItemViewDelegate
import com.leimo.common.adapter.util.ViewHolder
import com.wr.comic.R
import com.wr.comic.bean.ComicBean
import com.wr.comic.constant.TypeConstant

class MainItemType : ItemViewDelegate<ComicBean> {
    override fun getItemViewLayoutId(): Int {
        return R.layout.item_main_type
    }

    override fun convert(holder: ViewHolder?, t: ComicBean?, position: Int) {
    }

    override fun isForViewType(item: ComicBean?, position: Int): Boolean {
      return  item?.type==TypeConstant.Main.Type
    }
}