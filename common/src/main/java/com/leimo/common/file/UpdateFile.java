package com.leimo.common.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 下载文件后通知系统更新
 * Created by wangru
 * Date: 2018/6/1  14:37
 * mail: 1902065822@qq.com
 * describe:
 */

public class UpdateFile {
    private static final String TAG = "UpdateFile";

    /**
     * 保存文件通知系统更新，在图库显示图片
     * @param context
     * @param path
     * @param packageName 包名 eg: com.app.test
     */
    public static void updateImageSysStatu(Context context, String path, String packageName) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (context != null && file != null && file.exists()) {
                // 把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), "image", "图片");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                updateFileStatu(context, file,packageName);
            }

        } else {
            Log.d(TAG, "updateFileStatu: path is null");
        }
    }

    /**
     * 保存文件通知系统更新，在图库显示图片
     * @param context
     * @param file
     * @param packageName 包名 eg: com.app.test
     */
    public static void updateFileStatu(Context context, File file, String packageName) {
        if (context != null && file != null && file.exists()) {
            //通知图库更新
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri;
                if (Build.VERSION.SDK_INT >= 24) {
                    uri = FileProvider.getUriForFile(context, packageName + ".fileprovider", file);
                } else {
                    uri = Uri.fromFile(file);
                }
                intent.setData(uri);
                context.sendBroadcast(intent);
            } else {
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
            }
        } else {
            Log.d(TAG, "updateFileStatu: file is not exist");
        }
    }
}
