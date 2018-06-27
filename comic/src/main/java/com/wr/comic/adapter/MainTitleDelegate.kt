package com.wr.comic.adapter

import android.view.View
import com.app.common.adapter.util.ItemViewDelegate
import com.app.common.adapter.util.ViewHolder
import com.wr.comic.R
import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.ComicTitleBean

class MainTitleDelegate : ItemViewDelegate<ComicBean> {
    override fun getItemViewLayoutId(): Int {
        return R.layout.item_main_title
    }

    override fun convert(holder: ViewHolder?, t: ComicBean?, position: Int, payloads: List<Any>?) {
        holder?.let {
            if (position == 0 || position == 1) {
                holder.getView<View>(R.id.view_main_title_space).visibility = View.GONE
            }
            t?.let {
                val mainTitle = t as ComicTitleBean
                holder.setText(R.id.tv_main_title_text, mainTitle.itemTitle)
            }
        }
    }

    override fun isForViewType(item: ComicBean?, position: Int): Boolean {
        return item is ComicTitleBean
    }
}