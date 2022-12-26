package com.example.releaselearning.homeWork;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.example.releaselearning.Constant;
import com.example.releaselearning.Entity.Class;
import com.example.releaselearning.Entity.Homework;
import com.example.releaselearning.Entity.Teacher;
import com.example.releaselearning.R;
import com.example.releaselearning.exam.Exam;
import com.example.releaselearning.homeWork.fragment.HomeWorkAdapter;
import com.example.releaselearning.login_in;
import com.example.releaselearning.studentHomepage.StudentHomepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NoLook extends Fragment {

    private List<Homework> list;
    private String stuId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取布局文件
        View view = inflater.inflate(R.layout.fragment_home_work, null);
        //获取布局文件中的控件对象
        ListView listView = view.findViewById(R.id.lv_homework);
        //给控件对象设置必要的属性(给listview设置item)
//        bundle = getArguments();
//        stuId = bundle.getString("id");
//        stuId = getActivity().getIntent().getStringExtra("id");
        stuId = Constant.StuId;
        System.out.println(stuId);
        //获取学生作业数据
        list = getData(stuId);
        Log.e("list",list.toString());

        HomeWorkAdapter adapter = new HomeWorkAdapter(getContext(),R.layout.fragment_home_work_item,list);
        listView.setAdapter(adapter);

        // tvMsg.setText("设置页面");
        //给某些控件对象添加事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(R.id.tv_homeworkId);
                String homeworkId = tv.getText().toString();
//                Intent intent = getActivity().getIntent();
                Intent intent = new Intent(getContext(), SubmitHomework.class);
                intent.putExtra("homeworkId",homeworkId);
                intent.putExtra("studentId" , stuId);

                //跳转到作业详情页面
//                intent.setClass(getActivity(), Exam.class);
                startActivityForResult(intent, 1);
//                Intent intent = new Intent(login_in.this, StudentHomepage.class);
//
//                intent.putExtra("id", idStr);
//                Constant.StuId = idStr;
//                startActivityForResult(intent, 1);
            }
        });

        //返回布局文件对象
        return view;

    }


    private List<Homework> getData(String stuId) {
        List<Homework> works = new ArrayList<>();
        String URL = Constant.URLHomeWork+"/getHomeWorkAllByStuId/"+stuId;
        OkHttpClient okHttpClient = new OkHttpClient();
        System.out.println(URL);

        Request request2 = new Request.Builder()
                .url(URL)
                .build();
        Call call = okHttpClient.newCall(request2);



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
                Log.e("test1","test1");
                //处理数据
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    Log.e("responseStr",responseStr);
                    JSONArray jsonArray = JSON.parseArray(responseStr);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String homeworkId = jsonObject.getString("homeworkId");
                            String homeworkContent = jsonObject.getString("homeworkContent");
                            JSONObject clatemp = jsonObject.getJSONObject("classId");
                            String classId = clatemp.getString("classId");
                            JSONObject teacherIdtemp = clatemp.getJSONObject("teacherId");
                            String teacherId = teacherIdtemp.getString("teacherId");
                            String tname = teacherIdtemp.getString("name");
                            String tpassword = teacherIdtemp.getString("password");
                            Teacher teacher = new Teacher(teacherId,tname,tpassword);
                            Class cla = new Class(classId,teacher);
                            Homework homework = new Homework(homeworkId,homeworkContent,cla);
                            works.add(homework);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.e("works1",works.toString());
            }
        });

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("works2",works.toString());
        return works;
    }

}
