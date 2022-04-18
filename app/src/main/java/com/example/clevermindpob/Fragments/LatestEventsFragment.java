package com.example.clevermindpob.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clevermindpob.Adapters.EventsAdapter;
import com.example.clevermindpob.Adapters.SlideAdapter;
import com.example.clevermindpob.Adapters.SlideEventsAdapter;
import com.example.clevermindpob.Adapters.SoonAdapter;
import com.example.clevermindpob.Models.EventItem;
import com.example.clevermindpob.Models.SoonItem;
import com.example.clevermindpob.R;

import java.util.ArrayList;
import java.util.List;

public class LatestEventsFragment extends Fragment {


    private ViewPager2 viewPager;
    private Handler slideHandler = new Handler();

    public LatestEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest_events, container, false);
        TextView title = view.findViewById(R.id.title);
        title.setText(getString(R.string.latest_events));
        ImageView back = view.findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        List<SoonItem> items = new ArrayList<>();
        items.add(new SoonItem("EDUCATIONS STUDENTS IN ALL OVER THE WORLD!"));
        items.add(new SoonItem("HAPPY PROGRAMMING DAY THE WORLD!"));
        items.add(new SoonItem("QA SESSION TALKING ABOUT TESTING"));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new SoonAdapter(items));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        List<EventItem> list = new ArrayList<>();
        list.add(new EventItem("POWER YOUTH EVENT IN IRBID NOW!","SUNDAY","23-1-2019","11:00PM-2:00PM"));
        list.add(new EventItem("POWER YOUTH EVENT IN IRBID NOW!","SUNDAY","23-1-2019","11:00PM-2:00PM"));
        list.add(new EventItem("POWER YOUTH EVENT IN IRBID NOW!","SUNDAY","23-1-2019","11:00PM-2:00PM"));

        EventsAdapter eventsAdapter = new EventsAdapter(list, getContext());
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new SlideEventsAdapter(list, viewPager));
        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable,3000);
            }
        });

        ImageView left, right;
        left = view.findViewById(R.id.left);
        right = view.findViewById(R.id.rigth);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        return view;
    }
    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };
}