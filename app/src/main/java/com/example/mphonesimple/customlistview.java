package com.example.mphonesimple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class customlistview extends BaseAdapter {

    private ArrayList<String> listUsername;
    private ArrayList<String> listDateTime;
    private AppCompatActivity activity;
    private int x=0;

    public customlistview(ArrayList<String> listDateTime, AppCompatActivity activity){
        //this.listUsername = listUsername;
        this.listDateTime = listDateTime;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listUsername.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsername.get(x);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.rowlistview, parent, false);
        //((TextView)convertView.findViewById(R.id.usernamelv)).setText(listUsername.get(position));
        ((TextView)convertView.findViewById(R.id.datetimelv)).setText(listDateTime.get(position));
        return convertView;
    }
}
