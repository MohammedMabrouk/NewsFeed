package com.example.mohamed.newsfeed.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.newsfeed.Model.Article;
import com.example.mohamed.newsfeed.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {
    private final static String TAG = ArticlesAdapter.class.getSimpleName();

    private Context mContext;
    private List<Article> articleList;

    private RecyclerViewClickListener mListener;

    public ArticlesAdapter(Context mContext, List<Article> articleList) {
        this.mContext = mContext;
        this.articleList = articleList;
    }

    public void setClickListener(RecyclerViewClickListener mListener){
        this.mListener = mListener;
    }


    @NonNull
    @Override
    public ArticlesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.author.setText(mContext.getResources().getString(R.string.author_str, article.getAuthor()));

        holder.publishDate.setText(com.example.mohamed.newsfeed.Utls.DateFormat.changeDateFormat(article.getPublishDate()
                , com.example.mohamed.newsfeed.Utls.DateFormat.articleDateFormat
                , com.example.mohamed.newsfeed.Utls.DateFormat.layoutDateFormat));

        // load article thumbnail image using picasso
        Picasso.get()
                .load(article.getImgUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, author, publishDate;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.article_title_textView);
            author = (TextView) view.findViewById(R.id.article_author_textView);
            publishDate = (TextView) view.findViewById(R.id.article_publishDate_textView);
            thumbnail = (ImageView) view.findViewById(R.id.article_thumbnail_imageView);

            view.setOnClickListener(this);
            thumbnail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }
}
