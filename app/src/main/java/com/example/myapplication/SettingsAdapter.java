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

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private final List<SettingModel>settings;

    public SettingsAdapter(List<SettingModel> settings) {
        this.settings = settings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingModel setting = settings.get(position);
        holder.imageView3.setImageResource(setting.getImg());
        holder.textView9.setText(setting.getName());
        holder.linearLayout.setOnClickListener(v -> {
            if(position==0){
                v.getContext().startActivity(new Intent(v.getContext(),ForgotPassword.class));
            }
            if(position==1){
                String link = "https://sandeep-prabhakula.github.io/project-apk/app-debug.apk";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, link);
                v.getContext().startActivity(Intent.createChooser(intent, "Share Link"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView9;
        private final ImageView imageView3;
        private final LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView9 = itemView.findViewById(R.id.textView9);
            imageView3 = itemView.findViewById(R.id.imageView3);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
