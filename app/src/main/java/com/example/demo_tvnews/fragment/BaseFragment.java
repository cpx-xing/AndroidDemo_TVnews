package com.example.demo_tvnews.fragment;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    protected void saveStringNews(String key,String val){
        SharedPreferences sp = getActivity().getSharedPreferences("news_offline", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("新闻名称","内容");
        editor.putString(key,val);
        editor.commit();
    }
    protected String getStringNews(String key){
        SharedPreferences preferences = getActivity().getSharedPreferences("news_offline", Context.MODE_PRIVATE);
        return preferences.getString(key,"");
    }


}
