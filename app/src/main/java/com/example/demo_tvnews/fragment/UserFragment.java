package com.example.demo_tvnews.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.demo_tvnews.BaseActivity;
import com.example.demo_tvnews.LoginActivity;
import com.example.demo_tvnews.MainActivity;
import com.example.demo_tvnews.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class UserFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout relativeLayout;
    private Unbinder unbinder;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        relativeLayout = view.findViewById(R.id.rl_logout);
        relativeLayout.setOnClickListener(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_logout:
                romeKey();
                navigateToWithFlag(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
        }
    }

    //    清楚栈中所有的activity并将指定的activity放在最下面
//    navigateToWithFlag(LoginActivity.class,Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    public void navigateToWithFlag(Class cls, int flags) {
        Intent in = new Intent(getActivity(), cls);
        in.setFlags(flags);
        startActivity(in);
    }

    public void romeKey() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username").remove("userpwd").commit();
    }
}