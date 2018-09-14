package com.example.abdulrahman.newslist.di.module;



import com.example.abdulrahman.newslist.base.OnCustomClickListener;
import com.example.abdulrahman.newslist.base.baseLib.ImageLoader;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.data.remote.apiServices.MainServices;
import com.example.abdulrahman.newslist.data.remote.model.MainModel;
import com.example.abdulrahman.newslist.data.remote.model.MainModelImp;
import com.example.abdulrahman.newslist.di.PerActivity;
import com.example.abdulrahman.newslist.ui.MainInteractor;
import com.example.abdulrahman.newslist.ui.MainInteractorImp;
import com.example.abdulrahman.newslist.ui.MainPresenter;
import com.example.abdulrahman.newslist.ui.MainPresenterImp;
import com.example.abdulrahman.newslist.ui.MainView;
import com.example.abdulrahman.newslist.ui.adapter.NewsFeedAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by abdulrahman on 14/08/17.
 */
@Module
public class MainModule {


    @Provides
    @PerActivity
    MainPresenter<MainView> providesMainPresenter (MainPresenterImp<MainView> mainPresenterImp){
        return  mainPresenterImp;
    }


    @Provides
    @PerActivity
    MainModel providesMainModel (MainModelImp mainModelImp){
        return mainModelImp;
    }

    @Provides
    @PerActivity
    MainServices providesMainServices(Retrofit retrofit){
        return  retrofit.create(MainServices.class);
    }


    @Provides
    @PerActivity
    MainInteractor providesMainInteractor(MainInteractorImp mainInteractorImp){

        return mainInteractorImp;
    }

    @Provides
    @PerActivity
    NewsFeedAdapter providesNewsAdapter(List<NewsItem> newsItems, ImageLoader imageLoader, OnCustomClickListener onCustomClickListener) {

        return new NewsFeedAdapter(newsItems, imageLoader, onCustomClickListener);
    }

    @Provides
    @PerActivity
    List<NewsItem> providesNewsList() {
        return new ArrayList<NewsItem>();
    }



}
