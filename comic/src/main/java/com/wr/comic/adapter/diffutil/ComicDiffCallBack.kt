package com.wr.comic.adapter.diffutil

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.text.TextUtils
import com.wr.comic.bean.ComicBean
import com.wr.comic.constant.KeyConst

/**
 * Created by wangru
 * Date: 2018/6/22  11:12
 * mail: 1902065822@qq.com
 * describe:
 */
class ComicDiffCallBack(val mOldDataList: ArrayList<ComicBean>?, val mNewDataList: ArrayList<ComicBean>?) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (mOldDataList != null && mNewDataList != null) {
            var oldData = mOldDataList.get(oldItemPosition)
            var newData = mNewDataList.get(newItemPosition)
            if (oldData.id == newData.id) {
                return true
            }
            if (TextUtils.equals(oldData.title, newData.title)) {
                return true
            }
        }
        return false
    }

    override fun getOldListSize(): Int {
        return if (mOldDataList == null) 0 else mOldDataList.size
    }

    override fun getNewListSize(): Int {
        return if (mNewDataList == null) 0 else mNewDataList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        mOldDataList?.let {
            mNewDataList?.let {
                val oldData = mOldDataList.get(oldItemPosition)
                val newData = mNewDataList.get(newItemPosition)
                if (oldData.type != newData.type) {
                    return false
                }
                if (TextUtils.equals(oldData.title, newData.title)) {
                    return false
                }
                if (TextUtils.equals(oldData.cover, newData.cover)) {
                    return false
                }
                if (TextUtils.equals(oldData.describe, newData.describe)) {
                    return false
                }
            }
        }
        return true
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        if (mOldDataList == null || mNewDataList == null) {
            return null
        }
        val oldData = mOldDataList.get(oldItemPosition)
        val newData = mNewDataList.get(newItemPosition)
        val payload = Bundle()
        if (!TextUtils.equals(oldData.title, newData.title)) {
            payload.putString(KeyConst.MainComic.TITLE, newData.title)
        }
        if (!TextUtils.equals(oldData.cover, newData.cover)) {
            payload.putString(KeyConst.MainComic.COVER, newData.cover)
        }
        if (!TextUtils.equals(oldData.describe, newData.describe)) {
            payload.putString(KeyConst.MainComic.DESC, newData.describe)
        }
        if (payload.size() == 0) {
            return null
        }
        return payload

        return null
    }
}