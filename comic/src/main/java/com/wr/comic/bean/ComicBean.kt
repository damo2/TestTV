package com.wr.comic.bean

open class ComicBean() {
    var id: Long = 0
    //标题
    var title: String = ""
    //封面图片
    var cover: String = ""
    //作者
    var author: String = ""
    //章节标题
    var chapters: List<String> = ArrayList<String>()
    var chaptersUrl: List<String> = ArrayList<String>()
    //标签
    var tags: List<String> = ArrayList<String>()
    //收藏数
    var collections: String = ""
    //详情
    var describe: String = ""
    //评分
    var point: String = ""
    //人气值
    var popularity: String = ""
    //话题量
    var topics: String = ""
    //更新时间
    var updates: String = ""
    //状态
    var status: String = ""
    //默认阅读方式
    var readType: Int = 0
    //当前章节
    var currentChapter: Int = 0
    //收藏时间
    var collectTime: Long = 0
    //点击时间
    var clickTime: Long = 0
    //下载时间
    var downloadTime: Long = 0
    //是否收藏
    var isCollected: Boolean = false

    /*state状态数据库保存*/
    var stateInte: Int = 0
    //当前页
    var current_page: Int = 0
    //当前共有多少页面
    var current_page_all: Int = 0
    //有多少话在下载
    var download_num: Int = 0
    //下载完成多少话
    var download_num_finish: Int = 0
    //来自什么资源
    var from: Int = 0

}
