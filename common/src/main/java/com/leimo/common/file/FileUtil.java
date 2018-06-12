package com.leimo.common.file;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by wangru
 * Date: 2018/6/9  13:23
 * mail: 1902065822@qq.com
 * describe:
 */

public class FileUtil {
    private static final String TAG = "FileUtil";
    //获取文件大小
    public static long getFileSize(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return 0;
        }
        File file = new File(filePath);
        long size = 0;
        if (file.exists()) {
            return file.length();
        }
        return size;
    }
    /**
     * 复制文件到指定文件
     *
     * @param fromPath 源文件
     * @param toPath   复制到的文件
     * @return true 成功，false 失败
     */
    public static boolean copy(String fromPath, String toPath) {
        boolean result = false;
        File from = new File(fromPath);
        if (!from.exists()) {
            return result;
        }

        File toFile = new File(toPath);
        IOUtil.deleteFileOrDir(toFile);
        File toDir = toFile.getParentFile();
        if (toDir.exists() || toDir.mkdirs()) {
            FileInputStream in = null;
            FileOutputStream out = null;
            try {
                in = new FileInputStream(from);
                out = new FileOutputStream(toFile);
                IOUtil.copy(in, out);
                result = true;
            } catch (Throwable ex) {
                Log.d(TAG,ex.getMessage(), ex);
                result = false;
            } finally {
                IOUtil.closeQuietly(in);
                IOUtil.closeQuietly(out);
            }
        }
        return result;
    }
}
