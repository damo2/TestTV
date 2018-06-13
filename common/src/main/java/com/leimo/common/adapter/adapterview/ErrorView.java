package com.leimo.common.adapter.adapterview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.leimo.common.R;
import com.leimo.common.adapter.statu.RefreshStatuEnum;

import java.lang.ref.WeakReference;

/**
 * Created by wr on 2017/4/17.
 */

public class ErrorView extends RelativeLayout {
    private WeakReference<Context> contextReference;
    private TextView tv;

    public ErrorView(Context context) {
        super(context);
        contextReference = new WeakReference<Context>(context);
        LayoutInflater.from(context).inflate(R.layout.item_error, this);
        tv = (TextView) findViewById(R.id.tv_error_tip);
    }

    public void setStatu(RefreshStatuEnum refreshStatuEnum) {
        if (refreshStatuEnum == null) {
            setVisibility(View.GONE);
            return;
        }
        switch (refreshStatuEnum) {
            case FIRST_FAIL:
                setVisibility(View.VISIBLE);
                tv.setText(getContext().getString(R.string.loadFailToRefresh));
                break;
            case NULL:
                setVisibility(View.VISIBLE);
                tv.setText(getContext().getString(R.string.loadNull));
                break;
            default:
                setVisibility(View.GONE);
                break;
        }
    }

}
