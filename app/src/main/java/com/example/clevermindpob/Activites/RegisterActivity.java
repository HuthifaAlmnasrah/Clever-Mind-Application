package com.example.clevermindpob.Activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clevermindpob.R;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

public class RegisterActivity extends AppCompatActivity {

    public static final int CAMERA_IMAGE_CODE = 1;
    public static final int GALLERY_IMAGE_CODE = 2;

    private String cameraPermission[];
    private String storagePermission[];

    private ImageView back;
    private ImageView login;
    private CircleImageView profileImg;
    private EditText fullName, userName, email, password, dateOfBirth, phoneNumber;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allInfoExist()){
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        profileImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                int picd = 0;
                if(picd == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromGallery();
                    }
                }else if(picd == 1){
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        });
    }
    private void changeProfileImageByGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, GALLERY_IMAGE_CODE);
            } else {
                pickImageFromGallery();
            }
        } else {
            pickImageFromGallery();
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_IMAGE_CODE);
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_IMAGE_CODE);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case GALLERY_IMAGE_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    pickImageFromGallery();
//                }
//                break;
//            case CAMERA_IMAGE_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    takePicture();
//                }
//                break;
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == GALLERY_IMAGE_CODE) {
//            profileImg.setImageURI(data.getData());
//        }
//        if (resultCode == RESULT_OK && requestCode == CAMERA_IMAGE_CODE) {
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            profileImg.setImageBitmap(bitmap);
//        }
//    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_IMAGE_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission, GALLERY_IMAGE_CODE);
    }
    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(profileImg);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_IMAGE_CODE: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == (PackageManager.PERMISSION_GRANTED);
                    boolean storage_accepted = grantResults[1] == (PackageManager.PERMISSION_GRANTED);
                    if (camera_accepted && storage_accepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, getString(R.string.ebable_camera_and_storage), Toast.LENGTH_SHORT).show();
                    }
                }

            }
            break;
            case GALLERY_IMAGE_CODE: {
                if (grantResults.length > 0) {
                    boolean storage_accepted = grantResults[0] == (PackageManager.PERMISSION_GRANTED);
                    if (storage_accepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, getString(R.string.enable_storage_permission), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    private boolean allInfoExist() {
        if(fullName.getText().toString().isEmpty()){
            fullName.setError(getString(R.string.fullname_here));
            return false;
        }else{
            fullName.setError(null);
        }
        if(userName.getText().toString().isEmpty()){
            userName.setError(getString(R.string.username_here));
            return false;
        }else{
            userName.setError(null);
        }
        if(email.getText().toString().isEmpty()){
            email.setError(getString(R.string.email_here));
            return false;
        }else{
            email.setError(null);
        }
        if(phoneNumber.getText().toString().isEmpty()){
            phoneNumber.setError(getString(R.string.phone_here));
            return false;
        }else{
            phoneNumber.setError(null);
        }
        if(password.getText().toString().isEmpty()){
            password.setError(getString(R.string.phone_here));
            return false;
        }else{
            password.setError(null);
        }
        if(dateOfBirth.getText().toString().isEmpty()){
            dateOfBirth.setError(getString(R.string.date_here));
            return false;
        }else{
            dateOfBirth.setError(null);
        }
        return true;
    }

    private void initViews() {
        back = findViewById(R.id.btn_back);
        login = findViewById(R.id.login);
        profileImg = findViewById(R.id.profile_img);
        fullName = findViewById(R.id.fullname);
        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phone_number);
        dateOfBirth = findViewById(R.id.date_of_birth);
        title = findViewById(R.id.title);
        title.setText(getString(R.string.signup));
    }
}