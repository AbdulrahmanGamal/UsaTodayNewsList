package com.example.abdulrahman.newslist.base.baseLib;


public interface EventBus {
    void register(Object subscribe);
    void unregister(Object subscribe);
    void post(Object event);



}
