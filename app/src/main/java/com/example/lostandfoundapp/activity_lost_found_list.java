package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lostandfoundapp.data.databaseHelper;
import com.example.lostandfoundapp.model.LostFoundMod;

import java.util.ArrayList;
import java.util.List;

public class activity_lost_found_list extends AppCompatActivity {

    ListView lView;
    databaseHelper db;
    ArrayList<String> lostFoundArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_list);

        lView = findViewById(R.id.list);
        lostFoundArrayList = new ArrayList<>();
        db = new databaseHelper(activity_lost_found_list.this);

        List<LostFoundMod> listView = db.fetchAllItems();
        for (LostFoundMod lostFoundMod : listView)
        {
            lostFoundArrayList.add(lostFoundMod.getType() + " " + lostFoundMod.getName());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lostFoundArrayList);
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent storedata = new Intent(activity_lost_found_list.this, LostFoundData.class);
                storedata.putExtra("type", listView.get(position).getType());
                storedata.putExtra("name", listView.get(position).getName());
                storedata.putExtra("phone", listView.get(position).getPhone());
                storedata.putExtra("description", listView.get(position).getDescription());
                storedata.putExtra("date", listView.get(position).getDate());
                storedata.putExtra("location", listView.get(position).getLocation());
                startActivity(storedata);

            }
        });
    }
}