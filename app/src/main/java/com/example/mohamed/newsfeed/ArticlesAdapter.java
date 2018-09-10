package com.example.mohamed.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Article> articleList;


    public ArticlesAdapter(Context mContext, List<Article> articleList) {
        this.mContext = mContext;
        this.articleList = articleList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, author, publishDate;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.article_title_textView);
            author = (TextView) view.findViewById(R.id.article_author_textView);
            publishDate = (TextView) view.findViewById(R.id.article_publishDate_textView);
            thumbnail = (ImageView) view.findViewById(R.id.article_thumbnail_imageView);
        }

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
        holder.author.setText(article.getAuthor());
        holder.publishDate.setText(article.getPublishDate());

        // load article thumbnail image using picasso
        Picasso.get()
                .load(article.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        // set on click listener
    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
