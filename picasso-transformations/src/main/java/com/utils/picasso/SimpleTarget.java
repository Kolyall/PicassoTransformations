package com.utils.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by User on 27.04.2017.
 */

public abstract class SimpleTarget implements Target {

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
