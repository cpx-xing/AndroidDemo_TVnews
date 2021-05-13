package com.example.demo_tvnews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_tvnews.entity.DataBean;
import com.example.demo_tvnews.entity.MoviesBean;
import com.squareup.picasso.Picasso;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ImageBannerAdapter extends BannerAdapter<DataBean,ImageBannerAdapter.BannerViewHolder> {
    private Context mContext;
    public ImageBannerAdapter(List<DataBean> datas,Context mContext) {
        super(datas);
        this.mContext = mContext;
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, DataBean data, int position, int size) {
        Picasso.with(mContext)
                .load(data.imageRes)
//                下面可以更改图片样式
//                .transform(new CircleTransform())
                .into(holder.imageView);
    }

//    @Override
//    public void onBindView(BannerViewHolder holder, MoviesBean data, int position, int size) {
//        Picasso.with(mContext)
//                .load(data.getPic())
////                下面可以更改图片样式
////                .transform(new CircleTransform())
//                .into(holder.imageView);
//    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView;
        }
    }
}
