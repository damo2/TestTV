package com.leimo.common.log.util;

import android.text.TextUtils;
import android.util.Log;
import com.leimo.common.log.LogSD;

/**
 * Created by wangru
 * Date: 2018/6/8  16:44
 * mail: 1902065822@qq.com
 * describe:
 */

public class StackUtil {
    private static final String TAG = "StackUtil";



    public static StackTraceElement getTargetStackTraceElement(Class... classes) {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isOTherMethod = false;
            if (classes != null) {
                for (Class cls : classes) {
                    if (TextUtils.equals(stackTraceElement.getClassName(),cls.getName())) {
                        isOTherMethod = true;
                    }
                }
            }
            if (shouldTrace && !isOTherMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isOTherMethod;
        }
        return targetStackTrace;
    }
}
