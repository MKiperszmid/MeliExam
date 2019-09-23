package com.example.meliinterview.Controller;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.meliinterview.R;

public class GlideController {
    public static void loadImageFade(View view, String url, ImageView imageView) {
        Glide.with(view)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    public static void loadImageFade(View view, String url, ImageView imageView, Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5);
        circularProgressDrawable.setCenterRadius(30);
        circularProgressDrawable.start();

        Glide.with(view)
                .load(url)
                .placeholder(circularProgressDrawable)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
