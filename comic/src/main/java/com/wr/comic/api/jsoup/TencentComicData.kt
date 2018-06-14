package com.wr.comic.api.jsoup

import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.ComicRankListBean
import com.wr.comic.bean.ComicRecommendBean
import com.wr.comic.bean.MainBanner
import com.wr.comic.constant.TypeConstant
import org.jsoup.nodes.Document
import java.util.*

/**
 * Created by wangru
 * Date: 2018/6/12  15:37
 * mail: 1902065822@qq.com
 * describe:
 */

class TencentComicData {
    companion object {
        /**
         * 根据url取ID
         */
        fun getID(splitID: String): Long {
            val ids = splitID.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return java.lang.Long.parseLong(ids[ids.size - 1])
        }

        /**
         * 首页banner图
         */
        fun transToBanner(doc: Document): List<MainBanner> {
            val mainBannerList = ArrayList<MainBanner>()
            val detail = doc.getElementsByAttributeValue("class", "banner-list").get(0)
            val infos = detail.getElementsByTag("a")
            for (i in infos.indices) {
                val mainBanner = MainBanner(null, null, 0)
                mainBanner.img = infos.get(i).select("img").attr("src")
                mainBanner.title = infos.get(i).select("a").attr("title")
                try {
                    mainBanner.id = getID(infos.get(i).select("a").attr("href"))
                } catch (e: Exception) {
                }
                mainBannerList.add(mainBanner)
            }
            return mainBannerList
        }

        /**
         * 排行榜
         */
        fun transToRankList(doc: Document): List<ComicBean> {
            val comicList = ArrayList<ComicBean>()
            val detail = doc.getElementsByAttributeValue("class", "ret-works-cover")
            val infos = doc.getElementsByAttributeValue("class", "ret-works-info")
            for (i in detail.indices) {
                var comic = ComicRankListBean()
                comic.type = TypeConstant.MainType.RANK_LIST
                comic.title = detail[i].select("a").attr("title")
                comic.cover = (detail[i].select("img").attr("data-original"))
                try {
                    comic.id = getID(infos[i].select("a").attr("href"))
                } catch (e: Exception) {
                }
                comicList.add(comic)
            }
            return comicList
        }

        fun transToRecommend(doc: Document): List<ComicBean> {
            val comicList = ArrayList<ComicBean>()
            val detail = doc.getElementsByAttributeValue("class", "in-anishe-text")
            val random = Random()
            val result = random.nextInt(5)
            for (i in result * 6 until (result + 1) * 6) {
                val comic = ComicRecommendBean()
                comic.title=(detail[i].select("a").attr("title"))
                comic.cover=(detail[i].select("img").attr("data-original"))
                val ElementDescribe = detail[i].getElementsByAttributeValue("class", "mod-cover-list-intro")[0]
                comic.describe=(ElementDescribe.select("p").text())
                comic.id=getID(detail[i].select("a").attr("href"))
                comicList.add(comic)
            }
            return comicList
        }
    }

}
