package com.example.abdulrahman.newslist.di.module;

import android.app.Application;
import android.content.Context;


import com.example.abdulrahman.newslist.data.DataManager;
import com.example.abdulrahman.newslist.data.DataManagerImp;
import com.example.abdulrahman.newslist.data.Local.db.DbHelper;
import com.example.abdulrahman.newslist.data.Local.db.DbHelperImp;
import com.example.abdulrahman.newslist.data.Local.prefs.PrefrencesHelper;
import com.example.abdulrahman.newslist.data.Local.prefs.PrefrencesHelperImp;
import com.example.abdulrahman.newslist.di.ApplicationContext;
import com.example.abdulrahman.newslist.di.PreferenceInfo;
import com.example.abdulrahman.newslist.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by abdulrahman on 24/07/17.
 */
@Module
public class ApplicationModule {
    private final Application mApplication;


    public ApplicationModule(Application application) {
        this.mApplication = application;


    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }




    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_APP_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImp dataManagerImp) {
        return dataManagerImp;
    }


    @Provides
    @Singleton
    DbHelper providesDbHelper(DbHelperImp dbHelperImp){

        return  dbHelperImp;
    }

    @Provides
    @Singleton
    PrefrencesHelper providePreferencesHelper(PrefrencesHelperImp appPreferencesHelper) {
        return appPreferencesHelper;
    }

}
