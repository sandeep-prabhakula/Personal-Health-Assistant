package com.example.myapplication;

import android.content.Context;
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
    private List<ModelClass> activities;
    private Context context;

    public MainAdapter(List<ModelClass> activities, Context context) {
        this.activities = activities;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycle,parent,false);
        return new MainAdapter.ViewHolder(v);
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
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            textView = (TextView)itemView.findViewById(R.id.textView);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linear);
        }
    }
}
