package com.example.clevermindpob.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.clevermindpob.Fragments.LatestNewsFragment;
import com.example.clevermindpob.Models.NewsItem;
import com.example.clevermindpob.R;

import java.util.List;

public class NewsAdapter extends PagerAdapter {
    private List<NewsItem> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public NewsAdapter(List<NewsItem> models, Context context) {
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
        View view = layoutInflater.inflate(R.layout.news_item, container, false);
        TextView title, description;
        ImageView image;
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        image = view.findViewById(R.id.image);

        title.setText(models.get(position).getTitle());
        description.setText(models.get(position).getDescription());
        image.setImageResource(models.get(position).getImage());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
