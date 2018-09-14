
package com.example.abdulrahman.newslist.base.baseMvp;

import android.content.Context;
import android.net.NetworkInfo;

import com.example.abdulrahman.newslist.Events.DataEvent;
import com.example.abdulrahman.newslist.Events.ErrorEvent;
import com.example.abdulrahman.newslist.Events.Event;
import com.example.abdulrahman.newslist.R;
import com.example.abdulrahman.newslist.base.baseLib.EventBus;
import com.example.abdulrahman.newslist.data.entities.Entity;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView().
 */
public abstract class BasePresenter<V extends View> implements Presenter<V> {

    private static final String TAG = "BasePresenter";

    private Interactor mInteractor;
    private V mView;
    private EventBus mEventBus;
    private Disposable networkDisposable;
    private boolean networkconected = true;


    public BasePresenter(Interactor interactor) {
        this.mInteractor = interactor;

    }

    public BasePresenter(Interactor interactor, EventBus eventBus) {
        this.mInteractor = interactor;
        this.mEventBus = eventBus;

    }
    public BasePresenter() {

    }

    // START

    @Override
    public void onAttach(V mvpView) {
        mView = mvpView;

        if (mEventBus != null) {
            mEventBus.register(this);
        }
        networkDisposable = networkStateinit();

    }

    @Override
    public void onDetach() {


        if (networkDisposable != null && !networkDisposable.isDisposed()) {
            networkDisposable.dispose();
        }

        if (mEventBus != null) {
            mEventBus.unregister(this);
        }
        mView = null;
        networkDisposable = null;
    }

    @Override
    public void getAllData() {

        getView().showLoading();
        mInteractor.execute();

    }

    @Override
    public void getData(Entity entity) {
        getView().showLoading();
        mInteractor.execute(entity);
    }
    public V getView() {
        return mView;
    }

    public <T> T getInteractor(Class<T> interactor) {
        return interactor.cast(mInteractor);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Event event) {


        mView.hideLoading();
        if (event instanceof ErrorEvent) {
            if (((ErrorEvent) event).getErrorMessage() == null) {
                //error
                getView().onError(R.string.some_error);
            } else {
                //error message
                getView().showMessage(((ErrorEvent) event).getErrorMessage());
            }

        } else {
            if (event != null && ((DataEvent) event).getAllData() != null) {
                //update data
                onEvent((DataEvent) event);
            } else {
                //error
                getView().onError(R.string.some_error);
            }
        }


    }

    public Disposable networkStateinit() {
        return ReactiveNetwork.observeNetworkConnectivity(getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Connectivity>() {
                    @Override
                    public void accept(@NonNull Connectivity connectivity) throws Exception {

                        if (connectivity.isAvailable() && connectivity.getState() == NetworkInfo.State.CONNECTED) {

                            if (!networkconected) {
                                getView().networkAvailable();
                                networkconected = true;
                            }
                        } else {
                            networkconected = false;
                            getView().networkUnAvailable();
                        }

                    }
                });

    }
    abstract public void onEvent(DataEvent event);

    public Context getContext() {
        return getApplicationContext();
    }


    //END


}
