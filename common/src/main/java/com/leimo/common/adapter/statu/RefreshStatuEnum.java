package com.leimo.common.adapter.statu;

/**
 * 刷新加载类型
 * Created by wangru
 * Date: 2017/12/28  10:12
 * mail: 1902065822@qq.com
 * describe:
 */
public enum RefreshStatuEnum {
    /**
     * 首次进入
     */
    FIRST,
    /**
     * 刷新
     */
    REFRESH,
    /**
     * 加载
     */
    LOAD,
    /**
     * 首次完成
     */
    FIRST_SUC,
    /**
     * 首次失败
     */
    FIRST_FAIL,
    /**
     * 刷新完成
     */
    REFRESH_SUC,
    /**
     * 刷新完成
     */
    REFRESH_FAIL,
    /**
     * 加载完成
     */
    LOAD_SUC,
    /**
     * 加载失败
     */
    LOAD_FAIL,
    /**
     * 全部加在完成
     */
    LOAD_OVER_ALL,
    /**
     * 没有数据
     */
    NULL;


    /**
     * 得到完成状态
     */
    public static RefreshStatuEnum getStatuSuc(RefreshStatuEnum mRefreshStatu) {
        if (mRefreshStatu == RefreshStatuEnum.REFRESH) {
            mRefreshStatu = RefreshStatuEnum.REFRESH_SUC;
        }
        if (mRefreshStatu == RefreshStatuEnum.LOAD) {
            mRefreshStatu = RefreshStatuEnum.LOAD_SUC;
        }
        if (mRefreshStatu == RefreshStatuEnum.FIRST) {
            mRefreshStatu = RefreshStatuEnum.FIRST_SUC;
        }
        return mRefreshStatu;
    }

    public static RefreshStatuEnum getStatuFail(RefreshStatuEnum mRefreshStatu) {
        if (mRefreshStatu == RefreshStatuEnum.REFRESH) {
            mRefreshStatu = RefreshStatuEnum.REFRESH_FAIL;
        }
        if (mRefreshStatu == RefreshStatuEnum.LOAD) {
            mRefreshStatu = RefreshStatuEnum.LOAD_FAIL;
        }
        if (mRefreshStatu == RefreshStatuEnum.FIRST) {
            mRefreshStatu = RefreshStatuEnum.FIRST_FAIL;
        }
        return mRefreshStatu;
    }

    /**
     * 正在请求数据（刷新或加载）
     */
    public static boolean isDoing(RefreshStatuEnum mRefreshStatu) {
        return mRefreshStatu == RefreshStatuEnum.REFRESH || mRefreshStatu == RefreshStatuEnum.LOAD || mRefreshStatu == FIRST;
    }

    public static boolean isRefresh(RefreshStatuEnum mRefreshStatu) {
        return mRefreshStatu == RefreshStatuEnum.REFRESH || mRefreshStatu == REFRESH_SUC || mRefreshStatu == REFRESH_FAIL;
    }

    public static boolean isLoad(RefreshStatuEnum mRefreshStatu) {
        return mRefreshStatu == RefreshStatuEnum.LOAD || mRefreshStatu == LOAD_SUC || mRefreshStatu == REFRESH_FAIL;
    }

    public static boolean isFirst(RefreshStatuEnum mRefreshStatu) {
        return mRefreshStatu == RefreshStatuEnum.FIRST || mRefreshStatu == FIRST_SUC || mRefreshStatu == FIRST_FAIL;
    }

}
