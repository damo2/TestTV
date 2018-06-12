package com.wr.comic.adapter

import com.leimo.common.adapter.util.ItemViewDelegate
import com.leimo.common.adapter.util.ViewHolder
import com.wr.comic.R
import com.wr.comic.bean.ComicBean
import com.wr.comic.constant.TypeConstant

class MainItemDelegate : ItemViewDelegate<ComicBean> {
    override fun getItemViewLayoutId(): Int {
        return R.layout.item_main_recommend
    }

    override fun convert(holder: ViewHolder?, t: ComicBean?, position: Int) {
        holder?.setText(R.id.tv_main_recommend_title,t?.title)
    }

    override fun isForViewType(item: ComicBean?, position: Int): Boolean {
      return  item!!.type==TypeConstant.Main.Recommend
    }
}