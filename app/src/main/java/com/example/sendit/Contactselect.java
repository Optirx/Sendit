package com.example.sendit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;
public class Contactselect extends AppCompatActivity {

    private List<Model> mModelList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactselect);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(getListData());
        LinearLayoutManager manager = new LinearLayoutManager(Contactselect.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Model> getListData() {
        mModelList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            mModelList.add(new Model("TextView " + i));
        }
        return mModelList;
    }
}



