package com.example.mohamed.newsfeed.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ParseException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.newsfeed.Model.Article;
import com.example.mohamed.newsfeed.R;
import com.squareup.picasso.Picasso;


public class ArticleDetails extends AppCompatActivity {

    private ImageView articleImage;
    private TextView articleTitle, articleAuthor, articleDescription, articleDate;
    private Button openWebsiteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        articleImage = (ImageView) findViewById(R.id.article_thumbnail_imageView);
        articleTitle = (TextView) findViewById(R.id.article_title_textView);
        articleAuthor = (TextView) findViewById(R.id.article_author_textView);
        articleDescription = (TextView) findViewById(R.id.article_description_textView);
        articleDate = (TextView) findViewById(R.id.article_publishDate_textView);

        openWebsiteBtn = (Button) findViewById(R.id.open_website_btn);

        // Setting the Toolbar
        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        final Article article = (Article) intent.getSerializableExtra("article");

        // load image
        Picasso.get()
                .load(article.getImgUrl())
                .placeholder(R.drawable.placeholder)
                .into(articleImage);


        // load text values
        articleTitle.setText(article.getTitle());
        articleAuthor.setText(getResources().getString(R.string.author_str, article.getAuthor()));
        articleDescription.setText(article.getDescription());
        articleDate.setText(com.example.mohamed.newsfeed.Utls.DateFormat.changeDateFormat(article.getPublishDate()
        , com.example.mohamed.newsfeed.Utls.DateFormat.articleDateFormat
        , com.example.mohamed.newsfeed.Utls.DateFormat.layoutDateFormat));

        // set button action
        openWebsiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                startActivity(browserIntent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
