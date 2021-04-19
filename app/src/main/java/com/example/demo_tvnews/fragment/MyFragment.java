package com.example.demo_tvnews.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.adapter.MyPagerAdapter;
import com.example.demo_tvnews.news.Newsbean;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private ArrayList<Fragment> newsFragment = new ArrayList<>();
    private final String[] newType = new String[]{
            "热门","国内","国际","娱乐","体育","军事","科技","财经","时尚","游戏","汽车","健康"
    };
    private final String[] newTypeName = {
         "top","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang","youxi","qiche","jiankang"
    };

    private ViewPager newsViewPager;
    private SlidingTabLayout newsSlidingTabLayout;

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        newsViewPager = view.findViewById(R.id.news_fixedViewPager);
        newsSlidingTabLayout = view.findViewById(R.id.news_slidingTabLayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (String name : newTypeName){
            newsFragment.add(NewsFragment.newInstance(name));
        }
        newsViewPager.setOffscreenPageLimit(newsFragment.size());
        newsViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),newType,newsFragment));
        newsSlidingTabLayout.setViewPager(newsViewPager);
    }
}