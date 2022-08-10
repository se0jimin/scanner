package com.example.scanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    String data[];
    Bitmap images[];
    Context context;
    String description[];

    public RecyclerAdapter(String[] data, Context context, Bitmap[] images, String[] description) {
        this.context = context;
        this.data = data;
        this.images = images;
        this.description = description;
    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    System.out.println("OnCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_row, parent, false);
        return new  MyViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleText.setText(data[position]);
        holder.descriptionText.setText(description[position]);
        holder.myImageView.setImageBitmap(images[position]);
    }

    @Override
    public int getItemCount() {
        System.out.println("^^ " + data.length);
        return data.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleText, descriptionText;
        ImageView myImageView;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            myImageView = itemView.findViewById(R.id.myImageView);
       }
    }
}