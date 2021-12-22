package com.example.apppickerimageintentexplicit18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public class MainActivityPickPucture extends AppCompatActivity {
    TableLayout mTbLayout;
    int mColumn , mRow , mCount;
    int mWidthScreen, mHeightScreen;
    int mResource;
    String[] mArrDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pick_pucture);
        mTbLayout = findViewById(R.id.tableLayout);
        initView();

    }
    // get data
    private void initView(){
        Intent intent = getIntent();
        if(intent!= null){
//            String text = intent.getStringExtra("text");
          mArrDrawable = intent.getStringArrayExtra("arr_drawable");
            Collections.shuffle(Arrays.asList(mArrDrawable));
        }
        // Get dimension screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mHeightScreen = displayMetrics.heightPixels;
        mWidthScreen = displayMetrics.widthPixels;

        mRow = (int) Math.ceil( Double.parseDouble ((mArrDrawable.length / 3f)+""));
       // mColumn = mArrDrawable.length()%3;
        mColumn =3;
        mCount = 0;
        // 6 dong 3 cot
        for(int i =0; i<mRow;i++){
            //Creat row
            TableRow tableRow = new TableRow(this);
            for(int y = 0; y<mColumn;y++) {
                if (mCount < mArrDrawable.length){
                    //Tính vị trí của hình chữ nhật khi biết cấu trúc
//                    mIndex =mColumn*(i) +y;
                    mResource = getResources().getIdentifier(mArrDrawable[mCount],"drawable",getPackageName());
                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(mWidthScreen / 3, mWidthScreen / 3);
                    ImageView imageView = new ImageView(this);
//                    Resources r = getResources();
//                    int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,120,r.getDisplayMetrics());
//                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px,px);
//                    imageView.setLayoutParams(layoutParams);
                    imageView.setImageResource(mResource);
                    imageView.setTag(mResource);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intentData = new Intent();
                            intentData.putExtra("resourceData",(int)imageView.getTag());
                            setResult(RESULT_OK,intentData);
                            finish();
                        }
                    });
                    tableRow.addView(imageView);
                    tableRow.setGravity(Gravity.CENTER);
                    mCount++;
                }
            }
            mTbLayout.addView(tableRow);
        }
    }
}