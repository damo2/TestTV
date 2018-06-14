package com.wr.comic.adapter

import com.leimo.common.adapter.util.ItemViewDelegate
import com.leimo.common.adapter.util.ViewHolder
import com.wr.comic.R
import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.ComicRankListBean

class MainComicSmallDelegate : ItemViewDelegate<ComicBean> {
    override fun getItemViewLayoutId(): Int {
        return R.layout.item_main_comic_small
    }

    override fun convert(holder: ViewHolder?, t: ComicBean?, position: Int) {
        holder?.setText(R.id.iv_img_small_title, t!!.title)
        holder?.setImageLoad(R.id.iv_img_small_cover, t!!.cover)
    }

    override fun isForViewType(item: ComicBean?, position: Int): Boolean {
        return item is ComicRankListBean
    }

}