package com.wr.comic.constant;

/**
 * Created by wangru
 * Date: 2018/6/15  13:27
 * mail: 1902065822@qq.com
 * describe:
 */

public enum  DownState {
    START(0),
    DOWN(1),
    PAUSE(2),
    STOP(3),
    ERROR(4),
    FINISH(5),
    NONE(6),
    DELETE(-1),
    CACHE(-2);
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    DownState(int state) {
        this.state = state;
    }
}
