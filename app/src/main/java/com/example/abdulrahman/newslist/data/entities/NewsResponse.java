package com.example.abdulrahman.newslist.data.entities;

import java.util.ArrayList;

public class NewsResponse extends Entity {
    ArrayList<NewsItem> articles;

    public ArrayList<NewsItem> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsItem> articles) {
        this.articles = articles;
    }
}
