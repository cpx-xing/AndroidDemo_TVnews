package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo_tvnews.api.ApiConfig;
import com.example.demo_tvnews.db.DBManager;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.demo_tvnews.util.StringUtils.isEmpty;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private EditText logingAccount, loginPwd;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ininView();

    }

    private void ininView() {
        logingAccount = findViewById(R.id.login_account);
        loginPwd = findViewById(R.id.loging_pwd);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        sp = getSharedPreferences("info", Context.MODE_PRIVATE);

//        获取本地的sharePreferences，如果存在则填写到文本框中
        logingAccount.setText(sp.getString("username",""));
        loginPwd.setText(sp.getString("userpwd",""));



    }

    @Override
    public void onClick(View v) {
        String account = logingAccount.getText().toString().trim();
        String pwd = loginPwd.getText().toString().trim();


//        创建OKHttpClient
        OkHttpClient client = new OkHttpClient.Builder().build();
        Map map = new HashMap();
        map.put("mobile",account);
        map.put("password",pwd);
        JSONObject jsonObject = new JSONObject(map);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonStr);
        Request request = new Request.Builder()
                .url(ApiConfig.BASE_URL + ApiConfig.Login)
                .addHeader("contentType","application/json;charset=utf-8")
                .post(requestBodyJson)
                .build();
        final  Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("ONFAILURE","-----------------------------");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                login(account, pwd);
                String string = response.body().string();
                Log.e("onResponse",string);
            }
        });


        login(account, pwd);
    }

    private void login(String account, String pwd) {
        if (isEmpty(account)) {
            showToast("请输入用户名");
            return;
        }
        if (isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        if(DBManager.checkUser(account,pwd)){
            showToast("登录成功");

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username",account);
            editor.putString("userpwd",pwd);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        }else{
            showToast("账号或密码错误");
        }
    }




}