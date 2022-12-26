package com.example.releaselearning.homeWork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.releaselearning.Entity.Homework;
import com.example.releaselearning.R;
import com.example.releaselearning.Utils.ScreenUtils;


public class HomeWork extends Fragment{

    private FragmentTransaction transaction;
    private NoLook noLookFragment;

    //private YesLook yesLookFragment;
    private HomeWork yesLookFragment;
    private RadioGroup rg;
    private RadioButton rb_no;
    private RadioButton rb_yes;

    private View v_blue;
    private View v_white;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.activity_home_work, container, false);
        rg = inflate.findViewById(R.id.rg);
        rb_no = inflate.findViewById(R.id.rb_no);
        rb_yes = inflate.findViewById(R.id.rb_yes);
        v_blue = inflate.findViewById(R.id.v_blue);
        v_white = inflate.findViewById(R.id.v_white);

        transaction = getChildFragmentManager().beginTransaction();

        if (noLookFragment == null) {
            noLookFragment = new NoLook();
            transaction.add(R.id.homework_vp, noLookFragment);
        } else {
            transaction.show(noLookFragment);
        }
        transaction.commit();

        //RadioGroup选择监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_no:
                        rb_no.setBackground(getResources().getDrawable(R.color.blue_little_little));
                        rb_yes.setBackground(getResources().getDrawable(R.color.white));
                        v_blue.setBackground(getResources().getDrawable(R.color.blue));
                        v_white.setBackground(getResources().getDrawable(R.color.white));
                        transaction = getChildFragmentManager().beginTransaction();
                        if (noLookFragment == null) {
                            noLookFragment = new NoLook();
                        }
                        transaction.replace(R.id.homework_vp, noLookFragment);
                        transaction.commit();
                        break;

                    case R.id.rb_yes:
                        rb_no.setBackground(getResources().getDrawable(R.color.white));
                        rb_yes.setBackground(getResources().getDrawable(R.color.blue_little_little));
                        v_blue.setBackground(getResources().getDrawable(R.color.white));
                        v_white.setBackground(getResources().getDrawable(R.color.blue));
                        transaction = getChildFragmentManager().beginTransaction();
                        if (yesLookFragment == null) {
                            yesLookFragment = new HomeWork();
                        }
                        transaction.replace(R.id.homework_vp, yesLookFragment);
                        transaction.commit();
                        break;
                    default:
                        break;
                }
            }
        });
        return inflate;
    }
}