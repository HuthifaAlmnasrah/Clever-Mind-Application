package com.example.clevermindpob.Fragments;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clevermindpob.Activites.MainActivity;
import com.example.clevermindpob.Adapters.LatestNewsAdapter;
import com.example.clevermindpob.Models.LatestNewsItem;
import com.example.clevermindpob.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LatestNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LatestNewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LatestNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LatestNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LatestNewsFragment newInstance(String param1, String param2) {
        LatestNewsFragment fragment = new LatestNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest_news, container, false);
        TextView title = view.findViewById(R.id.title);
        title.setText(getString(R.string.latest_news));
        ImageView back = view.findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        ImageView share = view.findViewById(R.id.notification);
        share.setVisibility(View.VISIBLE);
        share.setImageResource(R.drawable.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        List<LatestNewsItem> latestNewsItems = new ArrayList<>();
        latestNewsItems.add(new LatestNewsItem(
                "google", "last updates of developments","developments\\nwhat is mobiledom and google mobile farendal y update \\n on april 21, 2015, google relased", R.drawable.google
        ));
        latestNewsItems.add(new LatestNewsItem(
                "android", "developers websites","developments\\nwhat is mobiledom and google mobile farendal y update \\n on april 21, 2015, google relased", R.drawable.android
        ));
        latestNewsItems.add(new LatestNewsItem(
                "ios", "last updates","developments\\nwhat is mobiledom and google mobile farendal y update \\n on april 21, 2015, google relased", R.drawable.ios_logo
        ));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new LatestNewsAdapter(latestNewsItems));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}