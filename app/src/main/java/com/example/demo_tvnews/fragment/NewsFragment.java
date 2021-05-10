package com.example.demo_tvnews.fragment;

import android.content.Context;
import android.content.Intent;
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

import com.example.demo_tvnews.NewsContentActivity2;
import com.example.demo_tvnews.OnItemChildClickListener;
import com.example.demo_tvnews.OnItemClickListener;
import com.example.demo_tvnews.R;
import com.example.demo_tvnews.adapter.NewsAdapter;
import com.example.demo_tvnews.db.DBManager;
import com.example.demo_tvnews.db.NewsContentBean;
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

public class NewsFragment extends BaseFragment implements OnItemChildClickListener {

    private String name;
    String head = "http://v.juhe.cn/toutiao/index?type=";
    String key = "&page=&page_size=&key=ab26369c5f61027169bdd1bebb9727ef";
    String url = null;
    String defaultUrl = "https://t7.baidu.com/it/u=715017140,34998045&fm=193&f=GIF";
    RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;
    private String reason = "超过每日可允许请求次数";
    List<NewsEntity> list = new ArrayList<>();

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
        String url = head + name + key;
//        Log.e("URL", url);

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
                String result = response.body().string();
//                Log.e("TAG", result);
//                添加判断：如果取到了数据，保存到数据库中，未取到数据则跳过
//                Log.e("TAG",result.indexOf(reason)+"");
                if (result.indexOf(reason) == -1) {
                    showNews(result);
                    NewsContentBean contentBean = new NewsContentBean(name, result);
//                    如果数据库中有数据，则更新，没有则添加
                    if (DBManager.isFirstData()) {
                        DBManager.putNewsContent(contentBean);
                    } else {
//                  数据库中已经有数据了，需要操作更新
                        DBManager.reflashNews(name, contentBean);
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        采用存储到数据库的方法--读取数据库中的数据
        try {
            String getNewsContent = DBManager.getNewsContent(name);
            showNews(getNewsContent);
        } catch (Exception e) {
//            list.add(new NewsEntity(0, name, defaultUrl, "xing", new Date().toString()));
        }
        NewsAdapter adapter = new NewsAdapter(getActivity(), list);
        adapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void showNews(String s) {
        Newsbean newsbean = new Gson().fromJson(s, Newsbean.class);
        List<Newsbean.ResultDTO.DataDTO> data1 = newsbean.getResult().getData();
        for (int i = 0; i < data1.size(); i++) {
            list.add(new NewsEntity(i, data1.get(i).getTitle(), data1.get(i).getThumbnail_pic_s(), data1.get(i).getAuthor_name(), data1.get(i).getDate()));
        }
    }

    @Override
    public void onItemChildClick(int position) {
        Toast.makeText(getContext(), "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), NewsContentActivity2.class);
        intent.putExtra("url", "https://blog.csdn.net/weixin_40438421/article/details/85700109");
        this.startActivity(intent);
    }
}