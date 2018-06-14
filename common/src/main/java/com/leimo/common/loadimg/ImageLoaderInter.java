package com.leimo.common.loadimg;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;


/**
 * Description :
 * Author :fengjing
 * Email :164303256@qq.com
 * Date :2016/10/28
 */
public class ImageLoaderInter {
    /**
     * 圆角圆形处理等
     */
    private BitmapTransformation transform;
    /**
     * 加载错误时显示的图片id
     */
    private int errorImgId;
    /**
     * 加载时的占位图id
     */
    private int placeholderImgId;
    /**
     * 是否需要从中间裁剪
     */
    private boolean centerCrop = false;
    /**
     * 是否淡入淡出
     */
    private boolean dontAnimate = false;
    /**
     * 是否加载gif图 <默认不加载></>
     */
    private boolean loadGif = false;
    /**
     * 图片加载监听 只有加载bitmap图片时有效 gif图片无效
     */
    private RequestListener<Drawable> requestListener;

    /**
     * 使用固定大小 oss自动裁剪
     */
    private boolean fixedSize = false;
    /**
     * oss处理
     */
    private String ossProcess;

    private int width;
    private int height;

    private int diskCacheType;

    public BitmapTransformation getTransform() {
        return transform;
    }

    public ImageLoaderInter setTransform(BitmapTransformation transform) {
        this.transform = transform;
        return this;
    }

    public int getErrorImgId() {
        return errorImgId;
    }

    public ImageLoaderInter setErrorImgId(int errorImgId) {
        this.errorImgId = errorImgId;
        return this;
    }

    public int getPlaceholderImgId() {
        return placeholderImgId;
    }

    public ImageLoaderInter setPlaceholderImgId(int placeholderImgId) {
        this.placeholderImgId = placeholderImgId;
        return this;
    }

    public boolean isCenterCrop() {
        return centerCrop;
    }

    public ImageLoaderInter setCenterCrop(boolean centerCrop) {
        this.centerCrop = centerCrop;
        return this;
    }

    public boolean isDontAnimate() {
        return dontAnimate;
    }

    public void setDontAnimate(boolean dontAnimate) {
        this.dontAnimate = dontAnimate;
    }

    public boolean isLoadGif() {
        return loadGif;
    }

    public ImageLoaderInter setLoadGif(boolean loadGif) {
        this.loadGif = loadGif;
        return this;
    }

    public RequestListener<Drawable> getRequestListener() {
        return requestListener;
    }

    public ImageLoaderInter setRequestListener(RequestListener<Drawable> requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    public boolean isFixedSize() {
        return fixedSize;
    }

    public ImageLoaderInter setFixedSize(boolean fixedSize) {
        this.fixedSize = fixedSize;
        return this;
    }

    public String getOssProcess() {
        return ossProcess;
    }

    public ImageLoaderInter setOssProcess(String ossProcess) {
        this.ossProcess = ossProcess;
        return this;
    }

    public int getDiskCacheType() {
        return diskCacheType;
    }

    public void setDiskCacheType(int diskCacheType) {
        this.diskCacheType = diskCacheType;
    }

    public void setWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public interface DiskCacheType{
        int ALL=1;
        int SOURCE=2;
        int NO=3;
    }
}
