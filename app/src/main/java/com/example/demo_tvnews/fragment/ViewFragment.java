package com.example.demo_tvnews.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.FalsifyFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends Fragment {

    private String title;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    List<VideoEntity> datas = new ArrayList<>();

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
        smartRefreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        ???????????????
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
//        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));

//        ?????????????????????
        smartRefreshLayout.setRefreshHeader(new FalsifyFooter(getContext()));
        smartRefreshLayout.setRefreshFooter(new FalsifyFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//??????false??????????????????

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//??????false??????????????????
            }
        });

//      ????????????
        getList();
//        ???????????????
        VideoAdapter adapter = new VideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(adapter);
    }

    public void getList() {
        for (int i = 0; i < 8; i++) {
            datas.add(new VideoEntity(i, "???????????????????????????", "xing", i * 2, i * 2 + 3, i * 2 + 4));
        }

    }
}