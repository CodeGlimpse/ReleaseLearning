package com.example.releaselearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class login_in extends AppCompatActivity {
    private EditText id , password;
    private Button register , login;
    private String idStr,passwordStr;
    //通信
    private Handler myHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constant.SUCCEED_CODE:
                    //创建Intent对象
                    Intent intent = new Intent(login_in.this , StudentHomepage.class) ;

                    intent.putExtra("id" , idStr);
                    startActivityForResult(intent , 1);
                    break;
                case Constant.UNSUCCEED_CODE:
                    id.setText("");
                    password.setText("");
                    Toast.makeText(login_in.this,"学号或密码错误",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        //获取控件
        getView();
        //绑定点击事件
        setClickListenerOnView();
    }

    private void setClickListenerOnView() {
        register.setOnClickListener(view -> {
            //跳到注册界面
            //创建Intent对象
            Intent intent = new Intent(this , Register.class) ;
            startActivityForResult(intent , 1);
        });
        login.setOnClickListener(view -> {
            //查询后端
            idStr = String.valueOf(id.getText());
            passwordStr = String.valueOf(password.getText());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkIdAndPasswordIsTrue(idStr , passwordStr);
                }
            }).start();

        });
    }

    private void checkIdAndPasswordIsTrue(String idStr, String passwordStr) {
        String URL = Constant.URLQueryIdAndPassword+"/"+idStr+"/"+passwordStr;
        System.out.println(URL);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            // 请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("okhttp" ,"请求失败");
                e.printStackTrace();
            }
            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //处理数据
                String responseStr = response.body().string();
                System.out.println(responseStr);
                if(responseStr.equals("true")){
                    Message message = new Message();
                    message.what = Constant.SUCCEED_CODE;
                    myHandler.sendMessage(message);
                }else{
                    Message message = new Message();
                    message.what = Constant.UNSUCCEED_CODE;
                    myHandler.sendMessage(message);
                }
            }
        });
    }

    private void getView() {
        id = findViewById(R.id.student_login_in_id);
        password = findViewById(R.id.student_login_in_password);
        register = findViewById(R.id.student_login_in_register);
        login = findViewById(R.id.student_login_in);
    }
}