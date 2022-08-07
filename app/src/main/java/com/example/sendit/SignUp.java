package com.example.sendit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText InputEmail,InputPassword,InputPassconf;
    private Button btnSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        InputEmail = findViewById(R.id.InputEmail);
        InputPassword = findViewById(R.id.InputPassword);
        InputPassconf = findViewById(R.id.InputPassconf);
        btnSignup=(Button)  findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnSignup:
                PerforAuth();
        }
    }
    private void PerforAuth() {
        String email=InputEmail.getText().toString();
        String password=InputPassword.getText().toString();
        String passconf=InputPassconf.getText().toString();

        if (!email.matches(emailPattern))
        {
            InputEmail.setError("Enter A Correct Email");
            InputEmail.requestFocus();
        }
        else if(password.isEmpty() || password.length()<6)
        {
            InputPassword.setError("Enter Propper Password");
        }
        else if (!password.equals(passconf))
        {
            InputPassconf.setError("Password Doesn't Match");
        }
        else
        {
            progressDialog.setMessage("Please Wait While Registration ..");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        User user = new User(email);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                            user.sendEmailVerification();
                                            Toast.makeText(SignUp.this, "Registration Successful , Check your email for verification", Toast.LENGTH_LONG).show();                                            progressDialog.dismiss();
                                            startActivity(new Intent(SignUp.this,LogIn.class));
                                        }}});}
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }}});}}}