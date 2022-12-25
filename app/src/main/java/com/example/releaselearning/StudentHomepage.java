package com.example.releaselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.releaselearning.chat.Chat;
import com.example.releaselearning.exam.Exam;
import com.example.releaselearning.homeWork.HomeWork;

public class StudentHomepage extends AppCompatActivity {
    private Button btn_homeWork;
    private Button btn_exam;
    private Button btn_chat;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        intent = getIntent();
        getViews();
        setListener();
    }
    private void setListener() {
        View.OnClickListener MyListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"you have clicked Button2",Toast.LENGTH_SHORT).show();

                switch (v.getId()){
                    case R.id.btn_homeWork:
                        intent.setClass(StudentHomepage.this,HomeWork.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_exam:
                        intent.setClass(StudentHomepage.this,Exam.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_chat:
                        intent.setClass(StudentHomepage.this,Chat.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        btn_homeWork.setOnClickListener(MyListener);
        btn_exam.setOnClickListener(MyListener);
        btn_chat.setOnClickListener(MyListener);
    }

    private void getViews() {
        btn_homeWork = findViewById(R.id.btn_homeWork);
        btn_exam = findViewById(R.id.btn_exam);
        btn_chat = findViewById(R.id.btn_chat);
    }
}