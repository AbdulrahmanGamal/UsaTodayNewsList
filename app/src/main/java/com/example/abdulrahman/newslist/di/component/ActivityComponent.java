package com.example.abdulrahman.newslist.di.component;


import com.example.abdulrahman.newslist.di.module.ActivityModule;
import com.example.abdulrahman.newslist.ui.MainActivity;
import com.example.abdulrahman.newslist.di.PerActivity;
import com.example.abdulrahman.newslist.di.module.MainModule;

import dagger.Component;

/**
 * Created by mohamedyoussef on 24/07/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { MainModule.class, ActivityModule.class })

public interface ActivityComponent {

    void inject(MainActivity mainActivity);


}
