package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.squareup.picasso.Transformation;

public class RingTransform implements Transformation {

    private static final int DEFAULT_COLOR = Color.WHITE;
    private static final float DEFAULT_STROKE_PERCENT = 0.04f;

     private int mColor = DEFAULT_COLOR;
    private float mStrokePercent;
     private float mStrokeSize = 0;

    public void setColor(int color) {
        mColor = color;
    }

    public void setStrokeSize(float strokeSize) {
        mStrokeSize = strokeSize;
    }

    public RingTransform() {
        this(DEFAULT_COLOR, DEFAULT_STROKE_PERCENT);
    }

    public RingTransform(int color) {
        this(color, DEFAULT_STROKE_PERCENT);
    }

    public RingTransform(int color, float strokePercent) {
        super();
        mColor = color;
        mStrokePercent = strokePercent;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        final int width = source.getWidth();
        final int height = source.getHeight();
        final float halfWidth = width * 0.5f;
        final float halfHeight = height * 0.5f;
        final float strokeWidth = mStrokeSize != 0 ? mStrokeSize : mStrokePercent * width;
        final Bitmap out = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(out);
        final Rect rect = new Rect(0, 0, width, source.getHeight());
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Matrix xform = new Matrix();
        xform.postScale(1 - strokeWidth / width, 1 - strokeWidth / height, halfWidth, halfHeight);
        canvas.setMatrix(xform);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawBitmap(source, rect, rect, paint);
        paint.setColor(mColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(halfWidth, halfHeight, halfWidth, paint);
        if (out != source) {
            source.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "RingTransform:" + mColor + ":" + mStrokePercent;
    }
}
