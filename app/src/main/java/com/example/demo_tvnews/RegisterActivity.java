package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo_tvnews.db.DBManager;

import static com.example.demo_tvnews.util.StringUtils.isEmpty;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText registerAccount,registerPwd;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerAccount = findViewById(R.id.register_account);
        registerPwd = findViewById(R.id.register_pwd);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = registerAccount.getText().toString().trim();
        String pwd = registerPwd.getText().toString().trim();
        register(name,pwd);
    }
    private void register(String name,String pwd){
        if (isEmpty(name)) {
            showToast("请输入用户名");
            return;
        }
        if (isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        if(DBManager.registerUser(name,pwd)){
            showToast("注册成功");
            finish();
        }else{
            showToast("注册失败，该账号已被注册");
        }
    }
}