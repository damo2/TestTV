package com.wr.comic.api.request

import com.wr.comic.api.UrlTencentComic
import com.wr.comic.api.jsoup.TencentComicData
import com.wr.comic.bean.ComicBean
import com.wr.comic.bean.MainBanner
import com.wr.comic.bean.ComicTitleBean
import com.wr.comic.constant.TypeConstant
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by wangru
 * Date: 2018/6/12  16:12
 * mail: 1902065822@qq.com
 * describe:
 */
class MainRequest {
    companion object {
        fun getBanner(observer: Observer<List<MainBanner>>) {
            var observable = Observable.create(ObservableOnSubscribe { e: ObservableEmitter<List<MainBanner>> ->
                run {
                    val doc = Jsoup.connect(UrlTencentComic.Banner).get()
                    val mainBannerList = TencentComicData.transToBanner(doc)
                    e.onNext(mainBannerList)
                }
            })
            observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
        }


        fun getRankList(observer: Observer<List<ComicBean>>) {

            var observableRankList = Observable.create(ObservableOnSubscribe { e: ObservableEmitter<List<ComicBean>> ->
                run {
                    val comicList = ArrayList<ComicBean>()
                    //排行榜
                    val doc = Jsoup.connect(UrlTencentComic.TencentRankList).get()
                    addRankToMain(comicList, doc, TypeConstant.MainType.RANK_LIST)
                    val recommendDoc = Jsoup.connect(UrlTencentComic.TencentHomePage).get()
                    //少年漫画
                    addRankToMain(comicList, recommendDoc, TypeConstant.MainType.BOY_RANK)
                    //少女漫画
                    addRankToMain(comicList, recommendDoc, TypeConstant.MainType.GIRL_RANK)
                    //强推作品
                    addRankToMain(comicList, recommendDoc, TypeConstant.MainType.RECOMMEND)
                    e.onNext(comicList)
                }
            })

            observableRankList.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
        }


        fun addRankToMain(comicList: ArrayList<ComicBean>, doc: Document, type: Int) {
            when (type) {
                TypeConstant.MainType.RANK_LIST -> {
                    val mainTitle = ComicTitleBean()
                    mainTitle.type = TypeConstant.MainType.TITLE
                    mainTitle.itemTitle = TypeConstant.MainTitle.RANK_LIST_TITLE
                    comicList.add(mainTitle)
                    comicList.addAll(TencentComicData.transToRankList(doc))
                }
                TypeConstant.MainType.RECOMMEND -> {
                    val mainTitle = ComicTitleBean()
                    mainTitle.type = TypeConstant.MainType.TITLE
                    mainTitle.itemTitle = TypeConstant.MainTitle.RECOMMEND_TITLE
                    comicList.add(mainTitle)
                    comicList.addAll(TencentComicData.transToRecommend(doc))
                }
                TypeConstant.MainType.BOY_RANK -> {
                    val mainTitle = ComicTitleBean()
                    mainTitle.type = TypeConstant.MainType.TITLE
                    mainTitle.itemTitle = TypeConstant.MainTitle.BOY_RANK_TITLE
                    comicList.add(mainTitle)
                    comicList.addAll(TencentComicData.transToBoyRank(doc))
                }
                TypeConstant.MainType.GIRL_RANK -> {
                    val mainTitle = ComicTitleBean()
                    mainTitle.type = TypeConstant.MainType.TITLE
                    mainTitle.itemTitle = TypeConstant.MainTitle.GIRL_RANK_TITLE
                    comicList.add(mainTitle)
                    comicList.addAll(TencentComicData.transToGrilRank(doc))
                }

            }
        }
    }


}