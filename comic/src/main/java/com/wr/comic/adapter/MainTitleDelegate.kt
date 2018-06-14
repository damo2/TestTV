package com.wr.comic.adapter

import com.leimo.common.adapter.util.ItemViewDelegate
import com.leimo.common.adapter.util.ViewHolder
import com.wr.comic.R
import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.ComicTitleBean
import com.wr.comic.constant.TypeConstant

class MainTitleDelegate : ItemViewDelegate<ComicBean> {
    override fun getItemViewLayoutId(): Int {
        return R.layout.item_main_recommend
    }

    override fun convert(holder: ViewHolder?, t: ComicBean?, position: Int) {
        val mainTitle = t as ComicTitleBean
        mainTitle?.let {
            holder?.setText(R.id.tv_main_recommend_title, mainTitle.itemTitle)
        }
    }

    override fun isForViewType(item: ComicBean?, position: Int): Boolean {
        return item?.type == TypeConstant.MainType.TITLE
    }
}