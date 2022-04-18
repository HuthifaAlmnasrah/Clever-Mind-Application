package com.example.clevermindpob.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clevermindpob.Activites.MainActivity;
import com.example.clevermindpob.Models.JobItem;
import com.example.clevermindpob.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.AdapterHolder> {

    List<JobItem> items;
    Context context;

    public JobsAdapter(List<JobItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false)
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

    class AdapterHolder extends RecyclerView.ViewHolder{

        private TextView title, experience;
        private ImageView image, uploadCV;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            experience = itemView.findViewById(R.id.experience);
            image = itemView.findViewById(R.id.image);
            uploadCV = itemView.findViewById(R.id.img_cv);
            uploadCV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadCV();
                }
            });
        }
        void setInfo(JobItem info){
            title.setText(info.getTitle());
            experience.setText(info.getExperience());
            image.setImageResource(info.getImage());
        }
    }
    private void uploadCV() {
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            intent = Intent.createChooser(intent, "Choose a file");
            ActivityCompat.startActivityForResult((Activity) context, intent, 1 , null);
        }else{
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }
}
