package com.example.clevermindpob.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clevermindpob.Models.NotificationItem;
import com.example.clevermindpob.R;

import java.util.List;

public class NotificationAdater extends RecyclerView.Adapter<NotificationAdater.AdapterHolder>{

    List<NotificationItem> items;

    public NotificationAdater(List<NotificationItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.soon_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        holder.setInfo(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AdapterHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.event_text);
        }
        void setInfo(NotificationItem info){
            txt.setText(info.getNotificationText().toString());
        }
    }
}
