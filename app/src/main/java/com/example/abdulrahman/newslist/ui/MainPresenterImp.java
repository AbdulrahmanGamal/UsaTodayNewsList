package com.example.abdulrahman.newslist.ui;

import android.arch.lifecycle.LiveData;

import com.example.abdulrahman.newslist.Events.DataEvent;
import com.example.abdulrahman.newslist.NewsListApp;
import com.example.abdulrahman.newslist.base.baseLib.EventBus;
import com.example.abdulrahman.newslist.base.baseMvp.BasePresenter;
import com.example.abdulrahman.newslist.data.entities.Entity;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.data.entities.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;


public class MainPresenterImp <V extends MainView> extends BasePresenter<V> implements MainPresenter<V> {


    private Box<NewsItem> newsBox;
    private Query<NewsItem> newsQuery;

    @Inject
    public MainPresenterImp(MainInteractor interactor , EventBus eventBus) {
        super(interactor,eventBus);
        BoxStore boxStore = ((NewsListApp) getContext()).getBoxStore();

        newsBox = boxStore.boxFor(NewsItem.class);
        newsQuery = newsBox.query().build();

    }




    @Override
    public void onEvent(DataEvent event) {
        Entity entities = event.getAllData();

        if (entities instanceof NewsResponse) {
            ArrayList<NewsItem> allNews= ((NewsResponse) entities).getArticles();
            getView().updateNews( allNews);
        }
    }


    @Override
    public List<NewsItem> getAllFavouriteNews() {
        return newsQuery.find();
    }

    @Override
    public void addNewsToFavourite(NewsItem newsItem) {
        newsBox.put(newsItem);

    }

    @Override
    public void deleteNewsFromFavourite(long id) {
        newsBox.remove(id);

    }
}
