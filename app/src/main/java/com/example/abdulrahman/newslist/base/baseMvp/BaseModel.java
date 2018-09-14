package com.example.abdulrahman.newslist.base.baseMvp;

import android.util.Log;
import com.example.abdulrahman.newslist.Events.DataEvent;
import com.example.abdulrahman.newslist.Events.ErrorEvent;
import com.example.abdulrahman.newslist.base.baseLib.EventBus;
import com.example.abdulrahman.newslist.data.entities.Entity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseModel implements Model, Observer<  Entity> {

    private EventBus mEventBus;

    public BaseModel(EventBus eventBus) {
        this.mEventBus = eventBus;

    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Entity entities) {
        if (entities.isError()) {
            mEventBus.post(new ErrorEvent("error"));
        } else {

            mEventBus.post(new DataEvent(  entities) ) ;

        }

    }

    @Override
    public void onError(Throwable e) {

        mEventBus.post(new ErrorEvent(null));
    }

    @Override
    public void onComplete() {
        Log.d("d","d");
    }

    protected void subscribe(Observable<  ? extends Entity  > observable , Observer observer) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);

    }


}
