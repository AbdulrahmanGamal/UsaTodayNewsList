package com.example.abdulrahman.newslist.ui;


import com.example.abdulrahman.newslist.base.baseMvp.Presenter;
import com.example.abdulrahman.newslist.di.PerActivity;

/**
 * Created by abdulrahman on 27/07/17.
 */

@PerActivity
public interface MainPresenter<V extends MainView> extends Presenter<V> {
}
