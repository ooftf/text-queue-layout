package com.ooftf.widget.queue;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        subscribe = Observable.interval(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {

            @Override
            public void accept(Long aLong) throws Exception {
                RecyclerView.Adapter adapter = getAdapter();

                if (adapter != null && adapter.getItemCount() > current) {
                    current++;
                    smoothScrollToPosition(current);
                }
            }
        });
    }

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


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
