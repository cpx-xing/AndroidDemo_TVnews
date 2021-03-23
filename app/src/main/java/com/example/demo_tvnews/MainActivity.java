package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo_tvnews.db.DBManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button loginButton,registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBManager.initDB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化控件
        ininView();
}
    private void ininView(){
        loginButton = findViewById(R.id.login_btn);
        registerButton = findViewById(R.id.register_btn);


        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
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
}