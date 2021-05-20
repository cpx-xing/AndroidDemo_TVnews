package com.example.demo_tvnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class FilpperActivity2 extends AppCompatActivity {

    ViewFlipper viewFlipper;
    Context mContext;
    Button button;

    private int[] resId = {R.mipmap.v1,R.mipmap.v2,R.mipmap.v3};

    private final static int MIN_MOVE = 200;   //最小距离
    private MyGestureListener mgListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filpper2);
        viewFlipper = findViewById(R.id.viewfilpper);
        button = findViewById(R.id.skipbtn);
        button.getBackground().setAlpha(100);
        mContext =getApplicationContext();
        //实例化SimpleOnGestureListener与GestureDetector对象
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
        //动态导入添加子View
        for(int i = 0;i < resId.length;i++){
            viewFlipper.addView(getImageView(resId[i]));
        }
        viewFlipper.startFlipping();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //重写onTouchEvent触发MyGestureListener里的方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }
    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if(e1.getX() - e2.getX() > MIN_MOVE){
                viewFlipper.setInAnimation(mContext,R.anim.right_in);
                viewFlipper.setOutAnimation(mContext, R.anim.right_out);
                viewFlipper.showNext();
            }else if(e2.getX() - e1.getX() > MIN_MOVE){
                viewFlipper.setInAnimation(mContext,R.anim.left_in);
                viewFlipper.setOutAnimation(mContext, R.anim.left_out);
                viewFlipper.showPrevious();
            }
            return true;
        }
    }

    private ImageView getImageView(int resId){
        ImageView img = new ImageView(this);
        img.setBackgroundResource(resId);
        return img;
    }
}