package com.example.demo_tvnews.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.location.LocationManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_tvnews.MainActivity;
import com.example.demo_tvnews.R;
import com.example.demo_tvnews.adapter.ImageBannerAdapter;
import com.example.demo_tvnews.entity.DataBean;
import com.example.demo_tvnews.entity.LocationBean;
import com.example.demo_tvnews.entity.Movies;
import com.example.demo_tvnews.entity.MoviesBean;
import com.example.demo_tvnews.entity.WeatherBean;
import com.google.android.exoplayer2.C;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class FirstFragment extends BaseFragment implements View.OnClickListener {
    private TextView tempereture;
    private ImageView icon;
    private TextView weather_aqi;
    private String weather_url;
    private TextView weather_info;
    Banner banner;
    private TextView locationmsg;
    private ImageBannerAdapter adapter;
    private CardView cardView;
    private TextView apibody;
    private Button locationBtn;
    double longitude;
    double latitude;
    String jingdu;
    String weidu;

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
        //      相应权限申请
        List<String> premissionList = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            premissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!premissionList.isEmpty()) {
            String[] premissions = premissionList.toArray(new String[premissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(), premissions, 1);
        } else {
        }


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
        banner = view.findViewById(R.id.banner);
        apibody = view.findViewById(R.id.apibody);
//        String test = "http://apis.juhe.cn/simpleWeather/query?city=杭州&key=511dfd17bf4b6ebe6181637e99d0ac97";
        weather_url = "http://apis.juhe.cn/simpleWeather/query?city=%E6%9D%AD%E5%B7%9E&key=511dfd17bf4b6ebe6181637e99d0ac97";
        getWeather(weather_url);
        locationBtn = view.findViewById(R.id.locationBtn);
        locationmsg = view.findViewById(R.id.locationText);
        cardView = view.findViewById(R.id.weather_card);
        cardView.setOnClickListener(this);
        locationBtn.setOnClickListener(this);

//        给banner添加数据
        adapter = new ImageBannerAdapter(DataBean.getTestData2(), getContext());
        banner.setAdapter(adapter)
                .setScrollTime(1000)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()))//设置指示器
                .setOnBannerListener((data, position) -> {
                    Snackbar.make(banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                    LogUtils.d("position：" + position);
                });
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weather_card:
                banner.setAdapter(new ImageBannerAdapter(DataBean.getTestData(), getContext()));
                break;
            case R.id.locationBtn:
                getLoation();
                break;
        }
    }

    public void getWeather(String string) {
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

    public void getLoation() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//        String providerName = locationManager.getBestProvider(criteria, true);
//        String providerName = LocationManager.NETWORK_PROVIDER;
        String providerName = "";
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            providerName = LocationManager.NETWORK_PROVIDER;
        } else if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            providerName = LocationManager.GPS_PROVIDER;
        } else {
            Toast.makeText(getContext(), "provider 获取失败", Toast.LENGTH_SHORT).show();
            return;
        }
        // 权限复验
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "权限未授权，请先授权UHello定位权限", Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(providerName);

        if (location != null) {
            longitude = location.getLongitude();//经度
            jingdu = String.format("%.6f", location.getLongitude());
            latitude = location.getLatitude();//纬度
            weidu = String.format("%.6f", location.getLatitude());
            apibody.setText("经度：" + longitude + "\n" + "纬度：" + latitude);
        }

        String url1 = "http://api.map.baidu.com/geocoder?output=json&location=";
        String url2 = "&ak=I9BuovIecQCsqSoXAQlNasodfz4F8xMp";
        String url = url1 + latitude + "," + longitude + url2;

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LocationBean locationBean = new Gson().fromJson(response.body().string(), LocationBean.class);
                String adress = locationBean.getResult().getFormattedAddress();
                locationmsg.setText(adress);
                String w1 = "http://apis.juhe.cn/simpleWeather/query?city=";
                String w2 = "&key=511dfd17bf4b6ebe6181637e99d0ac97";
//                getWeather(w1+locationBean.getResult().getAddressComponent().getCity()+w2);
//                getWeather(weather_url);

                String city = locationBean.getResult().getAddressComponent().getCity().substring(0, 2);

                Message msg = new Message();
                msg.obj = w1 + city + w2;
                msg.what = 1;
                handler.sendMessage(msg);
                Log.e("LOCATIONMSG", msg.obj + w2);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    break;
            }
        }
    };

    public void test(String s) {
        locationBtn.setText(s);
    }


}