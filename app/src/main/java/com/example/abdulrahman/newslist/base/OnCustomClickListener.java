package com.example.abdulrahman.newslist.base;


import com.example.abdulrahman.newslist.data.entities.Entity;
import com.example.abdulrahman.newslist.data.entities.NewsItem;

/**
 * Created by mohamedyoussef on 27/07/17.
 */

public interface OnCustomClickListener {

    void onItemClick(NewsItem entity, String clickType);
//    void onRetryclick(RecyclerView.Adapter adapter, PagerAdapter pagerAdapter);
}
