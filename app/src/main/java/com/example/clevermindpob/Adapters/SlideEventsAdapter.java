package com.example.clevermindpob.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.clevermindpob.Models.EventItem;
import com.example.clevermindpob.R;

import java.util.List;

public class SlideEventsAdapter extends RecyclerView.Adapter<SlideEventsAdapter.SlideViewHolder>{

    private List<EventItem> items;
    private ViewPager2 viewPager2;

    public SlideEventsAdapter(List<EventItem> items, ViewPager2 viewPager2) {
        this.items = items;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.setInfo(items.get(position));
        if(position == items.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder {
        TextView title, day, date, time;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
        void setInfo(EventItem info){
            title.setText(info.getTitle());
            day.setText(info.getDay());
            date.setText(info.getDate());
            time.setText(info.getTime());
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            items.addAll(items);
            notifyDataSetChanged();
        }
    };
}
