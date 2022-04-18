package com.example.clevermindpob.Fragments;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clevermindpob.Activites.MainActivity;
import com.example.clevermindpob.R;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView title = view.findViewById(R.id.title);
        title.setText(getString(R.string.profile));
        ImageView back = view.findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        EditText fullName, userName, email, phoneNumber, dateOfBirth;
        fullName = view.findViewById(R.id.fullname);
        fullName.setEnabled(false);
        userName = view.findViewById(R.id.username);
        userName.setEnabled(false);
        email = view.findViewById(R.id.email);
        email.setEnabled(false);
        phoneNumber = view.findViewById(R.id.phone_number);
        phoneNumber.setEnabled(false);
        dateOfBirth = view.findViewById(R.id.date_of_birth);
        dateOfBirth.setEnabled(false);
        TextView edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName.setEnabled(true);
                userName.setEnabled(true);
                email.setEnabled(true);
                phoneNumber.setEnabled(true);
                dateOfBirth.setEnabled(true);
                edit.setText(getString(R.string.save));
            }
        });
        ImageView imgEdit = view.findViewById(R.id.img_edit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName.setEnabled(true);
                userName.setEnabled(true);
                email.setEnabled(true);
                phoneNumber.setEnabled(true);
                dateOfBirth.setEnabled(true);
                edit.setText(getString(R.string.save));
            }
        });
        view.findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        view.findViewById(R.id.notification).setVisibility(View.VISIBLE);
        return view;
    }
}