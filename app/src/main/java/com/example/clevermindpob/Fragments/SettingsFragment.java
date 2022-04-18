package com.example.clevermindpob.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clevermindpob.R;

import java.util.Locale;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    EditText oldPassword, newPassword, rePassword, feedback;
    Button arabic, english, send;
    ImageView star1, star2, star3, star4, star5;

    public SettingsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        TextView title = view.findViewById(R.id.title);
        title.setText(R.string.settings);
        ImageView back = view.findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        oldPassword = view.findViewById(R.id.old_password);
        newPassword = view.findViewById(R.id.new_password);
        rePassword = view.findViewById(R.id.re_password);
        arabic = view.findViewById(R.id.ar);
        arabic.setOnClickListener(this);
        english = view.findViewById(R.id.en);
        english.setOnClickListener(this);
        send = view.findViewById(R.id.send);
        send.setOnClickListener(this);
        star1 = view.findViewById(R.id.star1);
        star1.setOnClickListener(this);
        star2 = view.findViewById(R.id.star2);
        star2.setOnClickListener(this);
        star3 = view.findViewById(R.id.star3);
        star3.setOnClickListener(this);
        star4 = view.findViewById(R.id.star4);
        star4.setOnClickListener(this);
        star5 = view.findViewById(R.id.star5);
        star5.setOnClickListener(this);

        loadLocale();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ar:
                setLocale("ar");
                getActivity().recreate();
                break;
            case R.id.en:
                setLocale("en");
                getActivity().recreate();
                break;
            case R.id.star1:
                star1.setImageResource(R.drawable.star_one);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case R.id.star2:
                star1.setImageResource(R.drawable.star_one);
                star2.setImageResource(R.drawable.star_one);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                    break;
            case R.id.star3:
                star1.setImageResource(R.drawable.star_one);
                star2.setImageResource(R.drawable.star_one);
                star3.setImageResource(R.drawable.star_one);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case R.id.star4:
                star1.setImageResource(R.drawable.star_one);
                star2.setImageResource(R.drawable.star_one);
                star3.setImageResource(R.drawable.star_one);
                star4.setImageResource(R.drawable.star_one);
                star5.setImageResource(R.drawable.star);
                break;
            case R.id.star5:
                star1.setImageResource(R.drawable.star_one);
                star2.setImageResource(R.drawable.star_one);
                star3.setImageResource(R.drawable.star_one);
                star4.setImageResource(R.drawable.star_one);
                star5.setImageResource(R.drawable.star_one);
                break;
            case R.id.send:
                Toast.makeText(getContext(), getString(R.string.your_message_sent), Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getContext().getResources().updateConfiguration(configuration, getContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getContext().getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putString("my_lang", language);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences preferences = getContext().getSharedPreferences("settings", MODE_PRIVATE);
        String language = preferences.getString("my_lang", "");
        if(language.equals("ar")){
            arabic.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disable)));
            arabic.setClickable(false);
            english.setClickable(true);
        }else{
            english.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disable)));
            arabic.setClickable(true);
            english.setClickable(false);
        }
        setLocale(language);
    }
}