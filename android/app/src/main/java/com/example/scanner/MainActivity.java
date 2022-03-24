package com.example.scanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.view.*;

public class MainActivity extends AppCompatActivity {
    //Variables
    ImageView imageView;
    Button btOpen;
    Handler handler;

    //Assign variables
    imageView = view.findViewByID(R.id.image_view); //view.findViewbyID
    btCamera = view.findViewByID(R.id.bt.open);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //request for camera acesss
        if (ContextCompat.checkSelfPermission(
                context: MainActivity.this,
//this가 뭐였죠? common use of the this keyword is to eliminate the confusion between class attributes and parameters with the same name
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { // != "not"
            // if camera is not granted then the following code will follow up for request permissions
            ActivityCompat.requestPermissions( activity: MainActivity.this,
            new String[] {
                        Manifest.permission.CAMERA
            }.
                                requestCode: 100);

            // press button to move to Camera
            btCamera.setOnClickListener(new View.OnClickListener() {
                @Override // when there is a parent class, the child kid receives all the ]
                // information, so when the child class wants to rewrite the code, we use "Override"
                public void onClick(View view) {
                    // open camera
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, requestCode: 100);
                }
            });
            @Override
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                if (requestCode == 100) {
                    //get capture image
                    Bitmap captureImage = (Bitmap) data.getExtras().get("data"); //bitmap is a from of image
                    //Set Capture Image to ImageView
                    imageView.setImageBitmap(captureImage);
                }
        }

            final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable()) {
                @Override


        }
    }
}