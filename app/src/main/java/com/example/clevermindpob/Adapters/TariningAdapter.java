package com.example.clevermindpob.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clevermindpob.Fragments.TrainingCenterFragment;
import com.example.clevermindpob.Models.TrainingItem;
import com.example.clevermindpob.R;

import java.util.List;

public class TariningAdapter extends RecyclerView.Adapter<TariningAdapter.AdapterHolder>{

    List<TrainingItem> items;
    String faragmentName;

    public TariningAdapter(List<TrainingItem> items, String faragmentName) {
        this.items = items;
        this.faragmentName = faragmentName;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.setInfo(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AdapterHolder extends RecyclerView.ViewHolder {

        private TextView title, days, time, attendNow;
        private ImageView image, cancel, go;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            days = itemView.findViewById(R.id.days);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.image);
            cancel = itemView.findViewById(R.id.cancel);
            go = itemView.findViewById(R.id.go);
            attendNow = itemView.findViewById(R.id.attend_now);
            if(faragmentName.equals("AttendedFragment")){
                cancel.setVisibility(View.VISIBLE);
                attendNow.setText(itemView.getResources().getText(R.string.attended));
                attendNow.setTextColor(Color.rgb(0,200,0));
            }
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    items.remove(getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info",items.get(getAdapterPosition()));
                    TrainingCenterFragment fragment = new TrainingCenterFragment();
                    fragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        fragment
                    ).addToBackStack(null).commit();
                }
            });
            attendNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info",items.get(getAdapterPosition()));
                    TrainingCenterFragment fragment = new TrainingCenterFragment();
                    fragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                            fragment
                    ).addToBackStack(null).commit();
                }
            });
        }
        void setInfo(TrainingItem info){
            title.setText(info.getTitle());
            days.setText(info.getDays());
            time.setText(info.getTime());
            image.setImageResource(info.getImage());
        }
    }
}
