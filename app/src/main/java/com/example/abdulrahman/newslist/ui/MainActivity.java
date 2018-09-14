package com.example.abdulrahman.newslist.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abdulrahman.newslist.NewsListApp;
import com.example.abdulrahman.newslist.R;
import com.example.abdulrahman.newslist.base.BaseActivity;
import com.example.abdulrahman.newslist.base.OnCustomClickListener;
import com.example.abdulrahman.newslist.data.entities.Entity;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.data.entities.NewsResponse;
import com.example.abdulrahman.newslist.ui.adapter.NewsFeedAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView  , OnCustomClickListener{

    @Inject
    MainPresenter<MainView> mPresenter;

    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    NewsFeedAdapter newsFeedAdapter;

    NewsResponse pageCountEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        NewsListApp.getWindowDimentions(this);
        pageCountEntity=new NewsResponse();
        pageCountEntity.setPageCount(1);

        //To increase Page :-
       // pageCountEntity.setPageCount(pageCountEntity.getPageCount()+1);
    }

    @Override
    public void updateNews(ArrayList<NewsItem> allNews) {
        newsFeedAdapter.addItems(allNews);
        setupNews();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onDetach();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onAttach(this);
        if (isNetworkConnected()) {
            mPresenter.getData(pageCountEntity);
        }
    }

    @Override
    public void networkAvailable() {
        super.networkAvailable();
        mPresenter.getData(pageCountEntity);

    }

    void setupNews(){
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsRecyclerView.setLayoutManager(mLayoutManager);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        newsRecyclerView.setAdapter(newsFeedAdapter);
    }


    @Override
    public void onItemClick(Entity entity, String clickType) {

    }
}
