package com.example.mohamed.newsfeed.Model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mohamed.newsfeed.Presenter.ArticlesFragmentPresenter;
import com.example.mohamed.newsfeed.R;
import com.example.mohamed.newsfeed.View.ArticlesAdapter;
import com.example.mohamed.newsfeed.View.ArticlesListFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    private static final String ENDPOINT = "https://newsapi.org/v1/articles?source=the-next-web&apiKey=533af958594143758318137469b41ba9";

    private ArticlesFragmentPresenter mPresenter;
    private Context mContext;
    private List<Article> articles;

    private RequestQueue mRequestQueue;
    private Gson gson;

    public Repository(Context mContext, ArticlesFragmentPresenter mPresenter){
        Log.v(TAG, "inside repository");
        this.mPresenter = mPresenter;
        this.mContext = mContext;
        articles = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(mContext);

        // Prepare Gson object
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }

    public void getArticles(){
        Log.v(TAG, "inside getArticles()");
        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
                        String status = "", art = "";

                        try {
                            status = jsonResponse.get("status").toString();
                            art = jsonResponse.get("articles").toString();
                            articles = Arrays.asList(gson.fromJson(art, Article[].class));

                            Log.v(TAG, "success: " + response);
                            if(articles.size() > 0){
                                // Attach Articles to Adapter
                                //mAdapter = new ArticlesAdapter(mContext, articles);
                                //mRecyclerView.setAdapter(mAdapter);
                            }
                            mPresenter.hideProgressBar();
                            mPresenter.showArticles(articles);

                        }catch (Exception e){
                            Log.v(TAG, "Error: " + e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: "+ error.toString());
                mPresenter.hideProgressBar();

                // Handling Error Cases
                if (error instanceof TimeoutError) {
                    mPresenter.showError(mContext.getResources().getString(R.string.timeout_err));
                } else if( error instanceof NoConnectionError){
                    mPresenter.showError(mContext.getResources().getString(R.string.noconnection_err));
                }else if (error instanceof AuthFailureError) {
                    mPresenter.showError(mContext.getResources().getString(R.string.authfailure_err));
                } else if (error instanceof ServerError) {
                    mPresenter.showError(mContext.getResources().getString(R.string.server_err));
                } else if (error instanceof NetworkError) {
                    mPresenter.showError(mContext.getResources().getString(R.string.network_err));
                } else if (error instanceof ParseError) {
                    mPresenter.showError(mContext.getResources().getString(R.string.parse_err));
                }
            }
        });
        mRequestQueue.add(request);
    }
}
