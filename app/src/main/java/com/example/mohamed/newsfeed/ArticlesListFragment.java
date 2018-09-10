package com.example.mohamed.newsfeed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ArticlesListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Article> tmp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tmp = new ArrayList<>();
        tmp.add(new Article("By: Andrew John", "This is article title: Google buys IBM for the reason of what.","sasasa","sasas","sasasa","April 3, 2017"));
        tmp.add(new Article("sasas", "2","sasasa","sasas","sasasa","ssasa"));
        tmp.add(new Article("sasas", "3","sasasa","sasas","sasasa","ssasa"));
        tmp.add(new Article("sasas", "4","sasasa","sasas","sasasa","ssasa"));
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_articles_list, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.articles_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ArticlesAdapter(this.getActivity(), tmp);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }



}
