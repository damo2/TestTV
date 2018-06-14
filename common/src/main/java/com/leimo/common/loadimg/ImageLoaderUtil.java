package com.leimo.common.loadimg;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;

/**
 * Description :图片加载
 * Author :fengjing
 * Email :164303256@qq.com
 * Date :2016/10/28
 */
public class ImageLoaderUtil {
    private static final String TAG = "ImageLoader";
    /**
     * 1为矩形圆角,其它为缩放后的圆形图
     */
    public static int CircleOrRectangle = 1;

    /**
     * activity中加载图片
     */
    public static void loadImage(Activity activity, ImageView img, String url, ImageLoaderInter interpolator) {
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            loadImage(activity.getApplicationContext(), img, url, interpolator);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            } else {
                loadImage(Glide.with(activity), img, url, interpolator);
            }
        }
    }

    //加载资源文件
    public static void loadImageRes(Context context, ImageView img, int url, ImageLoaderInter interpolator) {
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            loadImageRes(Glide.with(context.getApplicationContext()), img, url, interpolator);
        } else {
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()) {
                } else {
                    loadImageRes(Glide.with((Activity) context), img, url, interpolator);
                }
            } else {
                loadImageRes(Glide.with(context), img, url, interpolator);
            }
        }
    }

    /**
     * fragment中加载图片
     */
    public static void loadImage(Fragment fragment, ImageView img, String url, ImageLoaderInter interpolator) {
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            loadImage(fragment.getActivity().getApplicationContext(), img, url, interpolator);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && fragment.isDetached()) {//getActivity.isDestroyed
            } else {
                loadImage(Glide.with(fragment), img, url, interpolator);
            }
        }

    }

    /**
     * applicationContext对象加载图片
     */
    public static void loadImage(Context context, ImageView img, String url, ImageLoaderInter interpolator) {

        if (context instanceof Activity) {
            if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                loadImage(context.getApplicationContext(), img, url, interpolator);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()) {
                } else {
                    loadImage(Glide.with((Activity) context), img, url, interpolator);
                }
            }
        } else {
            loadImage(Glide.with(context), img, url, interpolator);
        }
    }

    /**
     * applicationContext对象加载图片
     */
    public static void loadImageUseDiskCache(Context context, ImageView img, String url, ImageLoaderInter interpolator) {

        if (context instanceof Activity) {
            if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                loadImage(context.getApplicationContext(), img, url, interpolator);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()) {
                } else {
                    loadImageDiskCache(Glide.with((Activity) context), img, url, interpolator);
                }
            }
        } else {
            loadImageDiskCache(Glide.with(context), img, url, interpolator);
        }
    }

    public static void loadImage(Activity activity, ImageView img, String url) {
        loadImage(activity, img, url, null);
    }

    public static void loadImage(Fragment fragment, ImageView img, String url) {
        loadImage(fragment, img, url, null);
    }

    public static void loadImage(Context context, ImageView img, String url) {
        loadImage(context, img, url, null);
    }


    //只缓存不加载到控件 type 1加oss裁剪0不添加
    public static RequestBuilder imageBuilder(Context context, ImageView img, String url, ImageLoaderInter interpolator, int type) {
        if (context instanceof Activity) {
            if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                return imageBuilder(context.getApplicationContext(), img, url, interpolator, type);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()) {
                    //todo context.getApplicationContext() 替换成 application
                    return imageBuilderOpt(Glide.with(context.getApplicationContext()), img, url, interpolator);
                } else {
                    return imageBuilderOpt(Glide.with((Activity) context), img, url, interpolator);
                }
            }
        } else {
            return imageBuilderOpt(Glide.with(context), img, url, interpolator);
        }
    }

    //缓存并加载图片  type 1加oss裁剪0不添加
    public static void loadImage(RequestManager manager, ImageView img, String url, ImageLoaderInter interpolator) {
        if (img != null) {
            imageBuilderOpt(manager, img, url, interpolator).into(img);
        }
    }

    //跳过缓存并加载图片
    public static void loadImageDiskCache(RequestManager manager, ImageView img, String url, ImageLoaderInter interpolator) {
        if (img != null) {
            imageBuilderOptDiskCache(manager, img, url, interpolator).into(img);
        }
    }

    //type 1加oss裁剪0不添加
    private static RequestBuilder imageBuilderOpt(RequestManager manager, ImageView img, String url, ImageLoaderInter interpolator) {
        String temp_url = url;
        CircleOrRectangle = 1;
        RequestOptions options = new RequestOptions();
        if (interpolator == null) {
            CircleOrRectangle = 1;
            return getLoad(manager, temp_url).apply(options);
        } else {
            if (!TextUtils.isEmpty(interpolator.getOssProcess())) {
                temp_url = url;
                temp_url += interpolator.getOssProcess();
            }
            //            DrawableTypeRequest<String> request = manager.load(temp_url);
            if (interpolator.isLoadGif()) {//是否加载动态图
                if (interpolator.isCenterCrop()) {//是否从中间裁剪
                    options.centerCrop();
                }
                if (interpolator.isDontAnimate()) {//不使用淡入淡出
                    options.dontAnimate();
                }
                if (interpolator.getErrorImgId() != 0) {//是否加载错误图片
                    options.error(interpolator.getErrorImgId());
                }
                if (interpolator.getPlaceholderImgId() != 0) {//是否加载占位图
                    options.placeholder(interpolator.getPlaceholderImgId());
                }

                return getLoad(manager, temp_url).apply(options);
            } else {
                if (interpolator.isCenterCrop()) {
                    options.centerCrop();
                }
                if (interpolator.isDontAnimate()) {
                    options.dontAnimate();
                }
                if (interpolator.getTransform() != null) {
                    options.transform(interpolator.getTransform());
                }
                if (interpolator.getErrorImgId() != 0) {
                    options.error(interpolator.getErrorImgId());
                }
                if (interpolator.getPlaceholderImgId() != 0) {
                    options.placeholder(interpolator.getPlaceholderImgId());
                }
                if (interpolator.getRequestListener() != null) {
                    return getLoad(manager, temp_url).apply(options);
                } else {
                    return getLoad(manager, temp_url).apply(options);
                }
            }
        }
    }

    private static RequestBuilder<Drawable> getLoad(RequestManager manager, final String temp_url) {
        return manager.load(temp_url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        });
    }

    private static RequestBuilder imageBuilderOptDiskCache(RequestManager manager, ImageView img, String url, ImageLoaderInter interpolator) {
        String temp_url = url;
        CircleOrRectangle = 1;
        RequestOptions options = new RequestOptions();

        options.diskCacheStrategy(DiskCacheStrategy.DATA);
        if (interpolator == null) {
            CircleOrRectangle = 1;
            return getLoad(manager, temp_url).apply(options);
        } else {
            if (interpolator.getWidth() > 0 && interpolator.getHeight() > 0) {
                options.override(interpolator.getWidth(), interpolator.getHeight());
            }
            if (!TextUtils.isEmpty(interpolator.getOssProcess())) {
                temp_url = url;
                temp_url += interpolator.getOssProcess();
            }
            switch (interpolator.getDiskCacheType()) {
                case ImageLoaderInter.DiskCacheType.ALL:
                    options.diskCacheStrategy(DiskCacheStrategy.ALL);
                    break;
                case ImageLoaderInter.DiskCacheType.SOURCE:
                    options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                    break;
                case ImageLoaderInter.DiskCacheType.NO:
                    options.diskCacheStrategy(DiskCacheStrategy.NONE);
                    break;
                default:
                    break;
            }

            if (interpolator.isLoadGif()) {//是否加载动态图
                if (interpolator.isCenterCrop()) {//是否从中间裁剪
                    options.centerCrop();
                }

                if (interpolator.getErrorImgId() != 0) {//是否加载错误图片
                    options.error(interpolator.getErrorImgId());
                }
                if (interpolator.getPlaceholderImgId() != 0) {//是否加载占位图
                    options.placeholder(interpolator.getPlaceholderImgId());
                }

                return getLoad(manager, temp_url).apply(options);
            } else {
                if (interpolator.isCenterCrop()) {
                    options.centerCrop();
                }
                if (interpolator.getTransform() != null) {
                    options.transform(interpolator.getTransform());
                }
                if (interpolator.getErrorImgId() != 0) {
                    options.error(interpolator.getErrorImgId());
                }
                if (interpolator.getPlaceholderImgId() != 0) {
                    options.placeholder(interpolator.getPlaceholderImgId());
                }
                if (interpolator.getRequestListener() != null) {
                    RequestListener<Drawable> requestListener = interpolator.getRequestListener();
                    return getLoad(manager, temp_url).apply(options).listener(requestListener);
                } else {
                    return getLoad(manager, temp_url).apply(options);
                }
            }
        }
    }


    private static void loadImageRes(RequestManager manager, ImageView img, @DrawableRes int url, ImageLoaderInter interpolator) {
        if (interpolator != null) {
            RequestOptions options = new RequestOptions();
            if (interpolator.isCenterCrop()) {
                options.centerCrop();
            }
            if (interpolator.getTransform() != null) {
                options.transform(interpolator.getTransform());
            }
            if (interpolator.getErrorImgId() != 0) {
                options.error(interpolator.getErrorImgId());
            }
            if (interpolator.getPlaceholderImgId() != 0) {
                options.placeholder(interpolator.getPlaceholderImgId());
            }
            if (interpolator.getRequestListener() != null) {
                RequestListener<Drawable> requestListener = interpolator.getRequestListener();
                manager.load(url).apply(options).listener(requestListener).into(img);
            } else {
                manager.load(url).apply(options).into(img);
            }
        } else {
            manager.load(url).into(img);
        }
    }


}
