package com.ooftf.master.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ooftf.widget.queue.SmoothLinearLayoutManager;
import com.ooftf.widget.queue.QueueLayout;

public class MainActivity extends AppCompatActivity {
    QueueLayout textQueueLayout;
    View button;
    int current = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textQueueLayout = findViewById(R.id.textQueueLayout);
        button = findViewById(R.id.button);
        textQueueLayout.setLayoutManager(new SmoothLinearLayoutManager(this, RecyclerView.VERTICAL, true));
        textQueueLayout.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false)) {

                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView.findViewById(R.id.text)).setText("position::" + position);
            }

            @Override
            public int getItemCount() {
                return current;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current++;
                textQueueLayout.getAdapter().notifyItemInserted(current);
            }
        });
    }
}
