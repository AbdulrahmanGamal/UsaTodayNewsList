package com.example.abdulrahman.newslist.ui;


import android.arch.lifecycle.LiveData;

import com.example.abdulrahman.newslist.base.baseMvp.Presenter;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.di.PerActivity;

import java.util.List;

/**
 * Created by abdulrahman on 27/07/17.
 */

@PerActivity
public interface MainPresenter<V extends MainView> extends Presenter<V> {
    List<NewsItem> getAllFavouriteNews();
    void  addNewsToFavourite(NewsItem newsItem);
    void  deleteNewsFromFavourite(long id);
}
