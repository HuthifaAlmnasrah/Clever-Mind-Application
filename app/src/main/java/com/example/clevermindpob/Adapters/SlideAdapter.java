package com.example.clevermindpob.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.clevermindpob.Fragments.LatestNewsFragment;
import com.example.clevermindpob.Fragments.TrainingCenterFragment;
import com.example.clevermindpob.Models.NewsItem;
import com.example.clevermindpob.R;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{

    private List<NewsItem> items;
    private ViewPager2 viewPager2;

    public SlideAdapter(List<NewsItem> items, ViewPager2 viewPager2) {
        this.items = items;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false)
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

    class SlideViewHolder extends RecyclerView.ViewHolder{
        private TextView title, desc;
        private ImageView image;
        private CardView cardView;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                            new LatestNewsFragment()
                    ).addToBackStack(null).commit();
                }
            });
        }
        void setInfo(NewsItem info){
            title.setText(info.getTitle());
            desc.setText(info.getDescription());
            image.setImageResource(info.getImage());
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
