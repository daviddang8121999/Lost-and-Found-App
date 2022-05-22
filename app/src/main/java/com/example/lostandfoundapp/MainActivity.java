package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newAdvert;
    Button showAdvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newAdvert = findViewById(R.id.NewBtn);
        newAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAdvert();
            }
        });

        showAdvert = findViewById(R.id.ShowBtn);
        showAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllLostFound();
            }
        });
    }

    public void CreateAdvert ()
    {
        Intent intent = new Intent(this, activity_advert.class);
        startActivity(intent);
    }

    public void ShowAllLostFound ()
    {
        Intent intent = new Intent(this, activity_lost_found_list.class);
        startActivity(intent);
    }
}