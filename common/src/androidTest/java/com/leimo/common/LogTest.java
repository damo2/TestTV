package com.leimo.common;

import android.test.AndroidTestCase;
import com.leimo.common.log.util.LogInfo;
import com.leimo.common.log.util.LogToDevice;
import com.leimo.common.path.SDPathUtils;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangru
 * Date: 2018/6/4  10:50
 * mail: 1902065822@qq.com
 * describe:
 */

public class LogTest extends AndroidTestCase {
    private static final String TAG = "LogTest";
    public void test(){
        LogToDevice.setIsDebug(true);
        LogToDevice.setRootDirDefault(SDPathUtils.getSDCardPublicDir(getContext(),"logTest"));
        assertEquals(true,new LogInfo.Builder().filename("test").tag(TAG).subDirs("a","b").build().write());
    }
}
