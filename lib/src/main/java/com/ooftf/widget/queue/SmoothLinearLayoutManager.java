package com.ooftf.widget.queue;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/11/13
 */
public class SmoothLinearLayoutManager extends LinearLayoutManager {
    public SmoothLinearLayoutManager(Context context) {
        super(context);
    }

    public SmoothLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public SmoothLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller2 linearSmoothScroller =
                new LinearSmoothScroller2(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    class LinearSmoothScroller2 extends LinearSmoothScroller {

        public LinearSmoothScroller2(Context context) {
            super(context);
        }


        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            //2 / displayMetrics.densityDpi
            return 1;
        }
    }

}
