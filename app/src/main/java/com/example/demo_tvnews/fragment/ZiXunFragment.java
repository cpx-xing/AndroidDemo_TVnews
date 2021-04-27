package com.example.demo_tvnews.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.adapter.VideoRecyclerViewAdapter;
import com.example.demo_tvnews.adapter.ZiXunAdapter;
import com.example.demo_tvnews.entity.ZiXunBean;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ZiXunFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected SmartRefreshLayout smartRefreshLayout;
    protected List<ZiXunBean> datas;
    protected ZiXunAdapter adapter;
    protected LinearLayoutManager mLinearLayoutManager;

    public ZiXunFragment() {
        // Required empty public constructor
    }
    public static ZiXunFragment newInstance(){
        ZiXunFragment ziXunFragment = new ZiXunFragment();
        return ziXunFragment;
    }

//    初始化数据
//    public  void getDatas(){
//        for (int i = 0;i<15;i++){
//            ZiXunBean ziXunBean = new ZiXunBean();
//            ziXunBean.setType(i%3+1);
//            datas.add(ziXunBean);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_zi_xun, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        smartRefreshLayout = view.findViewById(R.id.refreshLayout);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        datas = new ArrayList<>();
        for (int i = 0;i<15;i++) {
            int type = i % 3 + 1;
            ZiXunBean ziXunBean = new ZiXunBean();
            ziXunBean.setType(type);
            datas.add(ziXunBean);
        }
        adapter = new ZiXunAdapter(getContext(),datas);
        mRecyclerView.setAdapter(adapter);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
        return view;
    }
}