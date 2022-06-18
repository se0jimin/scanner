package com.example.scanner;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

public class MainActivity<e_view> extends AppCompatActivity {

    ImageView imageView; //declaration of variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageLoading); //view.findViewbyID

        //handler --> wait for 2 sec --> intent the homeActivity --> then go to homeActivity
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    Intent intent = new Intent(getApplicationContext(), HomeActivityBackUp.class);
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        },2000);
    }
}