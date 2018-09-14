package com.example.abdulrahman.newslist.data.remote.apiServices;


import com.example.abdulrahman.newslist.data.entities.NewsResponse;
import com.example.abdulrahman.newslist.data.remote.ApiEndPoint;
import com.example.abdulrahman.newslist.utils.AppConstants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainServices {

    @GET(ApiEndPoint.ENDPOINT_GET_ALL_NEWS)
    Observable<NewsResponse> getAllNews(
                                     @Query(AppConstants.SOURCES) String sources
                                    ,@Query(AppConstants.PAGE) int page
                                    ,@Query(AppConstants.PAGE_SIZE) int pageSize
                                    ,@Query(AppConstants.LANGUAGE) String language
                                    ,@Query(AppConstants.API_KEY) String apiKey


    );

}
