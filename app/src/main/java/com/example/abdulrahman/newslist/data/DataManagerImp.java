package com.example.abdulrahman.newslist.data;

import android.content.Context;

import com.example.abdulrahman.newslist.data.Local.prefs.PrefrencesHelper;
import com.example.abdulrahman.newslist.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManagerImp implements DataManager {


    private final Context mContext;
    private final PrefrencesHelper mPrefrencesHelper;

    @Inject
    public DataManagerImp(@ApplicationContext Context context, PrefrencesHelper prefrencesHelper) {
        this.mContext = context;
        this.mPrefrencesHelper = prefrencesHelper;

    }

}
