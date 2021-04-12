package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private final List<ModelClass> activities;

    public MainAdapter(List<ModelClass> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycle,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass activity = activities.get(position);
        holder.imageView.setImageResource(activity.getImage());
        holder.textView.setText(activity.getName());
        holder.linearLayout.setOnClickListener(v -> {
            if(position==0){
                v.getContext().startActivity(new Intent(v.getContext(),MainActivity2.class));
            }
            if(position==1){
                v.getContext().startActivity(new Intent(v.getContext(),Diagnosis.class));
            }
            if(position==2){
                v.getContext().startActivity(new Intent(v.getContext(),MainActivity4.class));
            }
            if(position==3){
                v.getContext().startActivity(new Intent(v.getContext(),BMICalculation.class));
            }
            if(position==4){
//                v.getContext().startActivity(new Intent(v.getContext(),MapsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        private final ImageView imageView;
        private final LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }
}
