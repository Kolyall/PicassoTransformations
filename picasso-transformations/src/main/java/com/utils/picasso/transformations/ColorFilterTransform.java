package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class ColorFilterTransform implements Transformation {

    private int mMultiply;
    private int mAdd;

    public ColorFilterTransform(int multiply, int add) {
        super();
        mMultiply = multiply;
        mAdd = add;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        // Create another bitmap that will hold the results of the filter.
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap out = Bitmap.createBitmap(width, height, source.getConfig());
        Canvas canvas = new Canvas(out);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new LightingColorFilter(mMultiply, mAdd));
        canvas.drawBitmap(source, 0f, 0f, paint);
        if (out != source) {
            source.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "ColorFilterTransform:"+mMultiply+":"+mAdd;
    }

}
