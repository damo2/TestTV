package com.leimo.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * mRecyclerView 添加item 点击和长按事件
 * mRecyclerView.addOnItemTouchListener
 * Created by as on 2017/3/16.
 */

public class RecyclerItemClickListener implements OnItemTouchListener {
    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    // 点击回调
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;
        // 识别并处理手势
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // 轻击触摸屏后,弹起，必须返回true，否则无法触发单击
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                if (recyclerView == null || e == null) {
                    return;
                }
                // 长按根据findChildViewUnder(float x, float y)来算出哪个item被选择了
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                // 有item被选则且监听器不为空触发长按事件
                if (childView != null && mListener != null) {
                    mListener.onItemLongClick(childView,
                    recyclerView.getChildLayoutPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        if (view == null || e == null) {
            return false;
        }
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            // 触发单击事件
            mListener.onItemClick(childView, view.getChildLayoutPosition(childView));
            return false;//return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

}
