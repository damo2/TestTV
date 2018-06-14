package com.leimo.common.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * 分割线
 */
public class GridBaseItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacingH;
    private int spacingV;
    private boolean includeEdge;

    public GridBaseItemDecoration(Context context, int spanCount, @DimenRes int spacingH, @DimenRes int spacingV, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacingH = context.getResources().getDimensionPixelSize(spacingH);
        this.spacingV = context.getResources().getDimensionPixelSize(spacingV);
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column
        if (includeEdge) {
            outRect.left = spacingH - column * spacingH / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacingH / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacingV;
            }
            outRect.bottom = spacingV; // item bottom
        } else {
            outRect.left = column * spacingH / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacingH - (column + 1) * spacingH / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacingV; // item top
            }
        }
    }
}
