package com.wr.comic.api.jsoup

import com.wr.comic.bean.*
import com.wr.comic.constant.DownState
import com.wr.comic.constant.TypeConstant
import org.jsoup.nodes.Document
import java.text.DecimalFormat
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

        /**
         * 推荐
         */
        fun transToRecommend(doc: Document): List<ComicBean> {
            val comicList = ArrayList<ComicBean>()
            val detail = doc.getElementsByAttributeValue("class", "in-anishe-text")
            val random = Random()
            val result = random.nextInt(5)
            for (i in result * 6 until (result + 1) * 6) {
                val comic = ComicRecommendBean()
                comic.title = (detail[i].select("a").attr("title"))
                comic.cover = (detail[i].select("img").attr("data-original"))
                val ElementDescribe = detail[i].getElementsByAttributeValue("class", "mod-cover-list-intro")[0]
                comic.describe = (ElementDescribe.select("p").text())
                comic.id = getID(detail[i].select("a").attr("href"))
                comicList.add(comic)
            }
            return comicList
        }

        /**
         * 少年漫画
         */
        fun transToBoyRank(doc: Document): List<ComicBean> {
            val comicList = ArrayList<ComicBean>()
            val detail = doc.getElementsByAttributeValue("class", "in-teen-list mod-cover-list clearfix")[0]
            val boys = detail.getElementsByTag("li")
            for (i in boys.indices) {
                val comic = ComicBoyRankBean()
                comic.title = boys[i].select("img").attr("alt")
                comic.cover = boys[i].select("img").attr("data-original")
                val ElementDescribe = boys[i].getElementsByAttributeValue("class", "mod-cover-list-intro")[0]
                comic.describe = ElementDescribe.select("p").text()
                comic.id = getID(boys[i].select("a").attr("href"))
                comicList.add(comic)
            }
            return comicList
        }

        /**
         * 少女漫画
         */
        fun transToGrilRank(doc: Document): List<ComicBean> {
            val comicList = ArrayList<ComicBean>()
            val detail = doc.getElementsByAttributeValue("class", "in-girl-list mod-cover-list clearfix")[0]
            if (detail != null) {
                val boys = detail.getElementsByTag("li")
                if (boys != null) {
                    for (i in boys.indices) {
                        val comic = ComicGrilRankBean()
                        comic.title = boys[i].select("img").attr("alt")
                        comic.cover = boys[i].select("img").attr("data-original")
                        val ElementDescribe = boys[i].getElementsByAttributeValue("class", "mod-cover-list-intro")[0]
                        comic.describe = ElementDescribe.select("p").text()
                        try {
                            comic.id = getID(boys[i].select("a").attr("href"))
                        } finally {

                        }
                        comicList.add(comic)
                    }
                }
            }
            return comicList
        }

        fun transToComicDetail(doc: Document): ComicBean {
            val comic = ComicBean()
            try {
                comic.title = doc.title().split("-")[0]
                //设置标签
                val ElementDescription = doc.getElementsByAttributeValue("name", "Description")[0]
                val descriptions = ElementDescription.select("meta").attr("content")
                val mdescriptions = descriptions.split("：".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val tags = ArrayList<String>()
                val mtags = mdescriptions[mdescriptions.size - 1].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (i in mtags.indices) {
                    tags.add(mtags[i])
                }
                comic.tags = tags

                val detail = doc.getElementsByAttributeValue("class", "works-cover ui-left")[0]
                comic.cover = detail.select("img").attr("src")
                //设置作者
                val author = doc.getElementsByAttributeValue("class", " works-author-name")[0]
                comic.author = author.select("a").attr("title")
                //设置收藏数
                val collect = doc.getElementsByAttributeValue("id", "coll_count")[0]
                val decimalFormat = DecimalFormat(".00")//构造方法的字符格式这里如果小数不足2位,会以0补足.
                val collection = decimalFormat.format((java.lang.Float.parseFloat(collect.text()) / 10000).toDouble())//format 返回的是字符串
                comic.collections = "($collection)万"
                //设置章节数
                val DivChapter = doc.getElementsByAttributeValue("class", "chapter-page-all works-chapter-list")[0]
                val ElementChapters = DivChapter.getElementsByAttributeValue("target", "_blank")
                val chapters = ArrayList<String>()
                for (i in ElementChapters.indices) {
                    chapters.add(ElementChapters[i].select("a").text())
                }
                comic.chapters = chapters

                val ElementDescribe = doc.getElementsByAttributeValue("class", "works-intro-short ui-text-gray9")[0]
                comic.describe = ElementDescribe.select("p").text()

                val ElementPopularity = doc.getElementsByAttributeValue("class", " works-intro-digi")[0]
                comic.popularity = ElementPopularity.select("em")[1].text()
                //设置状态
                val status = detail.select("label")[0].text()
                //设置更新日期
                if (status == "已完结") {
                    comic.status = "已完结"
                    comic.updates = "全" + ElementChapters.size + "话"
                } else {
                    val ElementUpdate = doc.getElementsByAttributeValue("class", " ui-pl10 ui-text-gray6")[0]
                    val updates = ElementUpdate.select("span")[0].text()
                    comic.updates = updates
                    comic.status = "更新最新话"
                }

                val ElementPoint = doc.getElementsByAttributeValue("class", "ui-text-orange")[0]
                comic.point = ElementPoint.select("strong")[0].text()
                //设置阅读方式
                val Element_isJ = doc.getElementsByAttributeValue("src", "http://q2.qlogo.cn/g?b=qq&k=hMPm8WLLDbcdk0Vs4epHxA&s=100&t=561")
//            if (Element_isJ != null && Element_isJ.size != 0) {
//                comic.readType=(Constants.UP_TO_DOWN)
//            } else {
//                comic.readType=(Constants.UP_TO_DOWN)
//            }
                comic.stateInte = DownState.START.getState()
            } catch (e: Exception) {
            }
            return comic;
        }
    }

}
