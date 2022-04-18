package com.example.clevermindpob.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
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

import com.example.clevermindpob.Activites.MainActivity;
import com.example.clevermindpob.Adapters.NewsAdapter;
import com.example.clevermindpob.Adapters.SlideAdapter;
import com.example.clevermindpob.Adapters.TariningAdapter;
import com.example.clevermindpob.Models.NewsItem;
import com.example.clevermindpob.Models.TrainingItem;
import com.example.clevermindpob.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private ViewPager2 viewPager;
    private Handler slideHandler = new Handler();
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView showMenu = view.findViewById(R.id.show_menu);
        showMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        ImageView notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        TextView latestNews = view.findViewById(R.id.latest_news);
        latestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.frame_container,
                new LatestNewsFragment()).addToBackStack(null).commit();
            }
        });

        List<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(
                new NewsItem("GOOGLE", "LAST UPDATE OF DEVELOPMENTS", R.drawable.google)
        );
        newsItems.add(
                new NewsItem("ANDROID", "LAST UPDATE OF DEVELOPMENTS", R.drawable.android)
        );
        newsItems.add(
                new NewsItem("IOS", "LAST UPDATE OF DEVELOPMENTS", R.drawable.ios_logo)
        );
        NewsAdapter adapter = new NewsAdapter(newsItems, getContext());
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new SlideAdapter(newsItems, viewPager));
        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r =  1 - Math.abs(position);
                page.setScaleY(0.85f + r *0.15f );
            }
        });
        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable,3000);
            }
        });

        List<TrainingItem> trainItems = new ArrayList<>();
        trainItems.add(
                new TrainingItem("ANDROID FULL TRACK DEVELOPMENT", "SUN - THU", "10:00AM - 1:00PM", R.drawable.android,
                        "advanced android devlopment\n in the advanced android devlopment training, youtake your android coding skils to the next level the course teaches you ways to expand the user experience, improve your apps performance, and add advanced features like custom vies, animations, and location-awareness. each lesson includes a tutorial with solution code in github, concept documentation, and a sude deck.",
                        "150 JD")
        );
        trainItems.add(
                new TrainingItem("IOS FULL TRACK DEVELOPMENT", "SAT - TUS", "11:00AM - 2:00PM", R.drawable.ios_logo,
                        "advanced android devlopment\n in the advanced android devlopment training, youtake your android coding skils to the next level the course teaches you ways to expand the user experience, improve your apps performance, and add advanced features like custom vies, animations, and location-awareness. each lesson includes a tutorial with solution code in github, concept documentation, and a sude deck.",
                        "150 JD")
        );
        trainItems.add(
                new TrainingItem("OA $ PM FOR MOBILE APPS AND WEBSITES", "MON - THU", "9:30AM - 1:00PM", R.drawable.qa,
                        "advanced android devlopment\n in the advanced android devlopment training, youtake your android coding skils to the next level the course teaches you ways to expand the user experience, improve your apps performance, and add advanced features like custom vies, animations, and location-awareness. each lesson includes a tutorial with solution code in github, concept documentation, and a sude deck.",
                        "150 JD")
        );
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TariningAdapter(trainItems, "MainFragment"));
        recyclerView.setHasFixedSize(true);

        ImageView left, right;
        left = view.findViewById(R.id.left);
        right = view.findViewById(R.id.right);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
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