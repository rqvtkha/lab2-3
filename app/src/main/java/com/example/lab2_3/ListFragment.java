package com.example.lab2_3;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ListFragment  extends Fragment {
    private ItemAdapter itemAdapter;
    private ListView itemList;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        itemList = (ListView) view.findViewById(R.id.itemList);
        context=view.getContext();
        return view;
    }
    public  void onStart()
    {
        super.onStart();
        itemAdapter = new ItemAdapter(context, R.layout.list_item, MainActivity.items);
        itemList.setAdapter(itemAdapter);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(MainActivity.allEntities.get(position)!=null)
                {
                    MainActivity.detailFragment = new DetailFragment(position);
                    getFragmentManager().beginTransaction().replace(R.id.detailFragment,MainActivity.detailFragment).commit();
                }
            }
        });
    }

}
