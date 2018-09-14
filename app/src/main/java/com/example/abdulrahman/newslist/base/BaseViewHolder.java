

package com.example.abdulrahman.newslist.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.ParseException;

/**
 * Created by mohamedyoussef on 24/07/17.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) throws ParseException {
        mCurrentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
