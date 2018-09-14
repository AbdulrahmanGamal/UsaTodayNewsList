package com.example.abdulrahman.newslist.base.baseMvp;


import com.example.abdulrahman.newslist.data.DataManager;

public abstract class BaseInteractor  implements Interactor{

    DataManager mDataManager;
    Model mModel;


    public BaseInteractor(DataManager dataManager ,Model model){

        this.mDataManager = dataManager;
        this.mModel =model;
    }


    @Override
    public DataManager getmDataManager() {
        return mDataManager;
    }

    public <T> T getModel(Class<T> model){

        return  model.cast(mModel);
    }

}
