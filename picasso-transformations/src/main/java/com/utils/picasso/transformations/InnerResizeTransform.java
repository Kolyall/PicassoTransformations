package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class InnerResizeTransform implements Transformation {
    private float mScale;

    public InnerResizeTransform(float scale) {
        mScale = scale;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(mScale, mScale);

        // "RECREATE" THE NEW BITMAP
        Bitmap scaled = Bitmap.createBitmap(source, 0, 0, width, height, matrix, false);

        Bitmap out = Bitmap.createBitmap(width, height, source.getConfig());

        Canvas canvas = new Canvas(out);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int dx = (width - scaled.getWidth()) / 2;
        int dy = (height - scaled.getHeight()) / 2;
        canvas.drawBitmap(scaled, dx, dy, paint);

        if (out != source) {
            source.recycle();
        }

        if (out != scaled) {
            scaled.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "InnerResizeTransform:" + mScale;
    }
}

