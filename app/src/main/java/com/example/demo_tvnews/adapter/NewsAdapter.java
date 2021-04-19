package com.example.demo_tvnews.adapter;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_tvnews.R;
import com.example.demo_tvnews.entity.NewsEntity;
import com.example.demo_tvnews.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.zip.Inflater;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int GET_DATA_SUCCESS = 1;
    private Context mContext;
    private List<NewsEntity> datas;
    //    设置监听
    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public NewsAdapter(Context mContext, List<NewsEntity> datas) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        NewsEntity entity = datas.get(position);
        viewHolder.title.setText(entity.getNewsTitle());
//        viewHolder.imageView.setImageURI(Uri.parse(entity.getImageUrl()));
        Picasso.with(mContext)
                .load(entity.getImageUrl())
//                下面可以更改图片样式
//                .transform(new CircleTransform())
                .into(viewHolder.imageView);
        viewHolder.author.setText(entity.getNewsAuthor());
        viewHolder.time.setText(entity.getNewsTime());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;
        private TextView author;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_news_title);
            imageView = itemView.findViewById(R.id.item_news_img);
            author = itemView.findViewById(R.id.item_news_author);
            time = itemView.findViewById(R.id.item_news_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("+++++++++++++++++++++++","--------------------------------");
                }
            });
        }
    }
}