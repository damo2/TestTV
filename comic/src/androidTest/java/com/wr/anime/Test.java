package com.wr.anime;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by Administrator on 2018/7/1.
 */
@RunWith(AndroidJUnit4.class)
public class Test {
    Instrumentation mInstrumentation;
    private UiDevice mUIDevice;
    Context mContext;
    @Before
    public void start ()throws RemoteException {
        mInstrumentation = InstrumentationRegistry.getInstrumentation();
        mUIDevice = UiDevice.getInstance(mInstrumentation);//获得device对象
        mContext=mInstrumentation.getContext();
//        if(!mUIDevice.isScreenOn()){  //唤醒屏幕
//            mUIDevice.wakeUp();
//        }
//        mUIDevice.pressHome();  //按home键
    }
    @org.junit.Test
    public void detail() throws UiObjectNotFoundException {
//        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.wr.tv");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        mContext.startActivity(intent);

        UiObject hot =mUIDevice.findObject(new UiSelector().resourceId("com.wr.tv:id/iv_img_small_cover"));
        hot.click();
    }
}
