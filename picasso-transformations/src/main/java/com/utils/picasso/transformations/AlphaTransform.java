package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.FloatRange;

import com.squareup.picasso.Transformation;

public class AlphaTransform implements Transformation {

    private int mAlpha;

    public AlphaTransform(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        mAlpha = (int) (alpha * 255);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        // Create another bitmap that will hold the results of the filter.
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap out = Bitmap.createBitmap(width, height, source.getConfig());
        Canvas canvas = new Canvas(out);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAlpha(mAlpha);
        canvas.drawBitmap(source, 0f, 0f, paint);
        if (out != source) {
            source.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "AlphaTransform:"+mAlpha;
    }

}
