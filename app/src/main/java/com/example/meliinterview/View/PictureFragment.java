package com.example.meliinterview.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meliinterview.Controller.GlideController;
import com.example.meliinterview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment {
    public static final String IMAGE_KEY = "ImageKey";
    public PictureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        Bundle bundle = getArguments();
        String image = bundle.getString(IMAGE_KEY);
        ImageView imageView = view.findViewById(R.id.fp_iv_pagerimage);
        GlideController.loadImageFade(view, image, imageView);
        return view;
    }

    public static PictureFragment factory(String imageUrl){
        PictureFragment fragment = new PictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_KEY, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

}
