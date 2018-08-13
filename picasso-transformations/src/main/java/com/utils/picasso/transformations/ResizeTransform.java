package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.squareup.picasso.Transformation;

public class ResizeTransform implements Transformation {
    private int mTargetWidth;
    private int mTargetHeight;
    public ResizeTransform(int targetWidth, int targetHeight) {
        mTargetWidth = targetWidth;
        mTargetHeight = targetHeight;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        float scaleWidth = ((float) mTargetWidth) / width;
        float scaleHeight = ((float) mTargetHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap out = Bitmap.createBitmap(
                source, 0, 0, width, height, matrix, false);
        if (out!=source) {
            source.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "ResizeTransform:"+mTargetWidth+":"+mTargetHeight;
    }
}

