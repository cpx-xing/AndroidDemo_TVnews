package com.example.demo_tvnews.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.news.Newsbean;
import com.google.gson.Gson;

import java.io.IOException;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;

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
        textView = view.findViewById(R.id.myFragmentText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("http://v.juhe.cn/toutiao/index?type=&page=&page_size=&key=ab26369c5f61027169bdd1bebb9727ef").get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                view1.setText(parseShowData(response).getTitle());
                String s = parseShowData(response).getTitle();
                textView.setText(s);

                Log.e("TITLE",s);
            }
        });
    }

    //    使用gson解析数据
    public Newsbean.ResultDTO.DataDTO parseShowData(Response response) {
        Newsbean newsbean = null;
        Newsbean.ResultDTO.DataDTO dataDTO = null;
        try {
            newsbean = new Gson().fromJson(response.body().string(), Newsbean.class);
             dataDTO = newsbean.getResult().getData().get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataDTO;
    }


}