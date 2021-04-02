package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {
    private List<DietClass> diets;
    private Context context;

    public DietAdapter(List<DietClass> diets, Context context) {
        this.diets = diets;
        this.context = context;
    }

    @NonNull
    @Override
    public DietAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_diet_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DietAdapter.ViewHolder holder, int position) {
        DietClass diet = diets.get(position);
        holder.textView11.setText(diet.getDiet());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "https://www.healthline.com/health/"+holder.textView11.getText().toString().toLowerCase()+"?ref=global";
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return diets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView11;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView11 = (TextView)itemView.findViewById(R.id.textView11);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
