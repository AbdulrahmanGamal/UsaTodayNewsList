package com.example.abdulrahman.newslist.di.component;

import android.app.Application;
import android.content.Context;


import com.example.abdulrahman.newslist.NewsListApp;
import com.example.abdulrahman.newslist.base.baseLib.EventBus;
import com.example.abdulrahman.newslist.base.baseLib.ImageLoader;
import com.example.abdulrahman.newslist.data.DataManager;
import com.example.abdulrahman.newslist.data.Local.prefs.PrefrencesHelper;
import com.example.abdulrahman.newslist.di.ApplicationContext;
import com.example.abdulrahman.newslist.di.module.ApplicationModule;
import com.example.abdulrahman.newslist.di.module.LibsModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by mohamedyoussef on 24/07/17.
 */

@Singleton
@Component(modules ={ ApplicationModule.class ,LibsModule.class})
public interface ApplicationComponent {

    void inject(NewsListApp app);


    @ApplicationContext
    Context context();
    Application application();
    DataManager getDataManager();
    Retrofit getRetrofit();
    EventBus getEventBus();
    ImageLoader getImageLoader();
    PrefrencesHelper getPreferencesHelper();

}
