package com.wr.comic.api.jsoup

import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.ComicRankListBean
import com.wr.comic.bean.MainBanner
import org.jsoup.nodes.Document

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
            val mainBannerList = ArrayList<ComicBean>()
            val detail = doc.getElementsByAttributeValue("class", "ret-works-cover")
            val infos = doc.getElementsByAttributeValue("class", "ret-works-info")
            for (i in detail.indices) {
                var comic = ComicRankListBean()
                comic.title = detail[i].select("a").attr("title")
                comic.cover = (detail[i].select("img").attr("data-original"))
                try {
                    comic.id = getID(infos[i].select("a").attr("href"))
                } catch (e: Exception) {
                }
                mainBannerList.add(comic)
            }
            return mainBannerList
        }
    }

}
