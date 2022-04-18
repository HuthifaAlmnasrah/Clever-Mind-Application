package com.example.clevermindpob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.clevermindpob.Models.EventItem;
import com.example.clevermindpob.R;

import java.util.List;

public class EventsAdapter extends PagerAdapter {

    private List<EventItem> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public EventsAdapter(List<EventItem> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.event_item, container, false);

        TextView title, day, date, time;
        title = view.findViewById(R.id.title);
        day = view.findViewById(R.id.day);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);

        title.setText(models.get(position).getTitle());
        day.setText(models.get(position).getDay());
        date.setText(models.get(position).getDate());
        time.setText(models.get(position).getTime());

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
