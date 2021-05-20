package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo_tvnews.db.DBManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button loginButton,registerButton;
    public static int mCurrentIndex;

    private static final String EXIT_MAIN_ACTIVITY_ACTION = "action.exit.MainActivity";
    private ExitMainActivityReceiver exitMainActivityReceiver = new ExitMainActivityReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBManager.initDB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        判断是否已经登录过，如果已经登录过则直接进入首页
        SharedPreferences sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("username","") != ""){
            navigateTo(HomeActivity.class);
        }else {
            //        判断是不是第一次进入软件
            SharedPreferences sp2 = getSharedPreferences("info",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp2.edit();
            if ((sp2.getString("isFirst","") == "")){
                editor.putString("isFirst","true");
                editor.commit();
                Intent intent = new Intent(this,FilpperActivity2.class);
                startActivity(intent);
            }
        }



        IntentFilter filter = new IntentFilter();
        filter.addAction(EXIT_MAIN_ACTIVITY_ACTION);
        registerReceiver(exitMainActivityReceiver, filter);

        loginButton = findViewById(R.id.login_btn);
        registerButton = findViewById(R.id.register_btn);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
//        初始化控件

}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        unregisterReceiver(exitMainActivityReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                navigateTo(LoginActivity.class);
                break;
            case R.id.register_btn:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
    class ExitMainActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ((MainActivity)context).finish();
        }
    }
}