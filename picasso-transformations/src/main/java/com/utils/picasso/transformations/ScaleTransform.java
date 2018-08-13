package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.squareup.picasso.Transformation;

public class ScaleTransform implements Transformation {

    private static final float DEFAULT_SCALE = 1.2f;

    private float mScale;

    public ScaleTransform() {
        this(DEFAULT_SCALE);
    }

    public ScaleTransform(float scale) {
        mScale = scale;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        final int width = source.getWidth();
        final int height = source.getHeight();
        final float halfWidth = width * 0.5f;
        final float halfHeight = height * 0.5f;
        final Bitmap out = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(out);
        final Matrix xform = new Matrix();
        xform.postScale(mScale, mScale, halfWidth, halfHeight);
        canvas.setMatrix(xform);
        canvas.drawBitmap(source, null, new Rect(0, 0, width, height), null);
        source.recycle();
        return out;
    }

    @Override
    public String key() {
        return "ScaleTransform"+mScale;
    }
}

