package com.example.lab2_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item>  {
    private LayoutInflater inflater;
    private int layout;
    private List<Item> items;

    public ItemAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(this.layout, parent, false);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView userNameView = (TextView) view.findViewById(R.id.username);
        Item item = items.get(position);
        nameView.setText(item.getName());
        userNameView.setText(item.getUserName());
        return view;
    }
}
