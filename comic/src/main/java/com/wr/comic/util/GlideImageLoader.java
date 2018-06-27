package com.wr.comic.util;

import android.content.Context;
import android.widget.ImageView;
import com.app.common.loadimg.ImageLoaderUtil;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by wangru
 * Date: 2018/6/12  14:47
 * mail: 1902065822@qq.com
 * describe:
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoaderUtil.loadImage(context,imageView, (String) path);
    }

    @Override
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }
}