package com.example.demo_tvnews.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo_tvnews.R;


public class UserFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}