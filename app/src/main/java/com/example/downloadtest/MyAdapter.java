package com.example.downloadtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<Profile> profiles;
    Context context;


    public MyAdapter(ArrayList<Profile> profiles)
    {
        this.profiles = profiles;
    }
/*
    public MyAdapter(Context c, ArrayList<Profile> p)
    {
        context = c;
        profiles = p;
    }
 */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);
        return new MyViewHolder(view);
        //return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //myViewHolder.textView_status.setText(""+profiles.get(i).getStatus());
        myViewHolder.textViewStatus.setText(""+profiles.get(i).getStatus());
        myViewHolder.textView.setText("ชื่อ: "+profiles.get(i).getName()+"\nวัน: "+profiles.get(i).getDay()+
                "\nเวลา: "+profiles.get(i).getTime()+"\nวันที่: "+profiles.get(i).getDate());
        Picasso.get().load(profiles.get(i).getPhoto()).into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        //TextView textView_status;
        TextView textView;
        TextView textViewStatus;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textViewStatus = itemView.findViewById(R.id.textView_status);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
