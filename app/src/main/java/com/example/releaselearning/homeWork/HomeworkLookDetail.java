package com.example.releaselearning.homeWork;

import static com.example.releaselearning.R.id.homework_look_detail_score;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.releaselearning.Constant;
import com.example.releaselearning.R;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeworkLookDetail extends AppCompatActivity {
    private int sorce=0;
    private String homeworkFile = "";
    private TextView tvTitle ,tvScore;

    //通信
    private final Handler myHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvScore.setText("作业得分："+sorce);
                    WebView wv = findViewById(R.id.wv);
                    wv.loadUrl(homeworkFile);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_look_detail);
        getView();
        String StudentId= getIntent().getStringExtra("studentId");
        String HomeworkId= getIntent().getStringExtra("homeworkId");
        tvTitle.setText(HomeworkId);

        String stringURL = Constant.URLHomeworkFindByStudentIDAndHomeworkId + "/" + StudentId + "/"+HomeworkId;
        new Thread(() -> getMes(stringURL)).start();
    }

    private void getView() {
        tvTitle = findViewById(R.id.homework_look_detail_title);
        tvScore = findViewById(R.id.homework_look_detail_score);
    }


    private void getMes(String URL) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            // 请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("okhttp", "请求失败");
                e.printStackTrace();
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //处理数据
                String responseStr = response.body().string();
                System.out.println(responseStr);
                JSONObject jsonObj = JSON.parseObject(responseStr);
                homeworkFile = jsonObj.get("homeworkFile").toString();
                sorce = (int) jsonObj.get("homeworkScore");
                Message message = new Message();
                message.what = 1;

                myHandler.sendMessage(message);
            }
        });
    }
}