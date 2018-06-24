package com.wr.baishi

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder.FRAME_OPTION
import com.bumptech.glide.request.RequestOptions
import com.leimo.common.adapter.CommonAdapter
import com.leimo.common.adapter.util.ViewHolder
import com.wr.baishi.adapter.layout.MyLinearLayoutManager
import com.wr.baishi.api.ApiRequest
import com.wr.baishi.bean.DataVideoBean
import com.wr.baishi.bean.VideoBean
import com.wr.base.BaseFragment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.security.MessageDigest


class MainBaishiFragment : BaseFragment() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: CommonAdapter<VideoBean>
    val mList = ArrayList<VideoBean>()
    override fun setLayoutResouceId(): Int {
        return R.layout.fragment_main_baishi
    }

    companion object {
        fun newInstance(): MainBaishiFragment {
            val fragment = MainBaishiFragment()
            return fragment
        }
    }

    override fun initView() {
        super.initView()
        mRecyclerView = mRootView.findViewById<RecyclerView>(R.id.recyclerView_baishi)
    }

    override fun initValue() {
        super.initValue()
        initAdapter()
        getData()
    }


    private fun initAdapter() {
        mRecyclerView.layoutManager = MyLinearLayoutManager(context)
        mAdapter = object : CommonAdapter<VideoBean>(context, R.layout.item_main_video, mList) {
            override fun convert(holder: ViewHolder?, t: VideoBean?, position: Int, payloads: MutableList<Any>?) {
                holder?.let {
                    t?.let {
                        val videoPlayer = holder.getView<JZVideoPlayerStandard>(R.id.videoplayer_main)
                        val image = holder.getView<ImageView>(R.id.iv_main)
                        if (t.videoUrls.size > 0) {
                            videoPlayer.setUp(t.videoUrls[0], JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,t.content)
                            //one
//                            videoPlayer.thumbImageView.setImageBitmap(getVideoThumbnail(t.videoUrls[0]))
//                            image.visibility = View.GONE
                            // two
                            loadVideoScreenshot(context, t.videoUrls[0], image, 1 * 1000 * 1000)
                            image.setOnClickListener {
                                run {
                                    videoPlayer.startVideo()
                                    image.visibility = View.GONE
                                }
                            }
                        }
                        holder.setText(R.id.tv_main_like, t.likeCount.toString())

                        holder.setText(R.id.tv_main_dislike, t.dislikeCount.toString())

                    }
                }
            }
        }
        mRecyclerView.adapter = mAdapter
    }

    fun loadVideoScreenshot(context: Context, uri: String, imageView: ImageView, frameTimeMicros: Long) {
        val requestOptions = RequestOptions.frameOf(frameTimeMicros)
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST)
        requestOptions.transform(object : BitmapTransformation() {
            override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
                return toTransform
            }

            override fun updateDiskCacheKey(messageDigest: MessageDigest) {
                try {
                    messageDigest.update((context.packageName + "RotateTransform").toByteArray(charset("utf-8")))
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        })
        Glide.with(context).load(uri).apply(requestOptions).into(imageView)
    }

    /**
     * 给出url，获取视频的第一帧
     *
     * @param url
     * @return
     */
    fun getVideoThumbnail(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        val retriever = MediaMetadataRetriever()
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(url, HashMap())
            //获得第一帧图片
            bitmap = retriever.frameAtTime
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } finally {
            retriever.release()
        }
        return bitmap
    }

    private fun getData() {
        ApiRequest.getVideoData("0", object : Observer<DataVideoBean> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: DataVideoBean) {
                mList.addAll(t.data)
                mAdapter.notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos()
    }

}