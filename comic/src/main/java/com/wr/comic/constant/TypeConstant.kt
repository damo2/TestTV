package com.wr.comic.constant

interface TypeConstant{
    interface MainType{
        companion object {
            val OTHER=-1
            val TITLE=0
            val RECOMMEND=1
            val RANK_LIST = 2
            val HOT_SERIAL = 3
            val HOT_JAPAN = 4
            val BOY_RANK = 5
            val GIRL_RANK = 6
        }
    }
    interface MainTitle{
        companion object {
            val RECOMMEND_TITLE="强推作品"
            val RANK_LIST_TITLE="排行榜"
            val HOT_SERIAL_TITLE="热门连载"
            val HOT_JAPAN_TITLE="日漫馆"
            val HOT_RANK_TITLE="排行榜"
            val BOY_RANK_TITLE="少年漫画"
            val GIRL_RANK_TITLE="少女漫画"
        }
    }


}

