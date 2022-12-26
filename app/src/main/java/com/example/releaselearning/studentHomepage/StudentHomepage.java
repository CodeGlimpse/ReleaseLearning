package com.example.releaselearning.studentHomepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.releaselearning.R;
import com.example.releaselearning.Utils.ScreenUtils;
import com.example.releaselearning.chat.Chat;
import com.example.releaselearning.exam.Exam;
import com.example.releaselearning.homeWork.HomeWork;

public class StudentHomepage extends AppCompatActivity {

    private LinearLayout homeWork;
    private LinearLayout exam;
    private LinearLayout chat;

    private ImageView iv_homework;
    private ImageView iv_exam;
    private ImageView iv_chat;

    private TextView tv_homework;
    private TextView tv_exam;
    private TextView tv_chat;

    private FragmentTransaction transaction;
    private HomeWork homeWorkFragment;
    private Exam examFragment;
    private Chat chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);
        //调用沉浸式
        ScreenUtils.fullScreen(this, true);
        initView();

        setListener();
    }

    private void initView() {
        homeWork = findViewById(R.id.btn_homeWork);
        exam = findViewById(R.id.btn_exam);
        chat = findViewById(R.id.btn_chat);

        iv_homework = findViewById(R.id.iv_homework);
        iv_exam = findViewById(R.id.iv_exam);
        iv_chat = findViewById(R.id.iv_chat);

        tv_homework = findViewById(R.id.tv_homework);
        tv_exam = findViewById(R.id.tv_exam);
        tv_chat = findViewById(R.id.tv_chat);

        //开始一个事物管理，用来切换教师登录或者学生登录
        transaction = getSupportFragmentManager().beginTransaction();
        //默认为作业界面
        if (homeWorkFragment == null) {
            homeWorkFragment = new HomeWork();
            transaction.add(R.id.login_frame, homeWorkFragment);
        } else {
            transaction.show(homeWorkFragment);
        }
        transaction.commit();
        //监听是哪一个页面
        setListener();
    }

    private void setListener() {

        View.OnClickListener MyListener = v -> {

            //Toast.makeText(MainActivity.this,"you have clicked Button2",Toast.LENGTH_SHORT).show();
            switch (v.getId()){
                case R.id.btn_homeWork:
                    homeWork.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout2));
                    iv_homework.setImageResource(R.drawable.homework);
                    exam.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout));
                    iv_exam.setImageResource(R.drawable.exam_click);
                    chat.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout));
                    iv_chat.setImageResource(R.drawable.chat_click);
                    tv_homework.setTextColor(getResources().getColor(R.color.white));
                    tv_exam.setTextColor(getResources().getColor(R.color.blue));
                    tv_chat.setTextColor(getResources().getColor(R.color.blue));

                    transaction = getSupportFragmentManager().beginTransaction();
                    if (homeWorkFragment == null) {
                        homeWorkFragment = new HomeWork();
                    }
                    transaction.replace(R.id.login_frame, homeWorkFragment);
                    transaction.commit();
                    break;

                    /*Intent intent1 = new Intent(StudentHomepage.this, HomeWork.class);
                    startActivity(intent1);
                    break;*/
                case R.id.btn_exam:
                    homeWork.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout));
                    iv_homework.setImageResource(R.drawable.homework_click);
                    exam.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout2));
                    iv_exam.setImageResource(R.drawable.exam);
                    chat.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout));
                    iv_chat.setImageResource(R.drawable.chat_click);
                    tv_homework.setTextColor(getResources().getColor(R.color.blue));
                    tv_exam.setTextColor(getResources().getColor(R.color.white));
                    tv_chat.setTextColor(getResources().getColor(R.color.blue));

                    transaction = getSupportFragmentManager().beginTransaction();
                    if (examFragment == null) {
                        examFragment = new Exam();
                    }
                    transaction.replace(R.id.login_frame, examFragment);
                    transaction.commit();
                    break;

                    /*Intent intent2 = new Intent(StudentHomepage.this, Exam.class);
                    startActivity(intent2);
                    break;*/

                case R.id.btn_chat:

                    homeWork.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout));
                    iv_homework.setImageResource(R.drawable.homework_click);
                    exam.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout));
                    iv_exam.setImageResource(R.drawable.exam_click);
                    chat.setBackground(getResources().getDrawable(R.drawable.shape_linearlayout2));
                    iv_chat.setImageResource(R.drawable.chat);
                    tv_homework.setTextColor(getResources().getColor(R.color.blue));
                    tv_exam.setTextColor(getResources().getColor(R.color.blue));
                    tv_chat.setTextColor(getResources().getColor(R.color.white));

                    transaction = getSupportFragmentManager().beginTransaction();
                    if (chatFragment == null) {
                        chatFragment = new Chat();
                    }
                    transaction.replace(R.id.login_frame, chatFragment);
                    transaction.commit();
                    break;

                   /* Intent intent3 = new Intent(StudentHomepage.this, Chat.class);
                    startActivity(intent3);
                    break;*/
            }
        };

        homeWork.setOnClickListener(MyListener);
        exam.setOnClickListener(MyListener);
        chat.setOnClickListener(MyListener);



    }
}