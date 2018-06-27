package com.app.common;

import android.content.Context;
import com.app.common.greendao.manage.DbManager;

/**
 * Created by wangru
 * Date: 2018/6/20  19:58
 * mail: 1902065822@qq.com
 * describe:
 */

public class CommonInit {
    public static void initDB(Context context){
        DbManager.instance().initDB(context);
    }
}
