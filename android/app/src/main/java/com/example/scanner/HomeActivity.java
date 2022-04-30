package com.example.scanner;

import static androidx.core.content.ContextCompat.startActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class HomeActivity extends Activity {

    ImageView homeImage;
    ImageView cameraPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            cameraPic = (ImageView) findViewById(R.id.cameraPic);
        // connecting with the activity_home.xml and naming of IDS

        //request for camera acesss
        if (ContextCompat.checkSelfPermission(
                HomeActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { // != "not"
            // if camera is not granted then the following code will follow up for request permissions
            ActivityCompat.requestPermissions(HomeActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);

            // press button to move to Camera
            cameraPic.setOnClickListener(new View.OnClickListener() {
                @Override // when there is a parent class, the child kid receives all the ]
                // information, so when the child class wants to rewrite the code, we use "Override"
                public void onClick(View view) {
                    // open camera
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 100);
                }
            });
            /*
            // need another explanation from this part
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(String.valueOf(this));
                    startActivity(intent);
                }
            }, 200); //run after 200 milli sec */
        }
    }
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data)
    {
        try {
        System.out.println("rq: "+ requestCode);
        if (requestCode == 100) {
            //get capture image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data"); //bitmap is a from of image
            //Set Capture Image to ImageView
            //cameraPic.setImageBitmap(captureImage);
            //handler --> wait for 2 sec --> intent the homeActivity --> then go to homeActivity
            final Handler handler = new Handler(Looper.getMainLooper());
            System.out.println("hi");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hey");
                    try{
                        System.out.println("yo");
                        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
        } } catch (Exception e) {
            System.out.println(e);
        }
    }
}