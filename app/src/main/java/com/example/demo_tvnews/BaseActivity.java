package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public void showToast(String string){
        Toast.makeText(mContext,string,Toast.LENGTH_SHORT).show();
    }

    public void navigateTo(Class cls){
        Intent in = new Intent(mContext,cls);
        startActivity(in);
    }



}