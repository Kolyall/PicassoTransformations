package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by Nikolay Unuchek on 29.04.2016.
 */
public class CircleTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap out = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(out);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();

        if (out != squaredBitmap) {
            squaredBitmap.recycle();
        }

        if (out != source) {
            source.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "CircleTransformation";
    }
}