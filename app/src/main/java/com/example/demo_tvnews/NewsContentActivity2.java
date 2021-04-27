package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class NewsContentActivity2 extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private WebView webView;
    private String newsurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content2);

        Intent intent = getIntent();
        newsurl = intent.getStringExtra("url");
        imageView = findViewById(R.id.back);
        webView = findViewById(R.id.webwiew);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.loadUrl(newsurl);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
           finish();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        webView.destroy();
        webView = null;
    }
}