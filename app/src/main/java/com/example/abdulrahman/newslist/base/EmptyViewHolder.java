package com.example.abdulrahman.newslist.base;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by mohamedyoussef on 14/09/17.
 */

public class EmptyViewHolder extends BaseViewHolder implements View.OnClickListener {



    OnCustomClickListener mCustomClickListener;

    RecyclerView.Adapter mAdapter;
    PagerAdapter mPagerAdapter;


    public EmptyViewHolder(View itemView, OnCustomClickListener onCustomClickListener, RecyclerView.Adapter adapter, PagerAdapter pagerAdapter) {
        super(itemView);

        this.mCustomClickListener = onCustomClickListener;
        this.mAdapter = adapter;
        this.mPagerAdapter = pagerAdapter;

    }

    @Override
    protected void clear() {


    }

    @Override
    public void onClick(View view) {
//        mCustomClickListener.onRetryclick(this.mAdapter, null);
    }




}