package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.demo_tvnews.adapter.MyPagerAdapter;
import com.example.demo_tvnews.entity.TabEntity;
import com.example.demo_tvnews.fragment.CollectFragment;
import com.example.demo_tvnews.fragment.FirstFragment;
import com.example.demo_tvnews.fragment.HomeFragment;
import com.example.demo_tvnews.fragment.MyFragment;
import com.example.demo_tvnews.fragment.UserFragment;
import com.example.demo_tvnews.fragment.ZiXunFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    //设置时间间隔和上次退出时间
    private static final long TIME=2000;
    private long exitTime;

    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    //    底部的标签
//    private String[] mTitles = {"首页", "视频", "新闻","资讯","我的"};
    private String[] mTitles = {"首页", "视频", "新闻","我的"};
    //    为点击时的图案
//    private int[] mIconUnselectIds = {
//            R.mipmap.home, R.mipmap.message,
//            R.mipmap.news1,R.mipmap.zixun,R.mipmap.mine};
//    //    点击时的图案
//    private int[] mIconSelectIds = {
//            R.mipmap.home2, R.mipmap.message2,
//            R.mipmap.news2,R.mipmap.zixun2,R.mipmap.mine2};
    private int[] mIconUnselectIds = {
            R.mipmap.home, R.mipmap.message,
            R.mipmap.news1,R.mipmap.mine};
    //    点击时的图案
    private int[] mIconSelectIds = {
            R.mipmap.home2, R.mipmap.message2,
            R.mipmap.news2,R.mipmap.mine2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);

//        mFragments.add(HomeFragment.newInstance());
        mFragments.add(FirstFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
//        mFragments.add(ZiXunFragment.newInstance());
        mFragments.add(UserFragment.newInstance());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        commonTabLayout.setTabData(mTabEntities);

        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

//        预加载
        viewPager.setOffscreenPageLimit(mFragments.size());
//        随着viewpaper的滑动，底部的菜单按钮也随之变动
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mTitles,mFragments));
    }

    //重写onKeyDown方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断是否按的后退键，而且按了一次
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
        {
            //获取当前的系统时间，和exitTime相减，判断两次间隔是否大于规定时间
            //exitTime没有初始值则默认为0
            //如果大于设定的时间，则弹出提示，同时把exitTime设置为当前时间
            if(System.currentTimeMillis()-exitTime>TIME)
            {
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_LONG).show();
                exitTime= System.currentTimeMillis();
            }
            else
            {
                //如果再次按后退的时间小于规定时间，则退出
                //启动MainActivity
                Intent intentActivity = new Intent();
                intentActivity.setClass(getApplicationContext(),MainActivity.class);
                startActivity(intentActivity);

                Intent intentBroadcast = new Intent();
                intentBroadcast.setAction("action.exit.MainActivity");
                sendBroadcast(intentBroadcast);
            }
            //消费事件
            return true;
        }
        //不处理事件
        return false;
    }
}