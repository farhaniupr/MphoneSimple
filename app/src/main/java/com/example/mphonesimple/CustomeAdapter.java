package com.example.mphonesimple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomeAdapter extends ArrayAdapter {
    private ArrayList dataSet;
    Context mContext;

    public CustomeAdapter(ArrayList data, Context context) {
        super(context,  R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;

    }

    @Nullable
    @Override
    public String getItem(int position) {
        return String.valueOf(dataSet.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtName.setText(getItem(position));
        // Return the completed view to render on screen
        return convertView;
    }
}
