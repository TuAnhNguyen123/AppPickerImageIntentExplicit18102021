package com.example.apppickerimageintentexplicit18102021;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
private String[] mArrDrawable;
private ImageView mImgPick,mImgRandom;
    private int mResourceRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        event();

    }

    private void event() {
        mImgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivityPickPucture.class);
                //intent.putExtra("text","Hello");
                intent.putExtra("arr_drawable",mArrDrawable);
//                Animal animal = new Animal("cat",2);
//                intent.putExtra("object",animal);
                someActivityResultLauncher.launch(intent);
            }
        });
    }
    private ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        int resourceData = data.getIntExtra("resourceData",-1);
                        mImgPick.setImageResource(resourceData);
                        if(resourceData== mResourceRandom){
                            Toast.makeText(MainActivity.this, "Correct!!!", Toast.LENGTH_SHORT).show();
                            mResourceRandom = randomImage(mArrDrawable);
                            mImgRandom.setImageResource(mResourceRandom);
                            mImgPick.setImageResource(R.drawable.nophoto);
                        }else{
                            Toast.makeText(MainActivity.this, "InCorrect???", Toast.LENGTH_SHORT).show();
                        }
                        }else{
                        mResourceRandom = randomImage(mArrDrawable);
                        mImgRandom.setImageResource(mResourceRandom);
                        Toast.makeText(MainActivity.this, "Not yet Choose picture!!??", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private void initView() {
        mImgPick = findViewById(R.id.imgPick);
        mImgRandom = findViewById(R.id.imgRandom);
        //Array String name
        mArrDrawable = getResources().getStringArray(R.array.ArrayDrawable);

        //Xử lí việc gán hình ảnh cho hình random
        mResourceRandom = randomImage(mArrDrawable);
        mImgRandom.setImageResource(mResourceRandom);
    }
    private int randomImage(String[] arrDrawable){
        int index = new Random().nextInt(arrDrawable.length);
        String name = arrDrawable[index];
        int resourceImage = getResources().getIdentifier(name,"drawable",getPackageName());
        return resourceImage;
    }

    //Tạo menu Refresh
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuRefresh:
                mResourceRandom = randomImage(mArrDrawable);
                mImgRandom.setImageResource(mResourceRandom);
                break;
        }
        return true;
    }
}