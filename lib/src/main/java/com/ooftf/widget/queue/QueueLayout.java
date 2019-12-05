package com.ooftf.widget.queue;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/11/12
 */
public class QueueLayout extends RecyclerView {

    public QueueLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public QueueLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QueueLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    int current = 0;

    private void init() {
        smoothScrollToPosition(current);
    }

    Disposable subscribe;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isAutoScroll) {
            start();
        }
    }

    private void start() {
        subscribe = Observable.interval(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Adapter adapter = getAdapter();
                if (adapter != null && adapter.getItemCount() - 1 > getEnd() && isShouldAutoScroll()) {
                    smoothScrollToPosition(getEnd() + 1);
                }
            }
        });
    }


    boolean isShouldAutoScroll() {
        if (!touchEnable) {
            return true;
        }

        if (!isTouching) {
            return true;
        }
        return false;
    }

    int getFirst() {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        return 0;
    }

    private boolean isAutoScroll = false;

    public void setAutoScroll(boolean isAutoScroll) {
        this.isAutoScroll = isAutoScroll;
        if (isAutoScroll) {
            start();
        } else {
            if (subscribe != null) {
                subscribe.dispose();
            }
        }
    }

    int getEnd() {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        return 0;
    }

    boolean isTouching = false;

    @Override
    public void smoothScrollToPosition(int position) {
        super.smoothScrollToPosition(position);
        current = position;
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
        current = position;
    }

    @Override
    protected void onDetachedFromWindow() {
        if (subscribe != null) {
            subscribe.dispose();
        }
        super.onDetachedFromWindow();
    }

    boolean touchEnable = true;

    public void setTouchEnable(boolean toucheEnable) {
        this.touchEnable = toucheEnable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (touchEnable) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                isTouching = true;
            } else if (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL) {
                isTouching = false;
            }
            return super.onTouchEvent(e);
        } else {
            return false;
        }
    }
}
