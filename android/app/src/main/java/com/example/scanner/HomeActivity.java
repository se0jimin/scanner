package com.example.scanner;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class HomeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.class);
                startActivity(intent);
            }
        },200); //run after 200 milli sec
    }
}
