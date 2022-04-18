package com.example.clevermindpob.Activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clevermindpob.Adapters.NotificationAdater;
import com.example.clevermindpob.Fragments.AttendedFragment;
import com.example.clevermindpob.Fragments.JobsFragment;
import com.example.clevermindpob.Fragments.LatestEventsFragment;
import com.example.clevermindpob.Fragments.LatestNewsFragment;
import com.example.clevermindpob.Fragments.MainFragment;
import com.example.clevermindpob.Fragments.PaymentFragment;
import com.example.clevermindpob.Fragments.PolicyFragment;
import com.example.clevermindpob.Fragments.ProfileFragment;
import com.example.clevermindpob.Fragments.SettingsFragment;
import com.example.clevermindpob.Models.NotificationItem;
import com.example.clevermindpob.R;
import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView profile, attended, latestEvents, jobs, settings, payment, policyAndPrivacy;
    private RelativeLayout logout;
    public static DrawerLayout drawerLayout;
    private ImageView closeMenu;
    private RecyclerView notificationRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initsViews();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(
                    R.id.frame_container,
                    new MainFragment()
            ).addToBackStack(null).commit();
        }
    }

    private void initsViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        closeMenu = findViewById(R.id.close_menu);
        closeMenu.setOnClickListener(this);
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(this);
        attended = findViewById(R.id.attended);
        attended.setOnClickListener(this);
        latestEvents = findViewById(R.id.latest_events);
        latestEvents.setOnClickListener(this);
        jobs = findViewById(R.id.jobs);
        jobs.setOnClickListener(this);
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);
        payment = findViewById(R.id.payment);
        payment.setOnClickListener(this);
        policyAndPrivacy = findViewById(R.id.policy_and_privacy);
        policyAndPrivacy.setOnClickListener(this);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
        notificationRecycler = findViewById(R.id.notification_rec);
        List<NotificationItem> items = new ArrayList<>();
        items.add(new NotificationItem("EDUCATIONS STUDENTS IN ALL OVER THE WORLD!"));
        items.add(new NotificationItem("HAPPY PROGRAMMING DAY THE WORLD!"));
        items.add(new NotificationItem("QA SESSION TALKING ABOUT TESTING QA SESSION TALKING ABOUT TESTING QA SESSION TALKING ABOUT TESTING"));
        notificationRecycler.setAdapter(new NotificationAdater(items));
        notificationRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_container,
                        new ProfileFragment()
                ).addToBackStack(null).commit();
                onBackPressed();
                break;
            case R.id.attended:
                AttendedFragment fragment = new AttendedFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_container,
                        fragment
                ).addToBackStack(null).commit();
                onBackPressed();
                break;
            case R.id.latest_events:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_container,
                        new LatestEventsFragment()
                ).addToBackStack(null).commit();
                onBackPressed();
                break;
            case R.id.jobs:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_container,
                        new JobsFragment()
                ).addToBackStack(null).commit();
                onBackPressed();
                break;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_container,
                        new SettingsFragment()
                ).addToBackStack(null).commit();
                onBackPressed();
                break;
            case R.id.payment:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_container,
                        new PaymentFragment()
                ).addToBackStack(null).commit();
                onBackPressed();
                break;
            case R.id.policy_and_privacy:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_container,
                        new PolicyFragment()
                ).addToBackStack(null).commit();
                onBackPressed();
                break;
            case R.id.logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.close_menu:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            String s = "";
            InputStream inputStream = null;
            try {
                inputStream = MainActivity.this.getContentResolver().openInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte []pdfBytes = new byte[0];
            try {
                pdfBytes = new byte[inputStream.available()];
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                inputStream.read(pdfBytes);
                s = Base64.encodeToString(pdfBytes, Base64.DEFAULT);
                Toast.makeText(MainActivity.this, "CV SELECTED", Toast.LENGTH_LONG).show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}