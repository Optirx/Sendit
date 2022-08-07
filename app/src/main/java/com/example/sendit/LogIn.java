package com.example.sendit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextPhone2,editTextTextPassword;
    private Button log_in_button;
    private TextView forgotpassword ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        log_in_button=(Button) findViewById(R.id.log_in_button);
        log_in_button.setOnClickListener(this);
        editTextPhone2 = (EditText) findViewById(R.id.editTextPhone2);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.log_in_button:
                userLogin();
                break;
            case R.id.forgotpassword:
                startActivity(new Intent( LogIn.this, Forgotpassword.class));
                break;
        }}
    private void userLogin() {
        String email = editTextPhone2.getText().toString().trim();
        String password =editTextTextPassword.getText().toString().trim();
        if(email.isEmpty())
        {
            editTextPhone2.setError("Email is required");
            editTextPhone2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextPhone2.setError("Please Enter A valid email");
            editTextPhone2.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editTextTextPassword.setError("Password is required!");
            editTextTextPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editTextTextPassword.setError("Enter a valid password of 6 characters !");
            return;
        }
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {
                        Toast.makeText(LogIn.this, "Welcome", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogIn.this,Message.class));
                    }else
                    {
                        Toast.makeText(LogIn.this, "Check your email to verify account", Toast.LENGTH_LONG).show();
                    }
                }else
                {
                    Toast.makeText(LogIn.this, "Failed to Login ! Please check your credentials ", Toast.LENGTH_LONG).show();
                }}});}}