package com.example.abdulrahman.newslist.libs;




import com.example.abdulrahman.newslist.base.baseLib.EventBus;

import javax.inject.Inject;


public class GreenRobotEventBus implements EventBus {

    org.greenrobot.eventbus.EventBus eventBus;

    @Inject
    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object subscribe) {
        if (!eventBus.getDefault().isRegistered(this)) {
            eventBus.getDefault().register(subscribe);
        }
    }

    @Override
    public void unregister(Object subscribe) {

        eventBus.getDefault().unregister(subscribe);
    }

    @Override
    public void post(Object event) {

        eventBus.getDefault().post(event);

    }
}
