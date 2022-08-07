package com.example.sendit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {
    ListView phonebook;
    ArrayList<Word> words=new ArrayList<>();
//to store the phonebook

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        phonebook=findViewById(R.id.rootview);
        //initialize memory to arraylist
        ArrayList<Word> words=new ArrayList<>();

        //give runtime permission for read contact
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                !=PackageManager.PERMISSION_GRANTED)
        {
            //request permission
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        else
        {
            //lower than marshmallow
            getcontact();
        }
    }

    private void getcontact() {
        //pass phonebook to cursor
        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                null,null,null);
        //bring all contacts from cursor
        while (cursor.moveToNext())
        {
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String mobile=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            words.add(new Word(name,mobile));
            WordAdapter adapter=new WordAdapter(this,words);
            ListView rootview=findViewById(R.id.rootview);
            rootview.setAdapter(adapter);


        }
    }

    //output of runtime

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if(grantResults [0]==PackageManager.PERMISSION_GRANTED)
            {
                getcontact();
            }

        }
    }
}

