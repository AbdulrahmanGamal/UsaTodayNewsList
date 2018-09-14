package com.example.abdulrahman.newslist;

import android.app.Activity;
import android.app.Application;
import android.graphics.Point;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.view.Display;

import com.example.abdulrahman.newslist.data.DataManager;
import com.example.abdulrahman.newslist.di.component.ApplicationComponent;
import com.example.abdulrahman.newslist.di.component.DaggerApplicationComponent;
import com.example.abdulrahman.newslist.di.module.ApplicationModule;
import com.example.abdulrahman.newslist.di.module.LibsModule;
import com.example.abdulrahman.newslist.utils.AppConstants;
import com.facebook.FacebookSdk;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class NewsListApp extends MultiDexApplication {
    private ApplicationComponent mApplicationComponent;
    public static Integer SCREEN_WIDTH = null;
    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        setUpdependancy();
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
        }


    public static void getWindowDimentions(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH = size.x;
    }
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
    private  void setUpdependancy(){
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .libsModule(new LibsModule(AppConstants.BASE_URL))
                .build();
        mApplicationComponent.inject(this);
    }

}
