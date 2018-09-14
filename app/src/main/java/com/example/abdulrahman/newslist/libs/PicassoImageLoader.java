package com.example.abdulrahman.newslist.libs;

import android.content.Context;
import android.widget.ImageView;

import com.example.abdulrahman.newslist.R;
import com.example.abdulrahman.newslist.base.baseLib.ImageLoader;
import com.example.abdulrahman.newslist.di.ApplicationContext;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import static com.example.abdulrahman.newslist.NewsListApp.SCREEN_WIDTH;

/**
 * Created by abdulrahman on 24/07/17.
 */

public class PicassoImageLoader implements ImageLoader {

    private Picasso picassoRequestManager;

    @Inject
    public PicassoImageLoader(@ApplicationContext Context context) {
        this.picassoRequestManager = Picasso.with(context);
    }

    @Override
    public void load(final ImageView imageView, final String URL) {

        picassoRequestManager
                .load(URL)
                 .placeholder(R.drawable.usa_today)
                .error(R.drawable.usa_today)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(SCREEN_WIDTH, SCREEN_WIDTH / 2)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        picassoRequestManager
                                .load(URL)
                                .error(R.drawable.usa_today)
                                .into(imageView, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });

    }
    @Override
    public void load(ImageView imageView, int rsourceId) {
        picassoRequestManager
                .load(rsourceId)
                .placeholder(R.drawable.usa_today)
                .into(imageView);
    }
    @Override
    public void normalLoad(final ImageView imageView, final String URL) {
        picassoRequestManager
                .load(URL)
                .placeholder(R.drawable.usa_today)
                .error(R.drawable.usa_today)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(SCREEN_WIDTH, SCREEN_WIDTH / 2)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        picassoRequestManager
                                .load(URL)
                                .error(R.drawable.usa_today)
                                .into(imageView, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });

    }



}
