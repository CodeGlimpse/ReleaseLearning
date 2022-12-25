package com.example.releaselearning.homeWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import com.example.releaselearning.R;
import com.example.releaselearning.homeWork.fragment.Corrected;
import com.example.releaselearning.homeWork.fragment.MyFragmentAdapter;
import com.example.releaselearning.homeWork.fragment.NotCorrected;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;




public class HomeWork extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 vp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);

        getViews();
        vp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        List<Fragment> fragments = setData();
        MyFragmentAdapter adapter = new MyFragmentAdapter(fragments,this);
        vp2.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(
                tabLayout,
                vp2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position){
                            case 0://第一个标签
                                tab.setText("待阅");
                               // tab.setIcon(R.mipmap.home);
                                break;
                            case 1://第二个标签
                                tab.setText("已阅");
                               // tab.setIcon(R.mipmap.cart);
                                break;

                        }
                    }
                }
        );
        mediator.attach();


    }


    private List<Fragment> setData() {
        NotCorrected notCorrected = new NotCorrected();
        Corrected corrected = new Corrected();
        List<Fragment> list = new ArrayList<>();
        list.add(notCorrected);
        list.add(corrected);
        return list;
    }

    private void getViews() {
        tabLayout = findViewById(R.id.tablayout);
        vp2 = findViewById(R.id.vp2);
    }
}