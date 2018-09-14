package com.example.abdulrahman.newslist.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.example.abdulrahman.newslist.base.OnCustomClickListener;
import com.example.abdulrahman.newslist.di.ActivityContext;
import com.example.abdulrahman.newslist.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdulrahman on 24/07/17.
 */
@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }



    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }
    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }


    @Provides
    GridLayoutManager providesGridLayoutManager(AppCompatActivity activity) {


        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);

        return gridLayoutManager;
    }


    @Provides
    @PerActivity
    OnCustomClickListener providesOnCustomClickListener() {

        return (OnCustomClickListener) mActivity;
    }



}
