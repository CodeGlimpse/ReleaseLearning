package com.example.releaselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建Intent对象
        Intent intent = new Intent(this , login_in.class) ;
        startActivityForResult(intent , 1);
    }
}