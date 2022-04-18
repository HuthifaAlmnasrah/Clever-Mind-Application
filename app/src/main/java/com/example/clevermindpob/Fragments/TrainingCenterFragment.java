package com.example.clevermindpob.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clevermindpob.Models.TrainingItem;
import com.example.clevermindpob.R;

public class TrainingCenterFragment extends Fragment {

    public TrainingCenterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_training_center, container, false);
        TextView title = view.findViewById(R.id.title);
        title.setText(getString(R.string.training_center));
        ImageView back = view.findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        TrainingItem info = (TrainingItem) this.getArguments().getSerializable("info");
        TextView field = view.findViewById(R.id.field);
        field.setText(info.getTitle());
        ImageView img = view.findViewById(R.id.image);
        img.setImageResource(info.getImage());
        TextView days = view.findViewById(R.id.days);
        days.setText(info.getDays());
        TextView time = view.findViewById(R.id.time);
        time.setText(info.getTime());
        TextView desc = view.findViewById(R.id.desc);
        desc.setText(info.getCourseInfo());
        TextView price = view.findViewById(R.id.price);
        price.setText(info.getPrice());

        TextView courseInfo = view.findViewById(R.id.course_info);
        final boolean[] isExpanded = {false};
        ImageView expand = view.findViewById(R.id.expand);
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpanded[0]){
                    TransitionManager.beginDelayedTransition(container);
                    rotateUp(expand);
                    courseInfo.setVisibility(View.VISIBLE);
                    desc.setVisibility(View.VISIBLE);
                    isExpanded[0] = !isExpanded[0];
                }else{
                    TransitionManager.beginDelayedTransition(container);
                    rotateDown(expand);
                    courseInfo.setVisibility(View.GONE);
                    desc.setVisibility(View.GONE);
                    isExpanded[0] = !isExpanded[0];
                }
            }
        });
        Button attendNow = view.findViewById(R.id.btn_attend);
        attendNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                AttendedFragment fragment = new AttendedFragment();
                fragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.frame_container,
                        fragment
                        ).addToBackStack(null).commit();
            }
        });
        ImageView btnPrivate, btnPublic;
        TextView txtPrivate, txtPublic;
        btnPublic = view.findViewById(R.id.img1);
        btnPrivate = view.findViewById(R.id.img2);
        txtPrivate = view.findViewById(R.id.txt_private);
        txtPublic = view.findViewById(R.id.txt_public);
        txtPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPrivate.setImageResource(R.drawable.dot_blue);
                btnPublic.setImageResource(R.drawable.dot_selver);
            }
        });
        txtPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPrivate.setImageResource(R.drawable.dot_selver);
                btnPublic.setImageResource(R.drawable.dot_blue);
            }
        });
        btnPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPrivate.setImageResource(R.drawable.dot_blue);
                btnPublic.setImageResource(R.drawable.dot_selver);
            }
        });
        btnPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPrivate.setImageResource(R.drawable.dot_selver);
                btnPublic.setImageResource(R.drawable.dot_blue);
            }
        });

        return view;
    }
    public void rotateUp(View v) {
        v.setPivotX(v.getWidth() / 2);
        v.setPivotY(v.getHeight() / 2);
        v.setRotation(0);
        v.animate().setDuration(400).rotation(90);
        v.animate().setDuration(400).rotation(180);
    }
    public void rotateDown(View v) {
        v.setPivotX(v.getWidth() / 2);
        v.setPivotY(v.getHeight() / 2);
        v.animate().setDuration(400).rotation(270);
        v.animate().setDuration(400).rotation(360);
    }
}