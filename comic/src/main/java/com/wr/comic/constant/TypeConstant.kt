package com.wr.comic.constant

interface TypeConstant{
    interface Main{
        companion object {
            val Banner=1
            val Recommend = 2
            val Type = 3
        }
    }

    interface MainType{
        companion object {
            val RECOMMEND=0
            val RANK_LIST = 1;
            val HOT_SERIAL = 2;
            val HOT_JAPAN = 3;
            val BOY_RANK = 4;
            val GIRL_RANK = 5;

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

