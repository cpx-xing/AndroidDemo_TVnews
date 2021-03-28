package com.example.demo_tvnews.util;

import com.example.demo_tvnews.news.Newsbean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsUtils {
    public static List<Newsbean.ResultDTO.DataDTO>  getNewsbean(String newsname){
        String head = "http://v.juhe.cn/toutiao/index?type=";
        String name = newsname;
        String key = "&page=&page_size=&key=ab26369c5f61027169bdd1bebb9727ef";
        String url = head + name +key;

        List<Newsbean.ResultDTO.DataDTO> data = null;

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                return ;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Newsbean newsbean = new Gson().fromJson(response.body().string(),Newsbean.class);
                List<Newsbean.ResultDTO.DataDTO> data1 = newsbean.getResult().getData();
                for(int i = 0;i<data1.size();i++){
                    data.add(data1.get(i));
                }
            }
        });

        System.out.println(data.toString()+"=================================================================================");
        return data;

    }

//    //    使用gson解析数据
//    public static List<Newsbean.ResultDTO.DataDTO> parseShowData(Response response) {
//        Newsbean newsbean = null;
//        List<Newsbean.ResultDTO.DataDTO> dataDTO = null;
//        try {
//            newsbean = new Gson().fromJson(response.body().string(), Newsbean.class);
//            dataDTO = newsbean.getResult().getData();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dataDTO;
//    }

}
