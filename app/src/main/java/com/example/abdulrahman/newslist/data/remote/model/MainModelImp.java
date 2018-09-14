package com.example.abdulrahman.newslist.data.remote.model;

import com.example.abdulrahman.newslist.base.baseLib.EventBus;
import com.example.abdulrahman.newslist.base.baseMvp.BaseModel;
import com.example.abdulrahman.newslist.data.entities.Entity;
import com.example.abdulrahman.newslist.data.entities.NewsResponse;
import com.example.abdulrahman.newslist.data.remote.apiServices.MainServices;
import com.example.abdulrahman.newslist.utils.AppConstants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class MainModelImp extends BaseModel implements  MainModel {

    MainServices mMainServices;
    @Inject
    public MainModelImp(EventBus eventBus  , MainServices mainServices) {
        super(eventBus);
        this.mMainServices = mainServices;

    }

    @Override
    public void getAllData() {

    }

    @Override
    public void getData(Entity entity) {

    }

    @Override
    public void getAllNews(int pageCount) {
        Observable<? extends Entity > entityObservable= mMainServices.getAllNews
                (
                AppConstants.SOURCES_USA_TODAY
                ,pageCount
                ,10
                ,AppConstants.LANGUAGE_EN
                ,AppConstants.API_KEY_VALUE)
                .map(new Function<NewsResponse, Entity>() {
            @Override
            public Entity apply(@NonNull NewsResponse newsResponse) throws Exception {
                return newsResponse;
            }
        });
        subscribe(entityObservable,this);
    }
}
