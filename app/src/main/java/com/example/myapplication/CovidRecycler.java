package com.example.myapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CovidRecycler extends RecyclerView.Adapter<CovidRecycler.ViewHolder> {
    private final List<CovidDataModel> data;
    public CovidRecycler(List<CovidDataModel> data) {
        this.data = data;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.covid_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CovidRecycler.ViewHolder holder, int position) {
        CovidDataModel model = data.get(position);
        holder.active.setText(model.getCount());
        holder.recovered.setText(model.getRecover());
        holder.deaths.setText(model.getDeaths());
        holder.country.setText(model.getState());
        Glide.with(holder.flag.getContext()).load(model.getFlag()).into(holder.flag);
        holder.covidTable.setOnClickListener(v -> {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent build = builder.build();
            build.launchUrl(v.getContext(), Uri.parse("https://corona.dnsforfamily.com/graph.png?c="+model.getSymbol()));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView country;
        private final TextView active;
        private final TextView recovered;
        private final TextView deaths;
        private final ConstraintLayout covidTable;
        private final ImageView flag;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            active  = itemView.findViewById(R.id.active);
            recovered = itemView.findViewById(R.id.recovered);
            deaths = itemView.findViewById(R.id.deceased);
            country = itemView.findViewById(R.id.country);
            covidTable = itemView.findViewById(R.id.covidTable);
            flag = itemView.findViewById(R.id.flag);
        }
    }
}
