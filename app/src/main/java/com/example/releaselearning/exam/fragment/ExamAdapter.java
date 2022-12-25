package com.example.releaselearning.exam.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.releaselearning.R;
import com.example.releaselearning.Entity.Exam;

import java.util.List;

public class ExamAdapter extends BaseAdapter {
    private final Context context;
    private final int layoutId;
    private final List<Exam> list;

    public ExamAdapter(Context context, int layoutId, List<Exam> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            //加载item的布局文件，生成一个View对象，再给converView赋值
            convertView = LayoutInflater.from(context).inflate(layoutId,null);
            //获取每一个item中的控件对象
            viewHolder = new ViewHolder();
            viewHolder.tv_examId = convertView.findViewById(R.id.tv_examId);
            viewHolder.tv_endTime_exam = convertView.findViewById(R.id.tv_endTime_exam);
            //将ViewHolder对象同converView关联起来
            convertView.setTag(viewHolder);
        }else {//重复利用convertView对象
            //获取到跟当前的convertView绑定在一起的ViewHolder对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //给控件对象绑定要显示的数据
        viewHolder.tv_examId.setText(list.get(position).getExamId());
        viewHolder.tv_endTime_exam.setText(list.get(position).getExamContent());

        return convertView;
    }

    private static class ViewHolder{
        //每一个item中的控件
        TextView tv_examId,tv_endTime_exam;
    }

}
