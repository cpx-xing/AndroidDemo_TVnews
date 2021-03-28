package com.example.demo_tvnews.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.adapter.NewsAdapter;
import com.example.demo_tvnews.entity.NewsEntity;
import com.example.demo_tvnews.news.Newsbean;
import com.example.demo_tvnews.util.NewsUtils;
import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private String name;
    String head = "http://v.juhe.cn/toutiao/index?type=";
    String key = "&page=&page_size=&key=ab26369c5f61027169bdd1bebb9727ef";
    String url = null;
    String defaultUrl = "https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/04/0A/ChMkJ1bWVkqIAOIIAATtjMLDO44AAMRawJxKN0ABO2k023.jpg";
    RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static NewsFragment newInstance(String name) {
        NewsFragment fragment = new NewsFragment();
        fragment.name = name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.newsrecyclerView);
        smartRefreshLayout = view.findViewById(R.id.smartrefreshLayout);

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

        //        布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<NewsEntity> list = new ArrayList<>();
        String url = head + name + key;
        Log.e("URL", url);

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                return;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Newsbean newsbean = new Gson().fromJson(response.body().string(), Newsbean.class);
                    List<Newsbean.ResultDTO.DataDTO> data1 = newsbean.getResult().getData();
                    for (int i = 0; i < data1.size(); i++) {
                        list.add(new NewsEntity(i, data1.get(i).getTitle(), data1.get(i).getThumbnail_pic_s(), data1.get(i).getAuthor_name(), data1.get(i).getDate()));
                    }
                    saveStringNews(name, response.body().string());
//                    保存数据
                } catch (Exception e) {
//                    离线数据
                    String offline = getStringNews(name);
                    try {
                        Newsbean offlinenewsBean = new Gson().fromJson(offline, Newsbean.class);
                        List<Newsbean.ResultDTO.DataDTO> data2 = offlinenewsBean.getResult().getData();
                        for (int i = 0; i < data2.size(); i++) {
                            list.add(new NewsEntity(i, data2.get(i).getTitle(), data2.get(i).getThumbnail_pic_s(), data2.get(i).getAuthor_name(), data2.get(i).getDate()));
                        }
                    } catch (Exception e1) {
                        list.add(new NewsEntity(0, "超过每日可允许请求次数", defaultUrl, "温馨提示", new Date().toString()));
                    }
                }
            }
        });
        NewsAdapter adapter = new NewsAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("Click", position + "");
    }
}