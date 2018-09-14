package com.example.abdulrahman.newslist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abdulrahman.newslist.NewsListApp;
import com.example.abdulrahman.newslist.R;
import com.example.abdulrahman.newslist.base.BaseActivity;
import com.example.abdulrahman.newslist.base.OnCustomClickListener;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.data.entities.NewsResponse;
import com.example.abdulrahman.newslist.ui.adapter.NewsFeedAdapter;
import com.example.abdulrahman.newslist.utils.AppConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
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
        setup();
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
    public void onItemClick(NewsItem entity, String clickType) {
        switch (clickType){
            case  AppConstants.CLICK_TYPE_NEWS_ITEM:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((NewsItem)entity).getUrl()));
                startActivity(browserIntent);
                break;
            case  AppConstants.CLICK_TYPE_ADD_FAV:

                mPresenter.addNewsToFavourite( entity);
                Log.d(NewsListApp.TAG, "Inserted new note, ID: " + entity.getId());
                break;
            case  AppConstants.CLICK_TYPE_UNFAV:

                Log.d(NewsListApp.TAG, "Deleted note, ID: " + entity.getId());
                mPresenter.deleteNewsFromFavourite( ( entity).getId());
                newsFeedAdapter.removeItem(entity);
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
        if (noPaginate!=null)
        {
        noPaginate.unbind();
        }
        super.onDestroy();
    }

    void  setup(){
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        NewsListApp.getWindowDimentions(this);
        pageCountEntity=new NewsResponse();
        pageCountEntity.setPageCount(1);

        setupNews();
        setupPagination();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.news_list:
                setupPagination();
                pageCountEntity=new NewsResponse();
                pageCountEntity.setPageCount(1);
                if (isNetworkConnected()) {
                    mPresenter.getData(pageCountEntity);

                }
                return true;
            case R.id.fav_list:

                if (noPaginate!=null){
                    noPaginate.unbind();
                    noPaginate=null;
                }

                newsFeedAdapter.addFavItems(mPresenter.getAllFavouriteNews());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

