package com.example.releaselearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Register extends AppCompatActivity {
    private EditText id , password , password2 , name , class_id;
    private Button register;
    //通信
    private Handler myHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constant.SUCCEED_CODE:
                    //创建Intent对象
                    Intent intent = new Intent(Register.this , login_in.class) ;
                    startActivityForResult(intent , 1);
                    break;
                case Constant.UNSUCCEED_CODE:
                    id.setText("");
                    password.setText("");
                    password2.setText("");
                    name.setText("");
                    class_id.setText("");
                    Toast.makeText(Register.this,"学生已存在",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //获取控件
        getView();
        //绑定点击事件
        setClickListenerOnView();
    }

    private void getView() {
        id = findViewById(R.id.student_register_id);
        password = findViewById(R.id.student_register_password);
        password2 = findViewById(R.id.student_register_password2);
        name = findViewById(R.id.student_register_name);
        class_id = findViewById(R.id.student_register_class);
        register = findViewById(R.id.register);
    }
    private void setClickListenerOnView() {
        register.setOnClickListener(view -> {
            String idStr = String.valueOf(id.getText());
            String passwordStr = String.valueOf(password.getText());
            String password2Str = String.valueOf(password2.getText());
            String nameStr = String.valueOf(name.getText());
            String classStr = String.valueOf(class_id.getText());

            if(!password2Str.equals(passwordStr)){
                id.setText("");
                password.setText("");
                password2.setText("");
                name.setText("");
                class_id.setText("");
                Toast.makeText(Register.this,"两次密码不一致",Toast.LENGTH_LONG).show();
            }
            else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        add(idStr ,passwordStr ,nameStr ,classStr);
                    }
                }).start();

            }

        });
    }

    private void add(String idStr, String passwordStr, String nameStr, String classStr) {
        String URL = Constant.URLAdd+"/"+ idStr+ "/"+passwordStr+"/"+nameStr +"/"+classStr;
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
                if(responseStr.equals("1")){
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
}