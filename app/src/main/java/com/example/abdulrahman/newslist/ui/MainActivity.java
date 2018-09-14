package com.example.abdulrahman.newslist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.abdulrahman.newslist.NewsListApp;
import com.example.abdulrahman.newslist.R;
import com.example.abdulrahman.newslist.base.BaseActivity;
import com.example.abdulrahman.newslist.base.OnCustomClickListener;
import com.example.abdulrahman.newslist.data.entities.Entity;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.data.entities.NewsResponse;
import com.example.abdulrahman.newslist.ui.adapter.NewsFeedAdapter;
import com.example.abdulrahman.newslist.utils.AppConstants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class MainActivity extends BaseActivity implements MainView  , OnCustomClickListener, OnLoadMoreListener {

    @Inject
    MainPresenter<MainView> mPresenter;

    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    NewsFeedAdapter newsFeedAdapter;

    NewsResponse pageCountEntity;
    private NoPaginate noPaginate;
    ArrayList<NewsItem>newsItemArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        NewsListApp.getWindowDimentions(this);
        pageCountEntity=new NewsResponse();
        pageCountEntity.setPageCount(1);


        setupNews();
        setupPagination();

        //To increase Page :-

        // pageCountEntity.setPageCount(pageCountEntity.getPageCount()+1);
    }

    @Override
    public void updateNews(ArrayList<NewsItem> allNews) {
        if (pageCountEntity.getPageCount()==1){
            newsFeedAdapter.addItems(allNews);

        }else if (pageCountEntity.getPageCount()>1){
            newsItemArrayList.clear();
            for (int i=0;i<allNews.size();i++){
                newsItemArrayList.add(allNews.get(i));

            }
            getItems();
        }


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
        switch (clickType){
            case  AppConstants.CLICK_TYPE_NEWS_ITEM:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((NewsItem)entity).getUrl()));
                startActivity(browserIntent);
                break;
        }
    }

    @Override
    public void onLoadMore() {

        Log.d("OnLoadMore", "onLoadMore: ");
        pageCountEntity.setPageCount(pageCountEntity.getPageCount()+1);
        mPresenter.getData(pageCountEntity);
//        getItems();
    }
    private void getItems() {
        showPaginateError(false);
        showPaginateLoading(true);
                newsFeedAdapter.addItems(newsItemArrayList);
                showPaginateLoading(false);

    }

    private void setupPagination() {
        noPaginate = NoPaginate.with(newsRecyclerView)
                .setOnLoadMoreListener(this)
                .setLoadingTriggerThreshold(10)
                .build();
    }


    public void showPaginateLoading(boolean isPaginateLoading) {
        noPaginate.showLoading(isPaginateLoading);
    }

    public void showPaginateError(boolean isPaginateError) {
        noPaginate.showError(isPaginateError);
    }


    @Override
    public void onDestroy() {
        noPaginate.unbind();
        super.onDestroy();
    }


}
