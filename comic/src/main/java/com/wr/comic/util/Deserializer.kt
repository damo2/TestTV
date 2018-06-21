package com.wr.comic.util

import com.google.gson.*
import com.wr.comic.bean.*
import com.wr.comic.constant.TypeConstant
import java.lang.reflect.Type

/**
 * Created by wangru
 * Date: 2018/6/21  11:28
 * mail: 1902065822@qq.com
 * describe:
 */

class Deserializer {
    class ComicMainDeserializer : JsonDeserializer<ComicBean> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ComicBean? {
            val jsonObject = json.asJsonObject
            val type = jsonObject.get("type").asInt
            if (type == TypeConstant.MainType.TITLE) {
                return Gson().fromJson(json, ComicTitleBean::class.java)
            } else if (type == TypeConstant.MainType.RECOMMEND) {
                return Gson().fromJson(json, ComicRecommendBean::class.java)
            } else if (type == TypeConstant.MainType.RANK_LIST) {
                return Gson().fromJson(json, ComicRankListBean::class.java)
            } else if (type == TypeConstant.MainType.BOY_RANK) {
                return Gson().fromJson(json, ComicBoyRankBean::class.java)
            } else if (type == TypeConstant.MainType.GRIL_RANK) {
                return Gson().fromJson(json, ComicGrilRankBean::class.java)
            } else {
            }
            return null
        }
    }
}
