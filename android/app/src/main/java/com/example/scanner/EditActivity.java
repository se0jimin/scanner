package com.example.scanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EditActivity extends Activity {

        RecyclerView recyclerView;
        int images[] = {R.drawable.bear, R.drawable.dog, R.drawable.cat};
        String names[] = {"William", "Brian", "Elizabeth"};
        String description[] = {"This is a bear", "This is a dog", "This is a cat"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_edit);

                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter( this, names, description, images);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
}
