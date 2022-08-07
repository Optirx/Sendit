package com.example.sendit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Writting extends AppCompatActivity {
    private Button next ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writting);
        next = (Button) findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTime();
            }
        });}
    public void openTime(){
        Intent intent = new Intent (Writting.this ,TimeSelect.class);
        startActivity(intent);
    }
}
