package com.leimo.common.log.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wangru
 * Date: 2018/6/4  20:21
 * mail: 1902065822@qq.com
 * describe:
 */

public interface Lv {
    int i = 0;
    int d = 1;
    int e = 2;
}

@Retention(RetentionPolicy.SOURCE)
@IntDef( {Lv.i, Lv.d, Lv.e})
@interface LogLvType {
}