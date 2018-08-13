package com.transformations.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.utils.picasso.transformations.AlphaTransform;
import com.utils.picasso.transformations.CircleTransformation;
import com.utils.picasso.transformations.RingTransform;
import com.utils.picasso.transformations.ShadeTransform;

import java.util.Arrays;

import jp.wasabeef.picasso.transformations.CropTransformation;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);

        Picasso.with(this)
                .load("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg")
                .transform(Arrays.asList(
                        new CropTransformation((float) 1, CropTransformation.GravityHorizontal.CENTER, CropTransformation.GravityVertical.CENTER)
                        , new ShadeTransform(0.7f)
                        , new CircleTransformation()
                        , new RingTransform()
                        , new AlphaTransform(0.8f)
                        )
                )
                .into(mImageView);
    }
}
