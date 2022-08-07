package com.example.sendit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

public WordAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0, objects);
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview=convertView;
        if (listview==null){
        listview= LayoutInflater.from(getContext()).inflate(R.layout.items,parent,false);
        }
        Word currentword=getItem(position);
        TextView name=listview.findViewById(R.id.Text1);
        name.setText(currentword.getName());

        TextView number=listview.findViewById(R.id.Text2);
         number.setText(currentword.getNumber());
        return listview;
        }
        }





