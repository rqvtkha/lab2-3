package com.example.lab2_3;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;


public class DetailFragment  extends Fragment {
    private String id;
    private String name;
    private String userName;
    private String email;
    public DetailFragment(){ }
    public DetailFragment(int id)
    {
        EntityData entity = MainActivity.allEntities.get(id);
        this.id = String.valueOf(entity.id);
        this.name=entity.name;
        this.userName=entity.userName;
        this.email = entity.email;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView detailId = (TextView) view.findViewById(R.id.id);
        TextView detailName = (TextView) view.findViewById(R.id.name);
        TextView detailUserName = (TextView) view.findViewById(R.id.userName);
        TextView detailEmail = (TextView) view.findViewById(R.id.email);
        detailId.setText(this.id);
        detailName.setText(this.name);
        detailUserName.setText(this.userName);
        detailEmail.setText(this.email);
        return view;
    }

}