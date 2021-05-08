package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText editTextTextPersonName,editTextTextPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        Button button = findViewById(R.id.button);
        editTextTextPassword.requestFocus();
        editTextTextPersonName.requestFocus();
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView16 = findViewById(R.id.textView16);
        auth = FirebaseAuth.getInstance();
        button.setFocusableInTouchMode(true);
        button.requestFocus();
        button.setOnClickListener(v -> {
            String email = editTextTextPersonName.getText().toString();
            String pass = editTextTextPassword.getText().toString();
            if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
                Toast.makeText(Login.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
            }
            else{
                login(email,pass);
            }
        });
        textView16.setOnClickListener(v -> startActivity(new Intent(Login.this,ForgotPassword.class)));
        textView7.setOnClickListener(v -> {
            startActivity(new Intent(Login.this,SignUp.class));
            finish();
        });
    }

    private void login(String mail,String pass){
        auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(Login.this, task -> {
            if(task.isSuccessful()){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                if(user.isEmailVerified()){
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    user.sendEmailVerification();
                    Toast.makeText(Login.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i =  new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}