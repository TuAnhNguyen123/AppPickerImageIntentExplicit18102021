package com.example.apppickerimageintentexplicit18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivityPickPucture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pick_pucture);
       // get data
        Intent intent = getIntent();
        if(intent!= null){
//            String text = intent.getStringExtra("text");
           // String[] arrDrawable = intent.getStringArrayExtra("arr_drawable");
            Animal animal = intent.getParcelableExtra("object");
            Toast.makeText(this, animal.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}