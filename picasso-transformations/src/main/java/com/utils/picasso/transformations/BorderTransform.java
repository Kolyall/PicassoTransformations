package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.squareup.picasso.Transformation;

/**
 * Created by Nick Unuchek on 18.07.2017.
 */

public class BorderTransform implements Transformation {

    private int mBorderColor;
    private int mBorderSize;

    public BorderTransform(@ColorInt int borderColor, int borderSize) {
        this.mBorderColor = borderColor;
        this.mBorderSize = borderSize;
    }

    public BorderTransform(@ColorInt int borderColor) {
        mBorderColor = borderColor;
    }

    public BorderTransform() {
        mBorderColor = Color.BLACK;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int borderSize = mBorderSize == 0 ? Math.max(source.getWidth() / 7, source.getHeight() / 7) / 4 : this.mBorderSize;
        int width = source.getWidth() + borderSize * 2;
        int height = source.getHeight() + borderSize * 2;
        Bitmap out = Bitmap.createBitmap(width, height, source.getConfig());
        Canvas canvas = new Canvas(out);
        canvas.drawColor(mBorderColor);
        canvas.drawBitmap(source, borderSize, borderSize, null);

        if (out != source) {
            source.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "BorderTransform:" + mBorderColor+":"+mBorderSize;
    }
}
