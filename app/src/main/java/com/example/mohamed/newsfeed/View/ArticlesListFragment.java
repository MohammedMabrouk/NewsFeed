package com.example.mohamed.newsfeed.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mohamed.newsfeed.Model.Article;
import com.example.mohamed.newsfeed.Presenter.ArticlesFragmentPresenter;
import com.example.mohamed.newsfeed.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArticlesListFragment extends Fragment implements ArticlesFragmentPresenter.View, RecyclerViewClickListener{
    private static final String TAG = ArticlesListFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ArticlesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ProgressBar loadingProgressBar;

    private Context mContext;
    private ArticlesFragmentPresenter mPresenter;

    List<Article> articleList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        mPresenter = new ArticlesFragmentPresenter(this, mContext);
        articleList = new ArrayList<>();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_articles_list, container, false);

        loadingProgressBar = (ProgressBar) root.findViewById(R.id.loading_progressbar);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.articles_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPresenter.getArticles();

        return root;
    }


    @Override
    public void showArticlesList(List<Article> articles) {
        articleList = articles;
        Log.v(TAG, "inside show articles list()");
        mAdapter = new ArticlesAdapter(mContext, articles);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
    }

    @Override
    public void showProgressBar() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }


    // When clicking on an article
    @Override
    public void onClick(View view, int position) {
        final Article article = articleList.get(position);
        Intent intent = new Intent(mContext, ArticleDetails.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }
}
