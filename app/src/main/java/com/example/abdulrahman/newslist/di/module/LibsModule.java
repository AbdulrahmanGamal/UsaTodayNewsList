package com.example.abdulrahman.newslist.di.module;


import android.content.Context;

import com.example.abdulrahman.newslist.base.baseLib.EventBus;
import com.example.abdulrahman.newslist.base.baseLib.ImageLoader;
import com.example.abdulrahman.newslist.di.ApplicationContext;
import com.example.abdulrahman.newslist.libs.GreenRobotEventBus;
import com.example.abdulrahman.newslist.libs.PicassoImageLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abdulrahman on 24/07/17.
 */
@Module
public class LibsModule {
    private String mBaseUrl;


    public LibsModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(PicassoImageLoader picassoImageLoader) {

        return picassoImageLoader;
    }
    @Provides
    @Singleton
    EventBus providesEventBus(GreenRobotEventBus greenRobotEventBus) {
        return greenRobotEventBus;

    }


    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesEventBusLibs() {
        return new org.greenrobot.eventbus.EventBus();

    }
    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient( @ApplicationContext Context context) {

        Cache cache = new Cache(context.getCacheDir(), Integer.MAX_VALUE);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .cache(cache).build();
    }


}
