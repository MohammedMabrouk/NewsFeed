package com.example.mohamed.newsfeed.Presenter;

import android.content.Context;

import com.example.mohamed.newsfeed.Model.Article;
import com.example.mohamed.newsfeed.Model.ArticlesRepository;

import java.util.List;

public class ArticlesFragmentPresenter {

    private View view;
    private ArticlesRepository mRepository;
    private Context mContext;

    public ArticlesFragmentPresenter(View view, Context mContext){
        this.view = view;
        this.mContext = mContext;
        mRepository = new ArticlesRepository(mContext, this);
    }

    public void getArticles(){
       view.showProgressBar();
       mRepository.getArticles();
    }

    public void showArticles(List<Article> articles){
        view.showArticlesList(articles);
    }

    public void hideProgressBar(){
        view.hideProgressBar();
    }

    public void showError(String error){
        view.showErrorMsg(error);
    }




    public interface View{

        void showArticlesList(List<Article> articles);

        void showProgressBar();
        void hideProgressBar();

        void showErrorMsg(String error);
    }
}
