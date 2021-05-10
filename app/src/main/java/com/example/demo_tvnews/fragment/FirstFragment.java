package com.example.demo_tvnews.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.entity.WeatherBean;
import com.google.android.exoplayer2.C;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirstFragment extends Fragment implements View.OnClickListener {
    private TextView tempereture;
    private ImageView icon;
    private TextView weather_aqi;
    private String weather_url;
    private TextView weather_info;
    private TextView apiText;

    private CardView cardView;


    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        tempereture = view.findViewById(R.id.weather_temperature);
        icon = view.findViewById(R.id.weather_icon);
        weather_info = view.findViewById(R.id.weather_info);
        weather_aqi = view.findViewById(R.id.weather_aqi);
        apiText = view.findViewById(R.id.apibody);
        weather_url = "http://apis.juhe.cn/simpleWeather/query?city=%E6%9D%AD%E5%B7%9E&key=511dfd17bf4b6ebe6181637e99d0ac97";
        getMovies(weather_url);
        getApiMsg();
        cardView = view.findViewById(R.id.weather_card);
        cardView.setOnClickListener(this);
        return view;
    }

    public void getMovies(String string) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().get().url(string).build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                WeatherBean weatherBean = new Gson().fromJson(response.body().string(), WeatherBean.class);
                tempereture.setText(weatherBean.getResult().getRealtime().getTemperature() + "℃");
                weather_aqi.setText(weatherBean.getResult().getRealtime().getAqi());
                weather_info.setText(weatherBean.getResult().getRealtime().getInfo());
                String temp = weatherBean.getResult().getRealtime().getInfo();
//                switch (temp){
//                    case "晴":
//                        icon.setImageResource(R.mipmap.w0);
//                        break;
//                    case "阴":
//                        icon.setImageResource(R.mipmap.w1);
//                        break;
//                }

                if (temp.equals("晴")) {
                    icon.setImageResource(R.mipmap.w0);
                } else if (temp.equals("阴")) {
                    icon.setImageResource(R.mipmap.w1);
                } else {
                    icon.setImageResource(R.mipmap.wdefualt);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weather_card:
                Toast.makeText(getContext(), "天气情况", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //    测试百度API
    public void getApiMsg() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "");

        Request request = new Request.Builder()
                .url("http://gwgp-qvaqxkx3eoc.n.bdcloudapi.com/movie/detail?movieid=137197&moviename=&")
                .addHeader("X-Bce-Signature", "AppCode/cbcea4898c5746a69225be0e52e0e122")
                .post(body).build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                apiText.setText(response.body().string());
            }
        });

    }
}