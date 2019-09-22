package com.example.meliinterview.Controller;

import android.view.View;
import android.widget.ImageView;

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
}
