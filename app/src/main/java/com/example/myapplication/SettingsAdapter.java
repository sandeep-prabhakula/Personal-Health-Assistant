package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                String link = "https://github.com/sandeep-prabhakula/sandeep-prabhakula.github.io/blob/main/project-apk/app-debug.apk?raw=true";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, link);
                v.getContext().startActivity(Intent.createChooser(intent, "Share Link"));
            }
            if(position==3){
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Delete Account")
                        .setMessage("Your data will be lost!")
                        .setPositiveButton("OK", (dialog, which) -> {
                            AuthCredential ac = EmailAuthProvider.getCredential(user.getEmail(),"rohitman45");
                            user.reauthenticate(ac).addOnCompleteListener(task -> user.delete().addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    v.getContext().startActivity(new Intent(v.getContext(),SignUp.class));
                                    Toast.makeText(v.getContext(), "Account Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }));
                        })
                        .setNegativeButton("CANCEL",null)
                        .show();
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
