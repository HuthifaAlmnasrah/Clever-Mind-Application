package com.example.clevermindpob.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clevermindpob.Adapters.TariningAdapter;
import com.example.clevermindpob.Models.TrainingItem;
import com.example.clevermindpob.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttendedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttendedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendedFragment newInstance(String param1, String param2) {
        AttendedFragment fragment = new AttendedFragment();
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
        View view = inflater.inflate(R.layout.fragment_attended, container, false);
        TextView title = view.findViewById(R.id.title);
        title.setText(getString(R.string.attended));
        ImageView back = view.findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
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
                new TrainingItem("OA & PM FOR MOBILE APPS AND WEBSITES", "MON - THU", "9:30AM - 1:00PM", R.drawable.qa,
                        "advanced android devlopment\n in the advanced android devlopment training, youtake your android coding skils to the next level the course teaches you ways to expand the user experience, improve your apps performance, and add advanced features like custom vies, animations, and location-awareness. each lesson includes a tutorial with solution code in github, concept documentation, and a sude deck.",
                        "150 JD")
        );

        if(!getArguments().isEmpty()){
            TrainingItem info = (TrainingItem) this.getArguments().getSerializable("info");
            trainItems.add(info);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TariningAdapter adapter = new TariningAdapter(trainItems,"AttendedFragment");
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        return view;
    }
}