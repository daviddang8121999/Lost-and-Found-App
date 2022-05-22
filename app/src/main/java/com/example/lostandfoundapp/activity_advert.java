package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfoundapp.data.databaseHelper;
import com.example.lostandfoundapp.model.LostFoundMod;

public class activity_advert extends AppCompatActivity {

    RadioGroup radioGroup;
    Button post;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        RadioButton lostRad = findViewById(R.id.lostRad);
        RadioButton foundRad = findViewById(R.id.foundRad);
        EditText editName = findViewById(R.id.editName);
        EditText editPhone = findViewById(R.id.editPhone);
        EditText editDescription = findViewById(R.id.editDes);
        EditText editDate = findViewById(R.id.editDate);
        EditText editLocation = findViewById(R.id.editLocation);

        databaseHelper db = new databaseHelper(this);

        radioGroup = findViewById(R.id.radioGroup);
        post = findViewById(R.id.PostBtn);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postType = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(postType);

                String type;
                if (lostRad.isChecked())
                    type = "lost";
                else
                    type = "found";

                String name = editName.getText().toString();
                String phone = editPhone.getText().toString();
                String description = editDescription.getText().toString();
                String date = editDate.getText().toString();
                String location = editLocation.getText().toString();

                LostFoundMod lostFoundMod = new LostFoundMod(type, name, phone, description, date, location);

                Long postData = db.insertLostFound(lostFoundMod);

                if (postData > 0)
                    Toast.makeText(activity_advert.this, "Successful Entry Log", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(activity_advert.this, "Unseccessful Entry Log", Toast.LENGTH_SHORT).show();

                Intent saveIntent = new Intent(activity_advert.this, MainActivity.class);
                startActivity(saveIntent);
            }
        });

    }


}