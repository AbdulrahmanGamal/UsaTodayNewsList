package com.example.abdulrahman.newslist.ui;

import android.util.Log;

import com.example.abdulrahman.newslist.base.baseMvp.BaseInteractor;
import com.example.abdulrahman.newslist.data.DataManager;
import com.example.abdulrahman.newslist.data.entities.Entity;
import com.example.abdulrahman.newslist.data.entities.NewsResponse;
import com.example.abdulrahman.newslist.data.remote.model.MainModel;

import javax.inject.Inject;

import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;

/**
 * Created by abdulrahman on 9/30/17.
 */

public class MainInteractorImp extends BaseInteractor implements  MainInteractor  {

    @Inject
    public MainInteractorImp(DataManager dataManager, MainModel model) {
        super(dataManager, model);
    }


    @Override
    public void execute() {

    }

    @Override
    public void execute(Entity entity) {
        getModel(MainModel.class).getAllNews(entity.getPageCount());
    }





}
