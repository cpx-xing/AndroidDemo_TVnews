package com.example.demo_tvnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.entity.ZiXunBean;
import com.example.demo_tvnews.news.Newsbean;

import java.util.List;

public class ZiXunAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<ZiXunBean> datas;

    public ZiXunAdapter(Context mContext, List<ZiXunBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        int type = datas.get(position).getType();
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
            return new ViewHolderOne(view);
        }else if (viewType == 2) {
                View view2 = LayoutInflater.from(mContext).inflate(R.layout.news_item_two, parent, false);
                return new ViewHolderTwo(view2);
        }else {
                View view3 = LayoutInflater.from(mContext).inflate(R.layout.news_item_three,parent,false);
                return new ViewHolderThree(view3);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case 1:
                ViewHolderOne vh = (ViewHolderOne) holder;
                break;
            case 2:
                ViewHolderTwo vh2 = (ViewHolderTwo) holder;
                break;
            case 3:
                ViewHolderThree vh3 = (ViewHolderThree) holder;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    public class ViewHolderOne extends RecyclerView.ViewHolder{

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class ViewHolderTwo extends RecyclerView.ViewHolder{

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class ViewHolderThree extends RecyclerView.ViewHolder{

        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
        }
    }

}
