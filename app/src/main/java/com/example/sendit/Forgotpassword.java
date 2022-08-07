package com.example.sendit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpassword extends AppCompatActivity {

    private EditText forgot;
    private Button btnforgot;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        forgot =(EditText) findViewById(R.id.forgot);
        btnforgot =(Button) findViewById(R.id.btnforgot);
        auth = FirebaseAuth.getInstance();
        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });}
    private void resetpassword() {
        String email = forgot.getText().toString().trim();

        if(email.isEmpty())
        {
            forgot.setError("Email is Required");
            forgot.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            forgot.setError("Please enter a valid email ");
            forgot.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Forgotpassword.this, "Check your email to reset password", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Forgotpassword.this, "Something went wrong , Try again", Toast.LENGTH_SHORT).show();
                }}});}}