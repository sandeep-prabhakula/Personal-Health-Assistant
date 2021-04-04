package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class PhoneAuthentication extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText editTextPhone,editTextpersonName;
    String codeSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_authentication);
        TextView emailIntent = findViewById(R.id.emailIntent);
        emailIntent.setOnClickListener(v -> {
            startActivity(new Intent(PhoneAuthentication.this,Login.class));
            finish();
        });
        auth = FirebaseAuth.getInstance();
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextpersonName = findViewById(R.id.editTextTextPersonName2);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> sendVerificationCode());
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> verifySignCode());
    }

    private void verifySignCode() {
        String code = editTextpersonName.getText().toString().trim();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(PhoneAuthentication.this,MainActivity.class));
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(PhoneAuthentication.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    

    private void sendVerificationCode() {
        String mob = editTextPhone.getText().toString().trim();
        if(mob.isEmpty()){
            editTextPhone.setError("Mobile number is Mandatory");
            editTextPhone.requestFocus();
            return;
        }
        if(mob.length()<10){
            editTextPhone.setError("Enter valid mobile number");
            editTextPhone.requestFocus();
            return;
        }
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(mob)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };
}