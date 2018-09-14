package com.example.abdulrahman.newslist.data.remote.model;


import com.example.abdulrahman.newslist.base.baseMvp.Model;


public interface MainModel extends Model {
    void getAllNews(int pageCount);
}
