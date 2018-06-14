package com.wr.comic.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.leimo.common.adapter.MultiItemTypeAdapter
import com.leimo.common.adapter.layoutrecycle.FullyGridLayoutManager
import com.leimo.common.adapter.layoutrecycle.NoScrollGridLayoutManager
import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.ComicRankListBean
import com.wr.comic.bean.ComicTitleBean
import com.wr.comic.constant.TypeConstant


class MainAdapter(var context: Context, var data: List<ComicBean>) : MultiItemTypeAdapter<ComicBean>(context, data) {
    val TAG = "MainAdapter"

    init {
        addItemViewDelegate(TypeConstant.MainType.TITLE, MainTitleDelegate())
        addItemViewDelegate(TypeConstant.MainType.RANK_LIST, MainComicSmallDelegate())

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView?.let {
            val manager = recyclerView.getLayoutManager()
            if (manager is FullyGridLayoutManager) {
                manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val type = getItemViewType(position - 1)
                        when (type) {
                            TypeConstant.MainType.RANK_LIST -> {return 2}
                            TypeConstant.MainType.TITLE -> return 6
                            else -> return 1;
                        }
                    }
                }
            }
        }
    }

}
