package com.example.abdulrahman.newslist.base;


import com.example.abdulrahman.newslist.data.entities.Entity;

/**
 * Created by mohamedyoussef on 27/07/17.
 */

public interface OnCustomClickListener {

    void onItemClick(Entity entity, String clickType);
//    void onRetryclick(RecyclerView.Adapter adapter, PagerAdapter pagerAdapter);
}
