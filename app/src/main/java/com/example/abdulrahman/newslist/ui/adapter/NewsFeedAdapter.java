package com.example.abdulrahman.newslist.ui.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdulrahman.newslist.R;
import com.example.abdulrahman.newslist.base.BaseViewHolder;
import com.example.abdulrahman.newslist.base.EmptyViewHolder;
import com.example.abdulrahman.newslist.base.OnCustomClickListener;
import com.example.abdulrahman.newslist.base.baseLib.ImageLoader;
import com.example.abdulrahman.newslist.data.entities.NewsItem;
import com.example.abdulrahman.newslist.utils.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsFeedAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int DATE = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public int widthItem = CardView.LayoutParams.MATCH_PARENT;
    List<NewsItem> newsItemArrayList;
    List<NewsItem> favListItems=new ArrayList<>();
    ImageLoader imageLoader;
    OnCustomClickListener mOnCustomClickListener;
    NewsItemViewHolder newsFeedViewHolder;
    Context mContext;
    int FAV_LIST_FLAG=0;


    @Inject
    public NewsFeedAdapter(List<NewsItem> newsItems, ImageLoader imageLoader,
                           OnCustomClickListener onCustomClickListener) {

        this.newsItemArrayList = newsItems;
        this.imageLoader = imageLoader;
        this.mOnCustomClickListener = onCustomClickListener;

    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        switch (viewType) {
            case VIEW_TYPE_NORMAL: {
                newsFeedViewHolder = new NewsItemViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false));
                ViewGroup.LayoutParams layoutParams = newsFeedViewHolder.itemView.getLayoutParams();

                layoutParams.width = widthItem;

                newsFeedViewHolder.itemView.setLayoutParams(layoutParams);

                return newsFeedViewHolder;
            }

            case DATE: {


            }


            default: {
                EmptyViewHolder emptyViewHolder = new EmptyViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.empty_item, parent, false), mOnCustomClickListener, this, null);
                return emptyViewHolder;
            }

        }
    }

    public void addItems(List<NewsItem> posts) {
        if (FAV_LIST_FLAG==1){
            this.newsItemArrayList.clear();
        }
        this.newsItemArrayList.addAll(posts);
        notifyDataSetChanged();
        FAV_LIST_FLAG=0;
    }
    public void addFavItems(List<NewsItem> posts) {
        this.newsItemArrayList.clear();
        this.newsItemArrayList.addAll(posts);
        notifyDataSetChanged();
        FAV_LIST_FLAG=1;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemCount() {
        if (newsItemArrayList != null && newsItemArrayList.size() > 0) {
            return newsItemArrayList.size();
        } else {
            return 1;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (newsItemArrayList != null && newsItemArrayList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return DATE;
        }
    }

    ////------------------

    public class NewsItemViewHolder extends BaseViewHolder {


        @BindView(R.id.newsName)
        TextView newsNameTextView;
        @BindView(R.id.newsTime)
        TextView newsTimeTextView;
        @BindView(R.id.newsImageView)
        ImageView newsImageView;
        @BindView(R.id.starImageView)
        ImageView starImageView;



        public NewsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


        public void onBind(int position) {
            super.onBind(position);


            final NewsItem newsFeedItem = newsItemArrayList.get(position);

            newsNameTextView.setText(newsFeedItem.getTitle());

            imageLoader.load(newsImageView,newsFeedItem.getUrlToImage());
            newsTimeTextView.setText(newsFeedItem.getPublishedAt());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnCustomClickListener.onItemClick(newsFeedItem, AppConstants.CLICK_TYPE_NEWS_ITEM);
                }
            });


            starImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        starImageView.setImageResource(R.drawable.star_filled);
                        mOnCustomClickListener.onItemClick(newsFeedItem,AppConstants.CLICK_TYPE_ADD_FAV);
                        starImageView.setVisibility(View.GONE);

                }
            });
            if (FAV_LIST_FLAG==1){
                starImageView.setImageResource(R.drawable.delete);
                starImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnCustomClickListener.onItemClick(newsFeedItem,AppConstants.CLICK_TYPE_UNFAV);

                    }
                });

            }

        }
        @Override
        protected void clear() {

            newsImageView.setImageResource(R.drawable.usa_today);
            newsNameTextView.setText("");
            newsTimeTextView.setText("");
            starImageView.setImageResource(R.drawable.star_empty);
        }



    }


   public void removeItem(NewsItem newsItem){
        newsItemArrayList.remove(newsItem);
        notifyDataSetChanged();
    }
}
