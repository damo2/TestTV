package com.wr.comic.bean

import com.wr.comic.constant.TypeConstant

/**
 *
 * Created by wangru
 * Date: 2018/6/12  19:11
 * mail: 1902065822@qq.com
 * describe:
 */
class ComicRecommendBean : ComicBean(){
    init {
        super.type= TypeConstant.MainType.RECOMMEND
    }
}