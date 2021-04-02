package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FitnessAdapter extends RecyclerView.Adapter<FitnessAdapter.ViewHolder> {
    private List<FitnessModel> exercises;
    private Context context;

    public FitnessAdapter(List<FitnessModel> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fitness_recycler,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FitnessModel exercise = exercises.get(position);
        holder.exIcon.setImageResource(exercise.getImg());
        holder.exName.setText(exercise.getName());
        holder.linearLayout.setOnClickListener(v -> {
            if(position==0){

            }
            if(position==1){

            }
            if(position==2){

            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private  ImageView exIcon;
        private  TextView exName;
        private  LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exIcon = (ImageView) itemView.findViewById(R.id.exIcon);
            exName = (TextView) itemView.findViewById(R.id.exName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
