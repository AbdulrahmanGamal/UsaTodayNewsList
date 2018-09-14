package com.example.abdulrahman.newslist.base.baseLib;

import android.widget.ImageView;


public interface ImageLoader {


    void load(ImageView imageView, String URL);
    void load(ImageView imageView, int rsourceId);
    void normalLoad(ImageView imageView, String URL);
}
