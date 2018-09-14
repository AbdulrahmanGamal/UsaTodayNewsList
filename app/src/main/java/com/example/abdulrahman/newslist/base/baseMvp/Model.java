package com.example.abdulrahman.newslist.base.baseMvp;


import com.example.abdulrahman.newslist.data.entities.Entity;

public interface Model {
    void getAllData();
    void getData(Entity entity);
}
