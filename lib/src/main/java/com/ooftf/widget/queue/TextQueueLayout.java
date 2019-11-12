package com.ooftf.widget.queue;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/11/12
 */
public class TextQueueLayout<T> extends FrameLayout {

    public TextQueueLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public TextQueueLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextQueueLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextQueueLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    RecyclerView recyclerView;

    int itemLayoutId;

    private void init() {
        final LayoutInflater from = LayoutInflater.from(getContext());
        from.inflate(R.layout.queue_layout_main, this);
        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        /*recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(from.inflate(itemLayoutId, parent)) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        });*/
        //new PagerSnapHelper().
        recyclerView.smoothScrollToPosition(0);
        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                recyclerView.getVerticalScrollbarPosition();
            }
        });
    }

    ArrayList<T> data = new ArrayList<>();

    public void addItem(String string) {

    }

    interface Display<T> {
        void display(View view, T item);
    }
}
