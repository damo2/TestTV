package com.wr.comic.api.jsoup

import com.wr.comic.bean.MainBanner
import org.jsoup.nodes.Document

/**
 * Created by wangru
 * Date: 2018/6/12  15:37
 * mail: 1902065822@qq.com
 * describe:
 */

class TencentComicData{
    companion object {
        /**
         * 首页banner图
         */
        public fun transToBanner(doc: Document): List<MainBanner> {
            val mainBannerList = ArrayList<MainBanner>()
            val detail = doc.getElementsByAttributeValue("class", "banner-list").get(0)
            val infos = detail.getElementsByTag("a")
            for (i in infos.indices) {
                val mainBanner = MainBanner(null, null, null)
                mainBanner.img = infos.get(i).select("img").attr("src")
                mainBanner.title = infos.get(i).select("a").attr("title")
                mainBanner.url = infos.get(i).select("a").attr("href")
                mainBannerList.add(mainBanner)
            }
            return mainBannerList
        }
    }
}
