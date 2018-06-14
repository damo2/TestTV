package com.leimo.common.layout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by wangru
 * Date: 2018/6/14  10:47
 * mail: 1902065822@qq.com
 * describe:
 */

public class LayoutUtil {
    public static void setWidth(View view, int width) {
        setWidthHeight(view,width,-1);
    }
    public static void setHeight(View view, int height) {
        setWidthHeight(view,-1,height);
    }
    public static void setWidthHeight(View view, int width, int height) {
        ViewGroup.LayoutParams layoutParamsGroup = view.getLayoutParams();
        if (layoutParamsGroup != null) {
            if (layoutParamsGroup instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layoutParamsGroup;
                if (width > -1) {
                    layoutParams.width = width;
                }
                if (height > -1) {
                    layoutParams.height = height;
                }
                view.setLayoutParams(layoutParams);
            }
            if (layoutParamsGroup instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layoutParamsGroup;
                if (width > -1) {
                    layoutParams.width = width;
                }
                if (height > -1) {
                    layoutParams.height = height;
                }
                view.setLayoutParams(layoutParams);
            }
        }
    }

    public static void setMargin(View view,int leftMagin,int rightMagin){
        ViewGroup.LayoutParams layoutParamsGroup = view.getLayoutParams();
        if (layoutParamsGroup != null) {
            if (layoutParamsGroup instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layoutParamsGroup;
                if (leftMagin > -1) {
                    layoutParams.leftMargin = leftMagin;
                }
                if (rightMagin > -1) {
                    layoutParams.rightMargin = rightMagin;
                }
                view.setLayoutParams(layoutParams);
            }
            if (layoutParamsGroup instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layoutParamsGroup;
                if (leftMagin > -1) {
                    layoutParams.leftMargin = leftMagin;
                }
                if (rightMagin > -1) {
                    layoutParams.rightMargin = rightMagin;
                }
                view.setLayoutParams(layoutParams);
            }
        }
    }

}
