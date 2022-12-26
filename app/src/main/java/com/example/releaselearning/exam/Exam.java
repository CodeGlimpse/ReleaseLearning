package com.example.releaselearning.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.releaselearning.R;

public class Exam extends Fragment {

    private FragmentTransaction transaction;
    private NoBegin noBeginFragment;
    private Ongoing ongoingFragment;
    private End endFragment;

    private RadioGroup rg;
    private RadioButton rb_no_begin;
    private RadioButton rb_ongoing;
    private RadioButton rb_end;

    private View v_blue;
    private View v_white;
    private View v_white2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.activity_exam, container, false);

        rg = inflate.findViewById(R.id.rg);
        rb_no_begin = inflate.findViewById(R.id.rb_no_begin);
        rb_ongoing = inflate.findViewById(R.id.rb_ongoing);
        rb_end = inflate.findViewById(R.id.rb_end);

        v_blue = inflate.findViewById(R.id.v_blue);
        v_white = inflate.findViewById(R.id.v_white);
        v_white2 = inflate.findViewById(R.id.v_white2);

        transaction = getChildFragmentManager().beginTransaction();

        if (noBeginFragment == null) {
            noBeginFragment = new NoBegin();
            transaction.add(R.id.login_frame, noBeginFragment);
        } else {
            transaction.show(noBeginFragment);
        }
        transaction.commit();

        //RadioGroup选择监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_no_begin:
                        rb_no_begin.setBackground(getResources().getDrawable(R.color.blue_little_little));
                        rb_ongoing.setBackground(getResources().getDrawable(R.color.white));
                        rb_end.setBackground(getResources().getDrawable(R.color.white));

                        v_blue.setBackground(getResources().getDrawable(R.color.blue));
                        v_white.setBackground(getResources().getDrawable(R.color.white));
                        v_white2.setBackground(getResources().getDrawable(R.color.white));

                        transaction = getChildFragmentManager().beginTransaction();
                        if (noBeginFragment == null) {
                            noBeginFragment = new NoBegin();
                        }
                        transaction.replace(R.id.login_frame, noBeginFragment);
                        transaction.commit();
                        break;

                    case R.id.rb_ongoing:
                        rb_no_begin.setBackground(getResources().getDrawable(R.color.white));
                        rb_ongoing.setBackground(getResources().getDrawable(R.color.blue_little_little));
                        rb_end.setBackground(getResources().getDrawable(R.color.white));

                        v_blue.setBackground(getResources().getDrawable(R.color.white));
                        v_white.setBackground(getResources().getDrawable(R.color.blue));
                        v_white2.setBackground(getResources().getDrawable(R.color.white));

                        transaction = getChildFragmentManager().beginTransaction();
                        if (ongoingFragment == null) {
                            ongoingFragment = new Ongoing();
                        }
                        transaction.replace(R.id.login_frame, ongoingFragment);
                        transaction.commit();
                        break;

                    case R.id.rb_end:
                        rb_no_begin.setBackground(getResources().getDrawable(R.color.white));
                        rb_ongoing.setBackground(getResources().getDrawable(R.color.white));
                        rb_end.setBackground(getResources().getDrawable(R.color.blue_little_little));

                        v_blue.setBackground(getResources().getDrawable(R.color.white));
                        v_white.setBackground(getResources().getDrawable(R.color.white));
                        v_white2.setBackground(getResources().getDrawable(R.color.blue));

                        transaction = getChildFragmentManager().beginTransaction();
                        if (endFragment == null) {
                            endFragment = new End();
                        }
                        transaction.replace(R.id.login_frame, endFragment);
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