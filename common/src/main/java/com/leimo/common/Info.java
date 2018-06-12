package com.leimo.common;

/**
 * Created by wangru
 * Date: 2018/6/1  14:24
 * mail: 1902065822@qq.com
 * describe:
 */

public class Info {
    public static Info newInstance() {
        return InfoHolder.INSTANCE;
    }

    private static class InfoHolder {
        private static final Info INSTANCE = new Info();
    }

}
