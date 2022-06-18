package com.example.scanner;

import android.content.Context;
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
    int images[];
    Context context;
    String description[];

    public RecyclerAdapter(String[] data, Context context, int[] images, String[] description) {
        this.context = context;
        this.data = data;
        this.images = images;
       this.description = description;
    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    System.out.println("OnCreateViewHolder");
        //LayoutInflater inflater = LayoutInflater.from(context);
        //View view = inflater.inflate(R.layout.activity_row, parent, false);
        //return new MyViewHolder(view);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_row, parent, false);
        return new  MyViewHolder((View) view);

//         View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.adapter_textview, parent, false);
//
//        MyViewHolder viewHolder = new MyViewHolder((TextView) view);
//        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleText.setText(data[position]);
        holder.myImageView.setImageResource(images[position]);
        holder.myImageView.setImageResource(images[position]);
        //holder.View.setText(data[position]);

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
            System.out.println("newViewHolder");
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            System.out.println("singing~~");
            myImageView = itemView.findViewById(R.id.myImageView);

//        public View view;
//        public BreakIterator titleText;

//        public MyViewHolder(View view) {
//            super(view);
//            this.view = view;

       }
    }
}