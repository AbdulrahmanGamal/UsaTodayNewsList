

package com.example.abdulrahman.newslist.base.baseMvp;

/**
 * Created by mohamedyoussef on 24/07/17.
 */


import com.example.abdulrahman.newslist.data.entities.Entity;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the View type that wants to be attached with.
 */
public interface Presenter<V extends View> {

    void onAttach(V mvpView);

    void onDetach();
    void getAllData();

    void getData(Entity entity);



}
