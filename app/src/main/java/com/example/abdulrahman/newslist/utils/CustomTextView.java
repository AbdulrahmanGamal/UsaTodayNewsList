package com.example.abdulrahman.newslist.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by index on 2/9/2017.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf");
        this.setTypeface(face);

    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf");
        this.setTypeface(face);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf");
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}