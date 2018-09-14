package com.example.abdulrahman.newslist.base.baseMvp;


import com.example.abdulrahman.newslist.data.DataManager;
import com.example.abdulrahman.newslist.data.entities.Entity;

public interface Interactor {
    DataManager getmDataManager();
    void execute();
    void execute(Entity entity);



}
