package com.leimo.common.adapter.adapterview;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.leimo.common.R;
import com.leimo.common.adapter.statu.RefreshStatuEnum;

import java.lang.ref.WeakReference;

/**
 * Created by wr on 2017/4/17.
 */

public class FooterView extends RelativeLayout {
    private WeakReference<Context> contextReference;
    private TextView tv;
    private ProgressBar progressBar;

    public FooterView(Context context) {
        super(context);
        contextReference = new WeakReference<Context>(context);
        LayoutInflater.from(context).inflate(R.layout.item_foot, this);
        tv = (TextView) findViewById(R.id.tv_foot_name);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_footer);
    }

    public void setStatu(RefreshStatuEnum refreshStatuEnum) {
        if (refreshStatuEnum == null) {
            setVisibility(View.GONE);
            return;
        }
        switch (refreshStatuEnum) {
            case LOAD:
                setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                tv.setText(getContext().getString(R.string.loading));
                break;
            case LOAD_SUC:
                setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tv.setText(getContext().getString(R.string.loadOver));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (contextReference != null) {
                            setVisibility(View.GONE);
                        }
                    }
                }, 600);
                break;
            case LOAD_FAIL:
                setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tv.setText(getContext().getString(R.string.loadFail));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (contextReference != null) {
                            setVisibility(View.GONE);
                        }
                    }
                }, 600);
                break;
            case LOAD_OVER_ALL:
                setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tv.setText(getContext().getString(R.string.loadOverAll));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (contextReference != null) {
                            setVisibility(View.GONE);
                        }
                    }
                }, 600);
                break;
            default:
                setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                break;
        }
    }

}
