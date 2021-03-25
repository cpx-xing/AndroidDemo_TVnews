package com.example.demo_tvnews.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.adapter.VideoAdapter;
import com.example.demo_tvnews.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {

    private String title;

    public ViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(String title) {
        ViewFragment fragment = new ViewFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
//        布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<VideoEntity> datas = new ArrayList<>();
        for(int i = 0;i< 8;i++){
            datas.add(new VideoEntity(i,"这是一条开心的狗狗","xing",i*2,i*2+3,i*2+4));
        }
        VideoAdapter adapter = new VideoAdapter(getActivity(),datas);
        recyclerView.setAdapter(adapter);
        return view;
    }
}