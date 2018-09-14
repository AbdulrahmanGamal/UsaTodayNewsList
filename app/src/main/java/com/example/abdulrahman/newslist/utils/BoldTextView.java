package com.example.abdulrahman.newslist.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by index on 2/9/2017.
 */

public class BoldTextView extends android.support.v7.widget.AppCompatTextView {

    public BoldTextView(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/arabic_bold2.ttf");
        this.setTypeface(face);

    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/arabic_bold2.ttf");
        this.setTypeface(face);
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/arabic_bold2.ttf");
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}