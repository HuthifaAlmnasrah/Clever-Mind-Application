package com.example.clevermindpob.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clevermindpob.Models.LatestNewsItem;
import com.example.clevermindpob.R;

import java.util.List;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.AdapterHolder>{
    List<LatestNewsItem> list;

    public LatestNewsAdapter(List<LatestNewsItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_news_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        holder.setInfo(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AdapterHolder extends RecyclerView.ViewHolder{

        TextView title, shortDesc, longDesc;
        ImageView image;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            shortDesc = itemView.findViewById(R.id.short_desc);
            longDesc = itemView.findViewById(R.id.long_desc);
            image = itemView.findViewById(R.id.image);
        }
        void setInfo(LatestNewsItem info){
            title.setText(info.getTitle());
            shortDesc.setText(info.getShortDesc());
            longDesc.setText(info.getLongDesc());
            image.setImageResource(info.getImage());
        }
    }
}
