package com.wr.comic.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.leimo.common.adapter.MultiItemTypeAdapter
import com.leimo.common.adapter.layoutrecycle.FullyGridLayoutManager
import com.wr.comic.bean.ComicBean
import com.wr.comic.constant.LocConst
import com.wr.comic.constant.TypeConstant


class MainAdapter(var context: Context, var data: List<ComicBean>) : MultiItemTypeAdapter<ComicBean>(context, data) {
    val TAG = "MainAdapter"

    init {
        addItemViewDelegate(TypeConstant.MainAdapterType.TITLE, MainTitleDelegate())
        addItemViewDelegate(TypeConstant.MainAdapterType.COMIC_MIDDLE, MainComicMiddleDelegate())
        addItemViewDelegate(TypeConstant.MainAdapterType.COMIC_SMALL, MainComicSmallDelegate())
        addItemViewDelegate(TypeConstant.MainAdapterType.COMIC_SMALL_MIN, MainComicSmallMinDelegate())
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView?.let {
            val manager = recyclerView.getLayoutManager()
            val adapter = recyclerView.adapter
            if (manager is FullyGridLayoutManager) {
                manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
//                        val type = getItemViewType(position - 1)
                        val type = getItemViewType(position)
                        var spanCount = LocConst.MAIN_ITEM_NUM
                        when (type) {
                            TypeConstant.MainAdapterType.COMIC_BIG -> {
                                spanCount = 2
                            }
                            TypeConstant.MainAdapterType.COMIC_MIDDLE -> {
                                spanCount = 3
                            }
                            TypeConstant.MainAdapterType.COMIC_SMALL -> {
                                spanCount = 4
                            }
                            TypeConstant.MainAdapterType.COMIC_SMALL_MIN -> {
                                spanCount = 6
                            }
                            TypeConstant.MainAdapterType.TITLE -> spanCount = 1
                            else -> spanCount = 1;
                        }
                        return LocConst.MAIN_ITEM_NUM / spanCount;
                    }
                }
            }
        }
    }

}
