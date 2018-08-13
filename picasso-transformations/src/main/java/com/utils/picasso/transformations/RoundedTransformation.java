package com.utils.picasso.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import com.squareup.picasso.Transformation;

public class RoundedTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap cropped = getCroppedBitmap(source);
        source.recycle();
        return cropped;
    }

    public static Bitmap getCroppedBitmap(Bitmap source) {
        if (source == null) { return null; }
        Bitmap sbmp;
        final int radius = source.getWidth();
        if (source.getHeight() != radius) sbmp = Bitmap.createScaledBitmap(source, radius, radius, false);
        else sbmp = source;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        return output;
    }


    @Override
    public String key() {
        return "RoundedTransformation";
    }
}
