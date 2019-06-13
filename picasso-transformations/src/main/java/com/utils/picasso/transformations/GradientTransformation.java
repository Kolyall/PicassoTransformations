package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

import androidx.annotation.ColorInt;

/**
 * Created by User on 06.05.2017.
 */

public class GradientTransformation  implements Transformation {

     @ColorInt int startColor = Color.TRANSPARENT;
     @ColorInt int endColor = Color.argb(240,0,0,0);

    public GradientTransformation() {
    }

    public GradientTransformation(@ColorInt int endColor) {
        this.endColor = endColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    @Override public Bitmap transform(Bitmap source) {

        int x = source.getWidth();
        int y = source.getHeight();

        Bitmap out = source.copy(source.getConfig(), true);
        Canvas canvas = new Canvas(out);
        //left-top == (0,0) , right-bottom(x,y);
        LinearGradient grad =
                new LinearGradient(0, 0, 0, y, startColor, endColor, Shader.TileMode.CLAMP);
        Paint paint = new Paint(Paint.DITHER_FLAG);
        paint.setShader(null);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setShader(grad);
        canvas.drawPaint(paint);
        if (out != source) {
            source.recycle();
        }
        return out;
    }

    @Override public String key() {
        return "GradientTransformation:"+startColor+":"+endColor;
    }
}