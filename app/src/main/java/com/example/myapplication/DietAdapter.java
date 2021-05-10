package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {
    private final List<DietClass> diets;

    public DietAdapter(List<DietClass> diets) {
        this.diets = diets;
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
        holder.linearLayout.setOnClickListener(v -> {
            String s = "https://www.healthline.com/health/"+holder.textView11.getText().toString().toLowerCase()+"?ref=global";
//            v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s)));
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(v.getContext(),Uri.parse(s));
        });
    }

    @Override
    public int getItemCount() {
        return diets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private final TextView textView11;
        private final LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView11 = itemView.findViewById(R.id.textView11);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String s = "https://www.healthline.com/health/"+textView11.getText().toString().toLowerCase()+"?ref=global";
            v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s)));
        }
    }
}
