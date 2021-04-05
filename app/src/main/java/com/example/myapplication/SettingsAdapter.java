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

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private List<SettingModel>settings;
    private Context context;

    public SettingsAdapter(List<SettingModel> settings, Context context) {
        this.settings = settings;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_layout,parent,false);
        return new SettingsAdapter.ViewHolder(v);
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
                Intent i = new Intent(Intent.ACTION_SEND);
                String s = "https://sandeep-prabhakula.github.io/project-apk/app-debug.apk";
                i.putExtra(Intent.EXTRA_SUBJECT,s);
                i.setType("message/rfc822");
                v.getContext().startActivity(Intent.createChooser(i,""));
            }
        });

    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView9;
        private ImageView imageView3;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView9 = (TextView)itemView.findViewById(R.id.textView9);
            imageView3 = (ImageView)itemView.findViewById(R.id.imageView3);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
