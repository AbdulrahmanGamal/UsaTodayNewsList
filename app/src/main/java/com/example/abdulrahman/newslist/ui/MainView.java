package com.example.abdulrahman.newslist.ui;


import com.example.abdulrahman.newslist.base.baseMvp.View;
import com.example.abdulrahman.newslist.data.entities.NewsItem;

import java.util.ArrayList;
import java.util.List;


public interface MainView  extends View {

    void updateNews(ArrayList<NewsItem> allNews);
}
