package com.example.scanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    String data[];
    int images[];
    Context context;
    String description[];

    public RecyclerAdapter(Context context, String data[], String description[], int images[]) {
        this.context = context;
        this.data = data;
        this.images = images;
        this.description = description;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleText.setText(data[position]);
        holder.myImageView.setImageResource(images[position]);
        holder.myImageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        System.out.println("^^ " + images.length);
        return images.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleText, descriptionText;
        ImageView myImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("hi");
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            myImageView = itemView.findViewById(R.id.myImageView);

        }
    }
}