package com.leimo.common.log;

import android.util.Log;
import com.leimo.common.log.util.LogInfo;
import com.leimo.common.log.util.LogToDevice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangru
 * Date: 2018/6/8  16:35
 * mail: 1902065822@qq.com
 * describe:
 */

public class LogSD {

    public static void w(String filename, Error e) {
        w(filename, getErrorInfo(e));
    }

    public static void w(String filename, Error e, String... subDirs) {
        w(filename, getErrorInfo(e), subDirs);
    }

    public static void w(String filename, String msg, String... subDirs) {
        new LogInfo.Builder().filename(filename).msg(msg).subDirs(subDirs).build().write();
    }

    public static void setRootDirDefault(String rootDirDefault){
        LogToDevice.setRootDirDefault(rootDirDefault);
    }
    public static void setIsDebug(boolean isDebug) {
        LogToDevice.setIsDebug(isDebug);
    }

    /**
     * 获取异常信息
     *
     * @param e
     * @return
     */
    public static String getErrorInfo(Error e) {
        Lock lock = new ReentrantLock();
        StringBuffer sb = new StringBuffer();
        lock.lock();
        try {
            StackTraceElement[] sts = e.getStackTrace();
            sb.append(e + "\r\n");
            if (sts != null && sts.length > 0) {
                for (StackTraceElement st : sts) {
                    if (st != null) {
                        sb.append("[ " + st.getFileName() + ":" + st.getLineNumber() + " ]\r\n");
                    }
                }
            }
        } finally {
            lock.unlock();
        }
        return sb.toString();
    }
}
