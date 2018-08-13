package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;

import com.squareup.picasso.Transformation;

/**
 * Created by Nick Unuchek on 19.05.2017.
 */

public class GlowTransform implements Transformation {
    int glowRadius;
    int margin;
    @ColorInt int glowColor;

    public GlowTransform(int glowRadius, int margin, int glowColor) {
        this.glowRadius = glowRadius;
        this.margin = margin;
        this.glowColor = glowColor;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int halfMargin = margin / 2;
        // The original image to use
        // extract the alpha from the source image
        Bitmap alpha = source.extractAlpha();

        // The output bitmap (with the icon + glow)
        Bitmap out = Bitmap.createBitmap(source.getWidth() + margin,
                source.getHeight() + margin, Bitmap.Config.ARGB_8888);

        // The canvas to paint on the image
        Canvas canvas = new Canvas(out);

        Paint paint = new Paint();
        paint.setColor(glowColor);

        // outer glow
        paint.setMaskFilter(new BlurMaskFilter(glowRadius, BlurMaskFilter.Blur.OUTER));
        canvas.drawBitmap(alpha, halfMargin, halfMargin, paint);

        // original icon
        canvas.drawBitmap(source, halfMargin, halfMargin, null);


        if (out != source) {
            source.recycle();
        }
        if (out != alpha) {
            alpha.recycle();
        }
        return out;
    }

    @Override
    public String key() {
        return "GlowTransform:"+glowRadius+":"+margin+":"+glowColor;
    }
}