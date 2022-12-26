package com.example.releaselearning.homeWork;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.releaselearning.Constant;
import com.example.releaselearning.Entity.Homework;
import com.example.releaselearning.Entity.HwDetail;
import com.example.releaselearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class SubmitHomework extends AppCompatActivity {
    private Button submit;
    private TextView tvTitle;
    private EditText etDetail;
    private HwDetail hwDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_submit);

        gitView();

        //获取信息；
        Intent intent = getIntent();
        hwDetail = new HwDetail();
        hwDetail.setHomeworkId(intent.getStringExtra("homeworkId"));
        hwDetail.setStudentId(intent.getStringExtra("studentId"));
        tvTitle.setText(intent.getStringExtra("homeworkId"));
        submit.setOnClickListener(view -> {
            String str = etDetail.getText().toString();
            System.out.println(str);
            if(!str.isEmpty()){
                hwDetail.setHomeworkFile(createFile(str));
                System.out.println(hwDetail.toString());
                try {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.put("homeworkId", "第一个作业");
                    params.put("studentId", "1");
                    String strPath = "/data/data/com.example.releaselearning/files/homework.txt";
                    params.put("uploadFiles", new File(strPath),"txt");
                    client.post(this, Constant.URLSubmitHomework,params,new AsyncHttpResponseHandler(){

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            etDetail.setText("");
        });

    }

    private File createFile(String str) {
        File file1=new File("/data/data/com.example.releaselearning/files" ,"homework.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(str.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file1;
    }

    private void gitView() {
        submit = findViewById(R.id.homweork_submit_button);
        tvTitle = findViewById(R.id.homework_submit_title);
        etDetail = findViewById(R.id.homework_submit_detail);

    }
}