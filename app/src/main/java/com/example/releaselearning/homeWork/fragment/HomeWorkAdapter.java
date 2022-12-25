package com.example.releaselearning.homeWork.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.releaselearning.Entity.Homework;
import com.example.releaselearning.R;

import java.util.List;

public class HomeWorkAdapter extends BaseAdapter {
    private final Context context;
    private final int layoutId;
    private final List<Homework> list;

    public HomeWorkAdapter(Context context, int layoutId, List<Homework> list) {
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
            viewHolder.tv_homeworkId = convertView.findViewById(R.id.tv_homeworkId);
            viewHolder.tv_endTime = convertView.findViewById(R.id.tv_endTime);
            //将ViewHolder对象同converView关联起来
            convertView.setTag(viewHolder);
        }else {//重复利用convertView对象
            //获取到跟当前的convertView绑定在一起的ViewHolder对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //给控件对象绑定要显示的数据
        viewHolder.tv_homeworkId.setText(list.get(position).getHomeworkId());
        viewHolder.tv_endTime.setText(list.get(position).getHomeworkContent());

        return convertView;
    }

    private static class ViewHolder{
        //每一个item中的控件
        TextView tv_homeworkId,tv_endTime;
    }

}
