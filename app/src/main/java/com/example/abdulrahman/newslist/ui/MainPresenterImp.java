package com.example.abdulrahman.newslist.ui;

import com.example.abdulrahman.newslist.Events.DataEvent;
import com.example.abdulrahman.newslist.base.baseLib.EventBus;
import com.example.abdulrahman.newslist.base.baseMvp.BasePresenter;
import com.example.abdulrahman.newslist.data.entities.Entity;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.data.entities.NewsResponse;

import java.util.ArrayList;

import javax.inject.Inject;


public class MainPresenterImp <V extends MainView> extends BasePresenter<V> implements MainPresenter<V> {


    @Inject
    public MainPresenterImp(MainInteractor interactor , EventBus eventBus) {
        super(interactor,eventBus);
    }




    @Override
    public void onEvent(DataEvent event) {
        Entity entities = event.getAllData();

        if (entities instanceof NewsResponse) {
            ArrayList<NewsItem> allNews= ((NewsResponse) entities).getArticles();
            getView().updateNews( allNews);
        }
    }
}
