package com.example.demo_tvnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videocontroller.component.PrepareView;
import com.example.demo_tvnews.OnItemChildClickListener;
import com.example.demo_tvnews.OnItemClickListener;
import com.example.demo_tvnews.R;
import com.example.demo_tvnews.entity.VideoBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.VideoHolder> {

    protected List<VideoBean> mVideos;
    protected Context mContext;
    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemClickListener mOnItemClickListener;

    public VideoRecyclerViewAdapter(List<VideoBean> mVideos, Context mContext) {
        this.mVideos = mVideos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VideoRecyclerViewAdapter.VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        return new VideoHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull VideoRecyclerViewAdapter.VideoHolder holder, int position) {
        VideoBean videoBean = mVideos.get(position);
//        缩略图
        Picasso.with(mContext)
                .load(videoBean.getThumb()).into(holder.mThumb);
        holder.mPosition = position;
        holder.mTitle.setText(videoBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public int mPosition;
        public FrameLayout mPlayerContainer;
        public TextView mTitle;
        public ImageView mThumb;
        public PrepareView mPrepareView;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            mPlayerContainer = itemView.findViewById(R.id.player_container);
            mTitle = itemView.findViewById(R.id.tv_title);
            mPrepareView = itemView.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.findViewById(R.id.thumb);
            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            itemView.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
                }
            }
        }
    }
    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
