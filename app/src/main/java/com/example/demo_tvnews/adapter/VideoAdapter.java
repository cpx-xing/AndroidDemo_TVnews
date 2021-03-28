package com.example.demo_tvnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.entity.VideoEntity;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datas;

    public  VideoAdapter(Context context, List<VideoEntity> datas){
        this.mContext = context;
        this.datas = datas;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datas.get(position);
        vh.tvTitle.setText(videoEntity.getTitle());
        vh.tvAuthor.setText(videoEntity.getName());
        vh.tvPinglun.setText(videoEntity.getPlCount()+"");
        vh.tvShoucang.setText(videoEntity.getScCount()+"");
        vh.tvDianzan.setText(videoEntity.getDzCount()+"");

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvPinglun;
        private TextView tvShoucang;
        private TextView tvDianzan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.top_title);
            tvAuthor = itemView.findViewById(R.id.top_author);
            tvPinglun = itemView.findViewById(R.id.pinglun);
            tvShoucang = itemView.findViewById(R.id.shoucang);
            tvDianzan = itemView.findViewById(R.id.dianzan);

        }
    }
}
