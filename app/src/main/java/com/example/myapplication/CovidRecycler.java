package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CovidRecycler extends RecyclerView.Adapter<CovidRecycler.ViewHolder> {
    private final List<CovidDataModel> data;
    public ItemClickListener itemClickListener;
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView country;
        private final TextView active;
        private final TextView recovered;
        private final TextView deaths;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            active  = itemView.findViewById(R.id.active);
            recovered = itemView.findViewById(R.id.recovered);
            deaths = itemView.findViewById(R.id.deceased);
            country = itemView.findViewById(R.id.country);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
    CovidDataModel get(int id){
       return data.get(id);
    }
    void setOnclickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public interface ItemClickListener{
        void onItemClick(View v,int position);
    }
}
