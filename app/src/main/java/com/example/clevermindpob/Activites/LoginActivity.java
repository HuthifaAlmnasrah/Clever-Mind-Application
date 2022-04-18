package com.example.clevermindpob.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clevermindpob.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView signUp, forgetPassword;
    private ImageView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allInfoExist()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void showDialog() {
        Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.forget_password_dialog);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        Button send = dialog.findViewById(R.id.send_new_password);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, getString(R.string.send_new_pass), Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private boolean allInfoExist() {
        if(email.getText().toString().isEmpty()){
            email.setError(getString(R.string.email_here));
            return false;
        }else{
            email.setError(null);
        }
        if(password.getText().toString().isEmpty()){
            password.setError(getString(R.string.password_here));
            return false;
        }else{
            password.setError(null);
        }
        return true;
    }

    private void initViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signup);
        forgetPassword = findViewById(R.id.forget_password);
        login = findViewById(R.id.login);
    }
}