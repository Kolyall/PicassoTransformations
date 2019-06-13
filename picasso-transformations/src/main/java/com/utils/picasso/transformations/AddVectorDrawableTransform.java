package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.core.graphics.drawable.DrawableCompat;

import com.squareup.picasso.Transformation;

public class AddVectorDrawableTransform implements Transformation {

    private Drawable mDrawable;
    @ColorInt int mTintColor;

    public AddVectorDrawableTransform(Drawable drawable, @ColorInt int tintColor) {
        super();
        mDrawable = drawable;
        mTintColor = tintColor;
    }

    @Override
    public Bitmap transform(Bitmap bitmap) {
        // Create another bitmap that will hold the results of the filter.
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap out = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Canvas canvas = new Canvas(out);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(bitmap, 0f, 0f, paint);
        Drawable drawable = mDrawable.mutate();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, mTintColor);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
//        mDrawable.setColorFilter( mTintColor, PorterDuff.Mode.MULTIPLY);
        drawable.setBounds(width/4, height/4, 3*width/4, 3*height/4);
        drawable.draw(canvas);
        bitmap.recycle();
        return out;
    }

    @Override
    public String key() {
        return "AddDrawableTransform:"+mDrawable+", "+mTintColor;
    }

}
